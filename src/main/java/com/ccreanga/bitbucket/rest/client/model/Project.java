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

import javax.annotation.Nullable;
import java.io.Serializable;

public class Project implements Serializable {
    private String key;
    private long id;
    private String name;
    @Nullable
    private String description;
    private boolean isPublic;
    private boolean isPersonal;
    private ProjectType type;
    private String selfUrl;

    private Project() {
    }

    public Project(String key, long id, String name, @Nullable String description, boolean isPublic, boolean isPersonal, ProjectType type, String selfUrl) {
        this.key = key;
        this.id = id;
        this.name = name;
        this.description = description;
        this.isPublic = isPublic;
        this.isPersonal = isPersonal;
        this.type = type;
        this.selfUrl = selfUrl;
    }

    public String getKey() {
        return key;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public boolean isPersonal() {
        return isPersonal;
    }

    /**
     * Retrieves the project's type. As of writing it may be NORMAL or PERSONAL
     *
     * @return the type for this project
     */
    public ProjectType getType() {
        return type;
    }

    public String getSelfUrl() {
        return selfUrl;
    }

    @Override
    public String toString() {
        return "Project{" +
                "key='" + key + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isPublic=" + isPublic +
                ", isPersonal=" + isPersonal +
                ", type='" + type + '\'' +
                ", selfUrl='" + selfUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return java.util.Objects.equals(id, project.id) &&
                java.util.Objects.equals(isPublic, project.isPublic) &&
                java.util.Objects.equals(isPersonal, project.isPersonal) &&
                java.util.Objects.equals(key, project.key) &&
                java.util.Objects.equals(name, project.name) &&
                java.util.Objects.equals(description, project.description) &&
                java.util.Objects.equals(type, project.type) &&
                java.util.Objects.equals(selfUrl, project.selfUrl);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(key, id, name, description, isPublic, isPersonal, type, selfUrl);
    }
}
