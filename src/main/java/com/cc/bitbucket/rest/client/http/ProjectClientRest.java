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

package com.cc.bitbucket.rest.client.http;

import com.cc.bitbucket.rest.client.ProjectClient;
import com.cc.bitbucket.rest.client.model.Branch;
import com.cc.bitbucket.rest.client.model.Task;
import com.cc.bitbucket.rest.client.model.pull.PullRequestChange;
import com.cc.bitbucket.rest.client.model.Page;
import com.cc.bitbucket.rest.client.model.pull.PullRequest;
import com.cc.bitbucket.rest.client.model.pull.activity.PullRequestActivity;
import com.cc.bitbucket.rest.client.model.User;
import com.cc.bitbucket.rest.client.Limit;
import com.cc.bitbucket.rest.client.model.Project;
import com.cc.bitbucket.rest.client.model.pull.PullRequestState;
import com.cc.bitbucket.rest.client.model.Repository;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonElement;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.cc.bitbucket.rest.client.http.responseparsers.Parsers.*;

class ProjectClientRest extends BitBucketClient implements ProjectClient {

//
//    public static void m1() throws MalformedURLException {
//
//        ProjectClient client = new ProjectClient(new BitBucketHttpExecutor(new URL("http://localhost:7990"), new BitBucketCredentials("cornelcreanga", "Cx43K}M7stash")));
//        Page<Project> page = client.getProjects(new Limit(0, 100));
//        System.out.println(page);
//        System.out.println(page.getValues());
//        Page<Repository> pageRepo = client.getProjectRepositories("TEST1", new Limit(0, 100));
//        System.out.println(pageRepo);
//        System.out.println(pageRepo.getValues());
//        System.out.println(client.getProjectByKey("TEST1"));
//
//        Page<Branch> branchPage = client.getRepositoryBranches("TEST1", "repo1", null, new Limit(0, 100));
//        System.out.println(branchPage);
//        System.out.println(branchPage.getValues());
//
//        pageRepo = client.getRepositoryForks("TEST1", "repo1", new Limit(0, 100));
//        System.out.println(pageRepo);
//        System.out.println(pageRepo.getValues());
//
//        System.out.println(client.getUsers(new Limit(0, 100)));
//    }
//
//    public static void main(String[] args) throws Exception {
//        ProjectClient.m1();
//    }

    public ProjectClientRest(BitBucketHttpExecutor bitBucketHttpExecutor) {
        super(bitBucketHttpExecutor);
    }

    @Override
    public Page<Project> getProjects(@Nonnull Limit limit) {
        String requestUrl = "/rest/api/1.0/projects" + addLimits(limit);
        JsonElement jsonElement = execute(requestUrl, HttpMethod.GET, null, false);
        return pageParser(projectParser()).apply(jsonElement);
    }

    @Override
    public Optional<Project> getProjectByKey(@Nonnull String projectKey) {
        try {
            String requestUrl = String.format("/rest/api/1.0/projects/%s", projectKey);
            JsonElement jsonElement = execute(requestUrl, HttpMethod.GET, null, false);
            return Optional.of(projectParser().apply(jsonElement));
        } catch (ResourceNotFoundException e) {
            return Optional.empty();
        }
    }

    @Override
    public Page<Repository> getProjectRepositories(@Nonnull String projectKey, @Nonnull Limit limit) {
        String requestUrl = String.format("/rest/api/1.0/projects/%s/repos", projectKey) + addLimits(limit);
        JsonElement jsonElement = execute(requestUrl, HttpMethod.GET, null, false);
        return pageParser(repositoryParser()).apply(jsonElement);
    }

    @Override
    public Page<Repository> getAllRepositories(@Nonnull Limit limit) {
        String requestUrl = "/rest/api/1.0/repos" + addLimits(limit);
        JsonElement jsonElement = execute(requestUrl, HttpMethod.GET, null, false);
        return pageParser(repositoryParser()).apply(jsonElement);
    }

    @Override
    public Optional<Repository> getRepositoryBySlug(@Nonnull String projectKey, @Nonnull String repositorySlug) {
        try {
            String requestUrl = String.format("/rest/api/1.0/projects/%s/repos/%s", projectKey, repositorySlug);
            JsonElement jsonElement = execute(requestUrl, HttpMethod.GET, null, false);
            return Optional.of(repositoryParser().apply(jsonElement));
        } catch (ResourceNotFoundException e) {
            return Optional.empty();
        }
    }

