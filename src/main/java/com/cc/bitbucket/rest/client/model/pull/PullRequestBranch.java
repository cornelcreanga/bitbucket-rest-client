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

package com.cc.bitbucket.rest.client.model.pull;

import java.util.Objects;

public class PullRequestBranch {

    private String id;
    private String repositorySlug;
    private String repositoryName;
    private String projectKey;

    public PullRequestBranch(String id, String repositorySlug, String repositoryName, String projectKey) {
        this.id = id;
        this.repositorySlug = repositorySlug;
        this.repositoryName = repositoryName;
        this.projectKey = projectKey;
    }

    public String getId() {
        return id;
    }

    public String getRepositorySlug() {
        return repositorySlug;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public String getProjectKey() {
        return projectKey;
    }

    @Override
    public String toString() {
        return "PullRequestBranch{" +
                "id='" + id + '\'' +
                ", repositorySlug='" + repositorySlug + '\'' +
                ", repositoryName='" + repositoryName + '\'' +
                ", projectKey='" + projectKey + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PullRequestBranch that = (PullRequestBranch) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(repositorySlug, that.repositorySlug) &&
                Objects.equals(repositoryName, that.repositoryName) &&
                Objects.equals(projectKey, that.projectKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, repositorySlug, repositoryName, projectKey);
    }
}
