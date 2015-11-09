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

import java.io.Serializable;

public class User implements Serializable {

    private long id;
    private String name;
    private String emailAddress;
    private String displayName;
    private boolean active;
    private String slug;
    private UserType type;

    public User(long id, String name, String emailAddress, String displayName, boolean active, String slug, UserType type) {
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
        this.displayName = displayName;
        this.active = active;
        this.slug = slug;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isActive() {
        return active;
    }

    public String getSlug() {
        return slug;
    }

    public UserType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", displayName='" + displayName + '\'' +
                ", active=" + active +
                ", slug='" + slug + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return java.util.Objects.equals(id, user.id) &&
                java.util.Objects.equals(active, user.active) &&
                java.util.Objects.equals(name, user.name) &&
                java.util.Objects.equals(emailAddress, user.emailAddress) &&
                java.util.Objects.equals(displayName, user.displayName) &&
                java.util.Objects.equals(slug, user.slug) &&
                java.util.Objects.equals(type, user.type);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, name, emailAddress, displayName, active, slug, type);
    }
}
