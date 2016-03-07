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

package com.ccreanga.bitbucket.rest.client;

import com.ccreanga.bitbucket.rest.client.model.*;
import com.ccreanga.bitbucket.rest.client.model.pull.PullRequest;
import com.ccreanga.bitbucket.rest.client.model.pull.PullRequestRole;
import com.ccreanga.bitbucket.rest.client.model.pull.activity.PullRequestActivity;
import com.ccreanga.bitbucket.rest.client.model.pull.PullRequestChange;
import com.ccreanga.bitbucket.rest.client.model.pull.PullRequestState;
import com.google.common.collect.ImmutableMap;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;
import java.util.Set;

public interface ProjectClient {
    /**
     * Returns a page of projects (between Range.start and Range.start+Range.limit) 
     * Only projects for which the authenticated user has the PROJECT_VIEW permission will be returned
     * @param range limit object
     * @return a Page of projects
     */
    Page<Project> getProjects(@Nonnull Range range);

    /**
     * Returns all the projects
     * Only projects for which the authenticated user has the PROJECT_VIEW permission will be returned
     * @return a Set of projects
     */
    Set<Project> getProjects();

    /**
     * Returns a project specified by its project key
     * @param projectKey project key
     * @return Optional object wrapping the project
     */
    Optional<Project> getProjectByKey(@Nonnull String projectKey);

    /**
     * Returns a page of repositories (between Range.start and Range.start+Range.limit)  belonging to a project
     * The authenticated user must have REPO_READ permission for the specified project
     * @param projectKey project key
     * @param range limit object
     * @return a Page of repositories
     */
    Page<Repository> getProjectRepositories(@Nonnull String projectKey, @Nonnull Range range);

    /**
     * Returns all the repositories belonging to a project
     * The authenticated user must have REPO_READ permission for the specified project
     * @param projectKey project key
     * @return a Set of repositories
     */
    Set<Repository> getProjectRepositories(@Nonnull String projectKey);
    /**
     * Returns a page of repositories (between Range.start and Range.start+Range.limit) 
     * @param range limit object
     * @return a Page of repositories
     */
    Page<Repository> getAllRepositories(@Nonnull Range range);
    /**
     * Returns all the repositories
     * The authenticated user must have REPO_READ permission for the specified project
     * @return a Set of repositories
     */
    Set<Repository> getAllRepositories();

    /**
     * Returns a repository specified by its project key/repository slug
     * @param projectKey project key
     * @param repositorySlug repository slug
     * @return a Page of repositories
     */
    Optional<Repository> getRepositoryBySlug(@Nonnull String projectKey, @Nonnull String repositorySlug);

    /**
     * Returns a page of repository branches (between Range.start and Range.start+Range.limit)
     * @param projectKey project key
     * @param repositorySlug repository slug
     * @param query optional parameter. If not null it will return the braches matching this parameter
     * @param range limit object
     * @return a Page of branches
     */
    Page<Branch> getBranches(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nullable String query, @Nonnull Range range);
    /**
     * Returns all the repository branches
     * @param projectKey project key
     * @param repositorySlug repository slug
     * @param query optional parameter. If not null it will return the braches matching this parameter
     * @return a Set of branches
     */
    Set<Branch> getBranches(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nullable String query);

    /**
     * Returns a page of forks (between Range.start and Range.start+Range.limit)  associated with a repository
     * @param projectKey project key
     * @param repositorySlug repository slug
     * @param range limit object
     * @return a Page of forks
     */
    Page<Repository> getForks(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nonnull Range range);
    /**
     * Returns all the repository forks
     * @param projectKey project key
     * @param repositorySlug repository slug
     * @return a Set of forks
     */
    Set<Repository> getForks(@Nonnull String projectKey, @Nonnull String repositorySlug);

    /**
     * Returns the default branch for a repository
     * @param projectKey project key
     * @param repositorySlug repository slug
     * @return Optional object wrapping the branch
     */
    Optional<Branch> getDefaultBranch(@Nonnull String projectKey, @Nonnull String repositorySlug);

    /**
     * Returns a page of pull requests (between Range.start and Range.start+Range.limit)  associated with a project/repository, with the specified state
     * @param projectKey project key
     * @param repositorySlug repository slug
     * @param pullRequestState (optional) pullRequest state; null means all the states
     * @param incoming the direction relative to the specified repository (true=INCOMING false=OUTGOING).
     * @param branchId (optional) a fully-qualified branch ID to find pull requests to or from
     * @param range limit object
     * @return a Page of PullRequests
     */
    Page<PullRequest> getPullRequests(@Nonnull String projectKey, @Nonnull String repositorySlug,PullRequestState pullRequestState, boolean incoming,
                                      String branchId,  @Nonnull Range range);

    /**
     * Returns all the pull requests associated with a project/repository, with the specified state
     * @param projectKey project key
     * @param repositorySlug repository slug
     * @param pullRequestState pullRequest state; null means all the states
     * @param incoming the direction relative to the specified repository (true=INCOMING false=OUTGOING).
     * @param branchId (optional) a fully-qualified branch ID to find pull requests to or from
     * @return a Set of PullRequests
     */
    Set<PullRequest> getPullRequests(@Nonnull String projectKey, @Nonnull String repositorySlug,PullRequestState pullRequestState, boolean incoming,
                                     String branchId);

