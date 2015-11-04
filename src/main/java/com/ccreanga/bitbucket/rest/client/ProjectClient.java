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
     * Returns a page of projects (between Limit.start and Limit.end)
     * Only projects for which the authenticated user has the PROJECT_VIEW permission will be returned
     * @param range limit object
     * @return a Page of projects
     */
    Page<Project> getProjects(@Nonnull Range range);

    Set<Project> getAllProjects();

    /**
     * Returns a project specified by its project key
     * @param projectKey project key
     * @return Optional object wrapping the project
     */
    Optional<Project> getProjectByKey(@Nonnull String projectKey);

    /**
     * Returns a page of repositories (between Limit.start and Limit.end) belonging to a project
     * The authenticated user must have REPO_READ permission for the specified project
     * @param projectKey project key
     * @param range limit object
     * @return a Page of repositories
     */
    Page<Repository> getProjectRepositories(@Nonnull String projectKey, @Nonnull Range range);

    Set<Repository> getAllProjectRepositories(@Nonnull String projectKey);
    /**
     * Returns a page of repositories (between Limit.start and Limit.end)
     * @param range limit object
     * @return a Page of repositories
     */
    Page<Repository> getAllRepositories(@Nonnull Range range);

    Set<Repository> getAllRepositories();

    /**
     * Returns a repository specified by its project key/repository slug
     * @param projectKey project key
     * @param repositorySlug repository slug
     * @return a Page of repositories
     */
    Optional<Repository> getRepositoryBySlug(@Nonnull String projectKey, @Nonnull String repositorySlug);

    /**
     * Returns a page of branches (between Limit.start and Limit.end)
     * @param projectKey project key
     * @param repositorySlug repository slug
     * @param query optional parameter. If not null it will return the braches matching this parameter
     * @param range limit object
     * @return a Page of repositories
     */
    Page<Branch> getRepositoryBranches(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nullable String query, @Nonnull Range range);

    Set<Branch> getAllRepositoryBranches(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nullable String query);

    /**
     * Returns a page of forks (between Limit.start and Limit.end) associated with a repository
     * @param projectKey project key
     * @param repositorySlug repository slug
     * @param range limit object
     * @return a Page of forks
     */
    Page<Repository> getRepositoryForks(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nonnull Range range);

    Set<Repository> getAllRepositoryForks(@Nonnull String projectKey, @Nonnull String repositorySlug);

    /**
     * Returns the default branch for a repository
     * @param projectKey project key
     * @param repositorySlug repository slug
     * @return Optional object wrapping the branch
     */
    Optional<Branch> getRepositoryDefaultBranch(@Nonnull String projectKey, @Nonnull String repositorySlug);

    /**
     * Returns a page of pull requests (between Limit.start and Limit.end) associated with a project/repository, with the specified state
     * @param projectKey project key
     * @param repositorySlug repository slug
     * @param pullRequestState PullRequestState
     * @param range limit object
     * @return a Page of PullRequests
     */
    Page<PullRequest> getRepositoryPullRequests(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nonnull PullRequestState pullRequestState,@Nonnull Range range);

    Set<PullRequest> getAllRepositoryPullRequests(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nonnull PullRequestState pullRequestState);
    /**
     * Returns a page of changes (between Limit.start and Limit.end) associated with a project/repository/pull request
     * @param projectKey project key
     * @param repositorySlug repository slug
     * @param pullRequestId pull request id
     * @param sinceCommitId the commit to which until should be compared to produce a page of changes. If not specified the commit's first parent is assumed
     * @param range limit object
     * @return a Page of PullRequestChange objects
     */
    Page<PullRequestChange> getRepositoryPullRequestsChanges(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nonnull Long pullRequestId,String sinceCommitId,@Nonnull Range range);

    Set<PullRequestChange> getAllRepositoryPullRequestsChanges(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nonnull Long pullRequestId,String sinceCommitId);

    /**
     * Returns a page of activities (between Limit.start and Limit.end) associated with a project/repository/pull request
     * @param projectKey project key
     * @param repositorySlug repository slug
     * @param pullRequestId pull request id
     * @param range limit object
     * @return a Page of PullRequestActivity objects
     */
    Page<PullRequestActivity> getRepositoryPullRequestsActivities(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nonnull Long pullRequestId,@Nonnull Range range);

    Set<PullRequestActivity> getAllRepositoryPullRequestsActivities(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nonnull Long pullRequestId);

    /**
     * Returns a page of tasks (between Limit.start and Limit.end) associated with a project/repository/pull request
     * @param projectKey project key
     * @param repositorySlug repository slug
     * @param pullRequestId pull request id
     * @param range limit object
     * @return a Page of Task objects
     */
    Page<Task> getRepositoryPullRequestsTasks(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nonnull Long pullRequestId,@Nonnull Range range);

    Set<Task> getAllRepositoryPullRequestsTasks(@Nonnull String projectKey, @Nonnull String repositorySlug, @Nonnull Long pullRequestId);

    /**
     * Returns a page of users (between Limit.start and Limit.end)
     * @param range limit object
     * @return a Page of User objects
     */
    Page<User> getUsers(@Nonnull Range range);

    Set<User> getAllUsers();


    /**
     * Retrieve version information and other application properties
     * @return map of properties
     */
    ImmutableMap<String, String> getBitBucketApplicationProperties();

    Project createProject(@Nonnull String projectKey, @Nonnull String name, @Nonnull ProjectType type, String description);
    Repository createRepository(@Nonnull String projectKey, @Nonnull String name, @Nonnull String scmId, boolean forkable);
    void deleteProject(@Nonnull String projectKey);
    void deleteRepository(@Nonnull String projectKey, @Nonnull String repositorySlug);

}
