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

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * Describes a repository
 */
public class Repository {
    private String hierarchyId;
    private long id;
    private String slug;
    @Nonnull
    private String name;
    private boolean isPublic;
    private boolean isFork;
    private boolean isForkable;
    private String sshCloneUrl;
    private String httpCloneUrl;
    private String selfUrl;
    @Nonnull
    private Project project;
    private Repository origin;
    @Nonnull
    private String scmId;
    @Nonnull
    private RepositoryState state;
    @Nonnull
    private String statusMessage;

    public Repository(String hierarchyId, long id, String slug, @Nonnull String name, boolean isPublic, boolean isFork, boolean isForkable,
                      String sshCloneUrl, String httpCloneUrl, String selfUrl, @Nonnull Project project, Repository origin,
                      @Nonnull String scmId, @Nonnull RepositoryState state, @Nonnull String statusMessage) {
        this.hierarchyId = hierarchyId;
        this.id = id;
        this.slug = slug;
        this.name = name;
        this.isPublic = isPublic;
        this.isFork = isFork;
        this.isForkable = isForkable;
        this.sshCloneUrl = sshCloneUrl;
        this.httpCloneUrl = httpCloneUrl;
        this.selfUrl = selfUrl;
        this.project = project;
        this.origin = origin;
        this.scmId = scmId;
        this.state = state;
        this.statusMessage = statusMessage;
    }

    public String getHierarchyId() {
        return hierarchyId;
    }

    public long getId() {
        return id;
    }

    public String getSlug() {
        return slug;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public boolean isFork() {
        return isFork;
    }

    public boolean isForkable() {
        return isForkable;
    }

    public String getSshCloneUrl() {
        return sshCloneUrl;
    }

    public String getHttpCloneUrl() {
        return httpCloneUrl;
    }

    public String getSelfUrl() {
        return selfUrl;
    }

    @Nonnull
    public Project getProject() {
        return project;
    }

    public Repository getOrigin() {
        return origin;
    }

    @Nonnull
    public String getScmId() {
        return scmId;
    }

    @Nonnull
    public RepositoryState getState() {
        return state;
    }

    @Nonnull
    public String getStatusMessage() {
        return statusMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repository that = (Repository) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(isPublic, that.isPublic) &&
                Objects.equals(isFork, that.isFork) &&
                Objects.equals(isForkable, that.isForkable) &&
                Objects.equals(hierarchyId, that.hierarchyId) &&
                Objects.equals(slug, that.slug) &&
                Objects.equals(name, that.name) &&
                Objects.equals(sshCloneUrl, that.sshCloneUrl) &&
                Objects.equals(httpCloneUrl, that.httpCloneUrl) &&
                Objects.equals(selfUrl, that.selfUrl) &&
                Objects.equals(project, that.project) &&
                Objects.equals(origin, that.origin) &&
                Objects.equals(scmId, that.scmId) &&
                Objects.equals(state, that.state) &&
                Objects.equals(statusMessage, that.statusMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hierarchyId, id, slug, name, isPublic, isFork, isForkable, sshCloneUrl, httpCloneUrl, selfUrl, project, origin, scmId, state, statusMessage);
    }

    @Override
    public String toString() {
        return "Repository{" +
                "hierarchyId='" + hierarchyId + '\'' +
                ", id=" + id +
                ", slug='" + slug + '\'' +
                ", name='" + name + '\'' +
                ", isPublic=" + isPublic +
                ", isFork=" + isFork +
                ", isForkable=" + isForkable +
                ", sshCloneUrl='" + sshCloneUrl + '\'' +
                ", httpCloneUrl='" + httpCloneUrl + '\'' +
                ", selfUrl='" + selfUrl + '\'' +
                ", project=" + project +
                ", origin=" + origin +
                ", scmId='" + scmId + '\'' +
                ", state=" + state +
                ", statusMessage='" + statusMessage + '\'' +
                '}';
    }
}
