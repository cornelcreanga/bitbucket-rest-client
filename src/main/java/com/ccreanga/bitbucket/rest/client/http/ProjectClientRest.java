/*
 *
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.ccreanga.bitbucket.rest.client.http;

import com.ccreanga.bitbucket.rest.client.ProjectClient;
import com.ccreanga.bitbucket.rest.client.http.dto.CreateProjectKeyRequest;
import com.ccreanga.bitbucket.rest.client.http.dto.CreateRepositoryRequest;
import com.ccreanga.bitbucket.rest.client.model.Branch;
import com.ccreanga.bitbucket.rest.client.model.ProjectType;
import com.ccreanga.bitbucket.rest.client.model.Task;
import com.ccreanga.bitbucket.rest.client.model.pull.PullRequestChange;
import com.ccreanga.bitbucket.rest.client.model.Page;
import com.ccreanga.bitbucket.rest.client.model.pull.PullRequest;
import com.ccreanga.bitbucket.rest.client.model.pull.activity.PullRequestActivity;
import com.ccreanga.bitbucket.rest.client.model.User;
import com.ccreanga.bitbucket.rest.client.Range;
import com.ccreanga.bitbucket.rest.client.model.Project;
import com.ccreanga.bitbucket.rest.client.model.pull.PullRequestState;
import com.ccreanga.bitbucket.rest.client.model.Repository;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonElement;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import static com.ccreanga.bitbucket.rest.client.http.responseparsers.Parsers.*;
import static com.ccreanga.bitbucket.rest.client.http.HttpMethod.*;

class ProjectClientRest extends BitBucketClient implements ProjectClient {

    public static final int DEFAULT_LIMIT = 25;

    public ProjectClientRest(BitBucketHttpExecutor bitBucketHttpExecutor) {
        super(bitBucketHttpExecutor);
    }

    @Override
    public Page<Project> getProjects(@Nonnull Range range) {
        String requestUrl = "/rest/api/1.0/projects" + addLimits(range);
        JsonElement jsonElement = execute(requestUrl, GET, null, false).get();
        return pageParser(projectParser()).apply(jsonElement);
    }

    public Set<Project> getAllProjects() {
        Range range = new Range(0, DEFAULT_LIMIT);
        Page<Project> page = getProjects(range);
        Set<Project> projects = new HashSet<>(page.getSize());
        projects.addAll(page.getValues());
        while(page.getNextPageStart()!=null){
            range = new Range(page.getNextPageStart(), DEFAULT_LIMIT);
            projects.addAll(getProjects(range).getValues());
        }
        return projects;
    }

    @Override
    public Optional<Project> getProjectByKey(@Nonnull String projectKey) {
        try {
            String requestUrl = String.format("/rest/api/1.0/projects/%s", projectKey);
            JsonElement jsonElement = execute(requestUrl, GET, null, false).get();
            return Optional.of(projectParser().apply(jsonElement));
        } catch (ResourceNotFoundException e) {
            return Optional.empty();
        }
    }

    @Override
    public Page<Repository> getProjectRepositories(@Nonnull String projectKey, @Nonnull Range range) {
        String requestUrl = String.format("/rest/api/1.0/projects/%s/repos", projectKey) + addLimits(range);
        JsonElement jsonElement = execute(requestUrl, GET, null, false).get();
        return pageParser(repositoryParser()).apply(jsonElement);
    }

    @Override
    public Set<Repository> getAllProjectRepositories(@Nonnull String projectKey) {
        Range range = new Range(0, DEFAULT_LIMIT);
        Page<Repository> page = getProjectRepositories(projectKey,range);
        Set<Repository> repositories = new HashSet<>(page.getSize());
        repositories.addAll(page.getValues());
        while(page.getNextPageStart()!=null){
            range = new Range(page.getNextPageStart(), DEFAULT_LIMIT);
            repositories.addAll(getProjectRepositories(projectKey, range).getValues());
        }
        return repositories;
    }

    @Override
    public Page<Repository> getAllRepositories(@Nonnull Range range) {
        String requestUrl = "/rest/api/1.0/repos" + addLimits(range);
        JsonElement jsonElement = execute(requestUrl, GET, null, false).get();
        return pageParser(repositoryParser()).apply(jsonElement);
    }

    @Override
    public Set<Repository> getAllRepositories() {
        Range range = new Range(0, DEFAULT_LIMIT);
        Page<Repository> page = getAllRepositories(range);
        Set<Repository> repositories = new HashSet<>(page.getSize());
        repositories.addAll(page.getValues());
        while(page.getNextPageStart()!=null){
            range = new Range(page.getNextPageStart(), DEFAULT_LIMIT);
            repositories.addAll(getAllRepositories(range).getValues());
        }
        return repositories;
    }

    @Override
    public Optional<Repository> getRepositoryBySlug(@Nonnull String projectKey, @Nonnull String repositorySlug) {
        try {
            String requestUrl = String.format("/rest/api/1.0/projects/%s/repos/%s", projectKey, repositorySlug);
            JsonElement jsonElement = execute(requestUrl, GET, null, false).get();
            return Optional.of(repositoryParser().apply(jsonElement));
        } catch (ResourceNotFoundException e) {
            return Optional.empty();
        }
    }

    @Override
    public Page<Branch> getRepositoryBranches(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nullable String query, @Nonnull Range range) {
        String requestUrl = String.format("/rest/api/1.0/projects/%s/repos/%s/branches", projectKey, repositorySlug) + addLimits(range);
        requestUrl += addParameter("filterText", query);
        requestUrl += "&details=true&orderBy=MODIFICATION";

        JsonElement jsonElement = execute(requestUrl, GET, null, false).get();
        Page<Branch> page = pageParser(branchParser()).apply(jsonElement);
        List<Branch> list = page.getValues();
        for (Branch branch : list) {
            branch.setProjectKey(projectKey);
            branch.setRepositorySlug(repositorySlug);
        }
        return page;
    }

    @Override
    public Set<Branch> getAllRepositoryBranches(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nullable String query) {
        Range range = new Range(0, DEFAULT_LIMIT);
        Page<Branch> page = getRepositoryBranches(projectKey, repositorySlug,query,range);
        Set<Branch> branches = new HashSet<>(page.getSize());
        branches.addAll(page.getValues());
        while(page.getNextPageStart()!=null){
            range = new Range(page.getNextPageStart(), DEFAULT_LIMIT);
            branches.addAll( getRepositoryBranches(projectKey, repositorySlug, query, range).getValues());
        }
        return branches;
    }

    @Override
    public Page<Repository> getRepositoryForks(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nonnull Range range) {
        String requestUrl = String.format("/rest/api/1.0/projects/%s/repos/%s/forks", projectKey, repositorySlug) + addLimits(range);

        JsonElement jsonElement = execute(requestUrl, GET, null, false).get();
        return pageParser(repositoryParser()).apply(jsonElement);
    }

    @Override
    public Set<Repository> getAllRepositoryForks(@Nonnull String projectKey, @Nonnull String repositorySlug) {
        Range range = new Range(0, DEFAULT_LIMIT);
        Page<Repository> page = getRepositoryForks(projectKey, repositorySlug, range);
        Set<Repository> repositories = new HashSet<>(page.getSize());
        repositories.addAll(page.getValues());
        while(page.getNextPageStart()!=null){
            range = new Range(page.getNextPageStart(), DEFAULT_LIMIT);
            repositories.addAll( getRepositoryForks(projectKey, repositorySlug, range).getValues());
        }
        return repositories;
    }


    @Override
    public Optional<Branch> getRepositoryDefaultBranch(@Nonnull String projectKey, @Nonnull String repositorySlug) {
        try {
            String requestUrl = String.format("/rest/api/1.0/projects/%s/repos/%s/branches/default", projectKey, repositorySlug);
            JsonElement jsonElement = execute(requestUrl, GET, null, false).get();
            return Optional.of(branchParser().apply(jsonElement));
        } catch (ResourceNotFoundException e) {
            return Optional.empty();
        }
    }

    @Override
    public Page<PullRequest> getRepositoryPullRequests(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nonnull PullRequestState pullRequestState,@Nonnull Range range) {
        //todo - check direction
        String requestUrl = String.format("/rest/api/1.0/projects/%s/repos/%s/pull-requests", projectKey, repositorySlug) + addLimits(range);

        requestUrl+="&direction=INCOMING&state="+pullRequestState.toString();
        JsonElement jsonElement = execute(requestUrl, GET, null, false).get();
        return pageParser(pullRequestParser()).apply(jsonElement);
    }

    @Override
    public Set<PullRequest> getAllRepositoryPullRequests(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nonnull PullRequestState pullRequestState) {
        Range range = new Range(0, DEFAULT_LIMIT);
        Page<PullRequest> page = getRepositoryPullRequests(projectKey, repositorySlug, pullRequestState, range);
        Set<PullRequest> pullRequests = new HashSet<>(page.getSize());
        pullRequests.addAll(page.getValues());
        while(page.getNextPageStart()!=null){
            range = new Range(page.getNextPageStart(), DEFAULT_LIMIT);
            pullRequests.addAll( getRepositoryPullRequests(projectKey, repositorySlug, pullRequestState, range).getValues());
        }
        return pullRequests;
    }

    @Override
    public Page<PullRequestChange> getRepositoryPullRequestsChanges(
            @Nonnull String projectKey,
            @Nonnull String repositorySlug,
            @Nonnull Long pullRequestId,
            String sinceCommitId,
            @Nonnull Range range) {
        String requestUrl = String.format("/rest/api/1.0/projects/%s/repos/%s/pull-requests/%s/changes",
                projectKey,
                repositorySlug,
                pullRequestId) + addLimits(range);
        if (sinceCommitId!=null)
            requestUrl+="&since="+sinceCommitId;
        JsonElement jsonElement = execute(requestUrl, GET, null, false).get();
        return pageParser(pullRequestChangeParser()).apply(jsonElement);
    }

    @Override
    public Set<PullRequestChange> getAllRepositoryPullRequestsChanges(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nonnull Long pullRequestId, String sinceCommitId) {
        Range range = new Range(0, DEFAULT_LIMIT);
        Page<PullRequestChange> page = getRepositoryPullRequestsChanges(projectKey, repositorySlug, pullRequestId,sinceCommitId, range);
        Set<PullRequestChange> pullRequestChanges = new HashSet<>(page.getSize());
        pullRequestChanges.addAll(page.getValues());
        while(page.getNextPageStart()!=null){
            range = new Range(page.getNextPageStart(), DEFAULT_LIMIT);
            pullRequestChanges.addAll( getRepositoryPullRequestsChanges(projectKey, repositorySlug, pullRequestId,sinceCommitId, range).getValues());
        }
        return pullRequestChanges;
    }

    @Override
    public Page<PullRequestActivity> getRepositoryPullRequestsActivities(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nonnull Long pullRequestId,@Nonnull Range range) {
        String requestUrl = String.format("/rest/api/1.0/projects/%s/repos/%s/pull-requests/%s/activities",
                projectKey,
                repositorySlug,
                pullRequestId) + addLimits(range);

        JsonElement jsonElement = execute(requestUrl, GET, null, false).get();
        return pageParser(pullRequestActivityParser()).apply(jsonElement);
    }

    @Override
    public Set<PullRequestActivity> getAllRepositoryPullRequestsActivities(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nonnull Long pullRequestId) {
        Range range = new Range(0, DEFAULT_LIMIT);
        Page<PullRequestActivity> page = getRepositoryPullRequestsActivities(projectKey, repositorySlug, pullRequestId, range);
        Set<PullRequestActivity> pullRequestActivities = new HashSet<>(page.getSize());
        pullRequestActivities.addAll(page.getValues());
        while(page.getNextPageStart()!=null){
            range = new Range(page.getNextPageStart(), DEFAULT_LIMIT);
            pullRequestActivities.addAll( getRepositoryPullRequestsActivities(projectKey, repositorySlug, pullRequestId, range).getValues());
        }
        return pullRequestActivities;
    }

    @Override
    public Page<Task> getRepositoryPullRequestsTasks(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nonnull Long pullRequestId,@Nonnull Range range){
        String requestUrl = String.format("/rest/api/1.0/projects/%s/repos/%s/pull-requests/%s/activities",
                projectKey,
                repositorySlug,
                pullRequestId) + addLimits(range);

        JsonElement jsonElement = execute(requestUrl, GET, null, false).get();
        return pageParser(taskParser()).apply(jsonElement);
    }

    @Override
    public Set<Task> getAllRepositoryPullRequestsTasks(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nonnull Long pullRequestId) {
        Range range = new Range(0, DEFAULT_LIMIT);
        Page<Task> page = getRepositoryPullRequestsTasks(projectKey, repositorySlug, pullRequestId, range);
        Set<Task> tasks = new HashSet<>(page.getSize());
        tasks.addAll(page.getValues());
        while(page.getNextPageStart()!=null){
            range = new Range(page.getNextPageStart(), DEFAULT_LIMIT);
            tasks.addAll( getRepositoryPullRequestsTasks(projectKey, repositorySlug, pullRequestId, range).getValues());
        }
        return tasks;
    }

    @Override
    public Page<User> getUsers(@Nonnull Range range) {
        String requestUrl = "/rest/api/1.0/users" + addLimits(range);

        JsonElement jsonElement = execute(requestUrl, GET, null, false).get();
        return pageParser(userParser()).apply(jsonElement);
    }

    @Override
    public Set<User> getAllUsers() {
        Range range = new Range(0, DEFAULT_LIMIT);
        Page<User> page = getUsers(range);
        Set<User> users = new HashSet<>(page.getSize());
        users.addAll(page.getValues());
        while(page.getNextPageStart()!=null){
            range = new Range(page.getNextPageStart(), DEFAULT_LIMIT);
            users.addAll(getUsers(range).getValues());
        }
        return users;
    }

    @Override
    public ImmutableMap<String, String> getBitBucketApplicationProperties() {
        String requestUrl = "/rest/api/1.0/application-properties";

        JsonElement jsonElement = execute(requestUrl, GET, null, true).get();
        ImmutableMap.Builder<String, String> resultBuilder = ImmutableMap.builder();
        if (jsonElement != null) {
            for (Map.Entry<String, JsonElement> entry : jsonElement.getAsJsonObject().entrySet()) {
                resultBuilder.put(entry.getKey(), entry.getValue().getAsString());
            }
        }
        return resultBuilder.build();
    }

    @Override
    public Project createProject(@Nonnull String projectKey, @Nonnull String name, @Nonnull ProjectType type, @Nullable String description) {
        final CreateProjectKeyRequest payload = new CreateProjectKeyRequest(projectKey, name, type.name(), description);
        final String requestUrl = "/rest/api/1.0/projects";
        return projectParser().apply(execute(requestUrl, POST, payload.toJson(), false).get());
    }

    @Override
    public Repository createRepository(@Nonnull String projectKey, @Nonnull String name, @Nonnull String scmId, boolean forkable) {
        final CreateRepositoryRequest payload = new CreateRepositoryRequest(name, scmId, forkable);
        final String requestUrl = "/rest/api/1.0/projects/" + projectKey + "/repos";
        return repositoryParser().apply(execute(requestUrl, POST, payload.toJson(), false).get());
    }

    @Override
    public void deleteProject(@Nonnull String projectKey) {
        final String requestUrl = "/rest/api/1.0/projects/" + projectKey;
        execute(requestUrl,DELETE,null, false);
    }

    @Override
    public void deleteRepository(@Nonnull String projectKey, @Nonnull String repositorySlug) {
        final String requestUrl = "/rest/api/1.0/projects/" + projectKey + "/repos/" + repositorySlug;
        execute(requestUrl, DELETE, null, false);
    }



}
