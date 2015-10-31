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

package com.cc.bitbucket.rest.client;

import com.cc.bitbucket.rest.client.model.*;
import com.cc.bitbucket.rest.client.model.pull.PullRequest;
import com.cc.bitbucket.rest.client.model.pull.activity.PullRequestActivity;
import com.cc.bitbucket.rest.client.model.pull.PullRequestChange;
import com.cc.bitbucket.rest.client.model.pull.PullRequestState;
import com.google.common.collect.ImmutableMap;

import java.util.Optional;

public interface ProjectClient {
    Page<Project> getProjects(Limit limit);

    Optional<Project> getProjectByKey(String projectKey);

    Page<Repository> getProjectRepositories(String projectKey, Limit limit);

    Page<Repository> getAllRepositories(Limit limit);

    Optional<Repository> getRepositoryBySlug(String projectKey, String repositorySlug);

    Page<Branch> getRepositoryBranches(String projectKey, String repositorySlug, String query, Limit limit);

    Page<Repository> getRepositoryForks(String projectKey, String repositorySlug, Limit limit);

    Optional<Branch> getRepositoryDefaultBranch(String projectKey, String repositorySlug);

    Page<PullRequest> getRepositoryPullRequests(String projectKey, String repositorySlug, PullRequestState pullRequestState,Limit limit);

    Page<PullRequestChange> getRepositoryPullRequestsChanges(String projectKey, String repositorySlug, Long pullRequestId,Limit limit);

    Page<PullRequestActivity> getRepositoryPullRequestsActivities(String projectKey, String repositorySlug, Long pullRequestId,Limit limit);

    Page<Task> getRepositoryPullRequestsTasks(String projectKey, String repositorySlug, Long pullRequestId,Limit limit);

    Page<User> getUsers(Limit limit);

    ImmutableMap<String, String> getBitBucketApplicationProperties();
}