    /**
     * Returns a page of pull requests (between Range.start and Range.start+Range.limit)  associated with a project/repository, with the specified state
     * @param projectKey project key
     * @param repositorySlug repository slug
     * @param pullRequestState (optional) pullRequest state; null means all the states
     * @param incoming the direction relative to the specified repository (true=INCOMING false=OUTGOING).
     * @param branchId (optional) a fully-qualified branch ID to find pull requests to or from
     * @param newestFirst the order to return pull requests in (newest/oldest)
     * @param users filter the pull request specifying an array of participants
     * @param pullRequestRoles (optional) - specify the role for each participant
     * @param approved (optional) - the approved status associated with the users
     * @param range limit object
     * @return a Page of PullRequests
     */
    public Page<PullRequest> getPullRequests(
            @Nonnull String projectKey,
            @Nonnull String repositorySlug,
            PullRequestState pullRequestState,
            boolean incoming,
            String branchId,
            boolean newestFirst,
            String[] users,
            PullRequestRole[] pullRequestRoles,
            boolean[] approved,
            @Nonnull Range range);

    public Set<PullRequest> getPullRequests(
            @Nonnull String projectKey,
            @Nonnull String repositorySlug,
            PullRequestState pullRequestState,
            boolean incoming,
            String branchId,
            boolean newestFirst,
            String[] users,
            PullRequestRole[] pullRequestRoles,
            boolean[] approved);

    /**
     * Returns a page of changes (between Range.start and Range.start+Range.limit)  associated with a project/repository/pull request
     * @param projectKey project key
     * @param repositorySlug repository slug
     * @param pullRequestId pull request id
     * @param sinceCommitId the commit to which until should be compared to produce a page of changes. If not specified the commit's first parent is assumed
     * @param range limit object
     * @return a Page of PullRequestChange objects
     */
    Page<PullRequestChange> getPullRequestsChanges(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nonnull Long pullRequestId, String sinceCommitId, @Nonnull Range range);
    /**
     * Returns all the changes associated with a project/repository/pull request
     * @param projectKey project key
     * @param repositorySlug repository slug
     * @param pullRequestId pull request id
     * @param sinceCommitId the commit to which until should be compared to produce a page of changes. If not specified the commit's first parent is assumed
     * @return a Set of PullRequestChange objects
     */
    Set<PullRequestChange> getPullRequestsChanges(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nonnull Long pullRequestId, String sinceCommitId);

    /**
     * Returns a page of activities (between Range.start and Range.start+Range.limit)  associated with a project/repository/pull request
     * @param projectKey project key
     * @param repositorySlug repository slug
     * @param pullRequestId pull request id
     * @param range limit object
     * @return a Page of PullRequestActivity objects
     */
    Page<PullRequestActivity> getPullRequestsActivities(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nonnull Long pullRequestId, @Nonnull Range range);
    /**
     * Returns all the associated with a project/repository/pull request
     * @param projectKey project key
     * @param repositorySlug repository slug
     * @param pullRequestId pull request id
     * @return a Set of PullRequestActivity objects
     */
    Set<PullRequestActivity> getPullRequestsActivities(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nonnull Long pullRequestId);

    /**
     * Returns a page of tasks (between Range.start and Range.start+Range.limit)  associated with a project/repository/pull request
     * @param projectKey project key
     * @param repositorySlug repository slug
     * @param pullRequestId pull request id
     * @param range limit object
     * @return a Page of Task objects
     */
    Page<Task> getPullRequestsTasks(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nonnull Long pullRequestId, @Nonnull Range range);
    /**
     * Returns all the tasks associated with a project/repository/pull request
     * @param projectKey project key
     * @param repositorySlug repository slug
     * @param pullRequestId pull request id
     * @return a Set of Task objects
     */
    Set<Task> getPullRequestsTasks(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nonnull Long pullRequestId);

    /**
     * Returns a page of users (between Range.start and Range.start+Range.limit) 
     * @param range limit object
     * @return a Page of User objects
     */
    Page<User> getUsers(@Nonnull Range range);
    /**
     * Returns all the users (between Range.start and Range.start+Range.limit)
     * @return a Set of User objects
     */
    Set<User> getUsers();


    /**
     * Retrieve version information and other application properties
     * @return map of properties
     */
    ImmutableMap<String, String> getBitBucketApplicationProperties();

    /**
     * Creates a project belonging to the current user
     * @param projectKey project key (unique key)
     * @param name project name
     * @param type project type
     * @param description project description
     * @return the created project
     */
    Project createProject(@Nonnull String projectKey, @Nonnull String name, @Nonnull ProjectType type, String description);

    /**
     * Creates a repository belonging to the current user
     * @param projectKey project key (unique key)
     * @param name repository name
     * @param scmId repository scmId
     * @param forkable is the repository forkable?
     * @return the created repository
     */
    Repository createRepository(@Nonnull String projectKey, @Nonnull String name, @Nonnull String scmId, boolean forkable);

    /**
     * Deletes a project
     * @param projectKey project key (unique key)
     */
    void deleteProject(@Nonnull String projectKey);

    /**
     * Deletes a repository identified by project key+repository slug
     * @param projectKey project key
     * @param repositorySlug repository slug
     */
    void deleteRepository(@Nonnull String projectKey, @Nonnull String repositorySlug);

}