    @Override
    public Page<Branch> getRepositoryBranches(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nullable String query, @Nonnull Limit limit) {
        String requestUrl = String.format("/rest/api/1.0/projects/%s/repos/%s/branches", projectKey, repositorySlug) + addLimits(limit);
        requestUrl += addParameter("filterText", query);
        requestUrl += "&details=true&orderBy=MODIFICATION";

        JsonElement jsonElement = execute(requestUrl, HttpMethod.GET, null, false);
        Page<Branch> page = pageParser(branchParser()).apply(jsonElement);
        List<Branch> list = page.getValues();
        for (Branch branch : list) {
            branch.setProjectKey(projectKey);
            branch.setRepositorySlug(repositorySlug);
        }
        return page;
    }

    @Override
    public Page<Repository> getRepositoryForks(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nonnull Limit limit) {
        String requestUrl = String.format("/rest/api/1.0/projects/%s/repos/%s/forks", projectKey, repositorySlug) + addLimits(limit);

        JsonElement jsonElement = execute(requestUrl, HttpMethod.GET, null, false);
        return pageParser(repositoryParser()).apply(jsonElement);
    }


    @Override
    public Optional<Branch> getRepositoryDefaultBranch(@Nonnull String projectKey, @Nonnull String repositorySlug) {
        try {
            String requestUrl = String.format("/rest/api/1.0/projects/%s/repos/%s/branches/default", projectKey, repositorySlug);
            JsonElement jsonElement = execute(requestUrl, HttpMethod.GET, null, false);
            return Optional.of(branchParser().apply(jsonElement));
        } catch (ResourceNotFoundException e) {
            return Optional.empty();
        }
    }

    @Override
    public Page<PullRequest> getRepositoryPullRequests(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nonnull PullRequestState pullRequestState,@Nonnull Limit limit) {
        //todo - check direction
        String requestUrl = String.format("/rest/api/1.0/projects/%s/repos/%s/pull-requests", projectKey, repositorySlug) + addLimits(limit);

        requestUrl+="&direction=INCOMING&state="+pullRequestState.toString();
        JsonElement jsonElement = execute(requestUrl, HttpMethod.GET, null, false);
        return pageParser(pullRequestParser()).apply(jsonElement);
    }

    @Override
    public Page<PullRequestChange> getRepositoryPullRequestsChanges(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nonnull Long pullRequestId,@Nonnull Limit limit) {
        String requestUrl = String.format("/rest/api/1.0/projects/%s/repos/%s/pull-requests/%s/changes",
                projectKey,
                repositorySlug,
                pullRequestId) + addLimits(limit);

        JsonElement jsonElement = execute(requestUrl, HttpMethod.GET, null, false);
        return pageParser(pullRequestChangeParser()).apply(jsonElement);
    }
    @Override
    public Page<PullRequestActivity> getRepositoryPullRequestsActivities(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nonnull Long pullRequestId,@Nonnull Limit limit) {
        String requestUrl = String.format("/rest/api/1.0/projects/%s/repos/%s/pull-requests/%s/activities",
                projectKey,
                repositorySlug,
                pullRequestId) + addLimits(limit);

        JsonElement jsonElement = execute(requestUrl, HttpMethod.GET, null, false);
        return pageParser(pullRequestActivityParser()).apply(jsonElement);
    }

    @Override
    public Page<Task> getRepositoryPullRequestsTasks(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nonnull Long pullRequestId,@Nonnull Limit limit){
        String requestUrl = String.format("/rest/api/1.0/projects/%s/repos/%s/pull-requests/%s/activities",
                projectKey,
                repositorySlug,
                pullRequestId) + addLimits(limit);

        JsonElement jsonElement = execute(requestUrl, HttpMethod.GET, null, false);
        return pageParser(taskParser()).apply(jsonElement);
    }

    @Override
    public Page<User> getUsers(@Nonnull Limit limit) {
        String requestUrl = "/rest/api/1.0/users" + addLimits(limit);

        JsonElement jsonElement = execute(requestUrl, HttpMethod.GET, null, false);
        return pageParser(userParser()).apply(jsonElement);
    }

    @Override
    public ImmutableMap<String, String> getBitBucketApplicationProperties() {
        String requestUrl = "/rest/api/1.0/application-properties";

        JsonElement jsonElement = execute(requestUrl, HttpMethod.GET, null, true);
        ImmutableMap.Builder<String, String> resultBuilder = ImmutableMap.builder();
        if (jsonElement != null) {
            for (Map.Entry<String, JsonElement> entry : jsonElement.getAsJsonObject().entrySet()) {
                resultBuilder.put(entry.getKey(), entry.getValue().getAsString());
            }
        }
        return resultBuilder.build();
    }


}
