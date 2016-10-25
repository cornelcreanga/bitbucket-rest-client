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

package com.ccreanga.bitbucket.rest.client.model;

import com.google.gson.JsonObject;

import java.io.Serializable;

public class Branch implements Serializable {
    private String id;
    private String displayId;
    private String latestChangeset;
    private boolean isDefault;

    private String repositorySlug;
    private String projectKey;
    private JsonObject metadata;

    private Branch() {
    }

    public Branch(String id, String displayId, String latestChangeset, boolean isDefault) {
        this.id = id;
        this.displayId = displayId;
        this.latestChangeset = latestChangeset;
        this.isDefault = isDefault;
    }

    public String getId() {
        return id;
    }

    public String getDisplayId() {
        return displayId;
    }

    public String getLatestChangeset() {
        return latestChangeset;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public String getRepositorySlug() {
        return repositorySlug;
    }

    public void setRepositorySlug(String repositorySlug) {
        this.repositorySlug = repositorySlug;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    public JsonObject getMetadata() {
        return metadata;
    }

    public void setMetadata(JsonObject metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id='" + id + '\'' +
                ", displayId='" + displayId + '\'' +
                ", latestChangeset='" + latestChangeset + '\'' +
                ", isDefault=" + isDefault +
                ", repositorySlug='" + repositorySlug + '\'' +
                ", projectKey='" + projectKey + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Branch branch = (Branch) o;
        return java.util.Objects.equals(isDefault, branch.isDefault) &&
                java.util.Objects.equals(id, branch.id) &&
                java.util.Objects.equals(displayId, branch.displayId) &&
                java.util.Objects.equals(latestChangeset, branch.latestChangeset) &&
                java.util.Objects.equals(repositorySlug, branch.repositorySlug) &&
                java.util.Objects.equals(projectKey, branch.projectKey);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, displayId, latestChangeset, isDefault, repositorySlug, projectKey);
    }
}
