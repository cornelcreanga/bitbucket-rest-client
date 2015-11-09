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

import com.google.common.collect.ImmutableList;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Comment implements Serializable {

    private long id;
    private long version;
    private String text;
    private User author;
    private Date created;
    private Date updated;
    private List<Comment> comments;
    private PermittedOperations permittedOperations;

    public Comment(long id, long version, String text, User author, Date created, Date updated,List<Comment> comments,PermittedOperations permittedOperations) {
        this.id = id;
        this.version = version;
        this.text = text;
        this.author = author;
        this.created = created;
        this.updated = updated;
        this.comments = ImmutableList.copyOf(comments);
        this.permittedOperations = permittedOperations;
    }

    public long getId() {
        return id;
    }

    public long getVersion() {
        return version;
    }

    public String getText() {
        return text;
    }

    public User getAuthor() {
        return author;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public PermittedOperations getPermittedOperations() {
        return permittedOperations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) &&
                Objects.equals(version, comment.version) &&
                Objects.equals(text, comment.text) &&
                Objects.equals(author, comment.author) &&
                Objects.equals(created, comment.created) &&
                Objects.equals(updated, comment.updated) &&
                Objects.equals(comments, comment.comments) &&
                Objects.equals(permittedOperations, comment.permittedOperations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, text, author, created, updated, comments, permittedOperations);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", version=" + version +
                ", text='" + text + '\'' +
                ", author=" + author +
                ", created=" + created +
                ", updated=" + updated +
                ", comments=" + comments +
                ", permittedOperations=" + permittedOperations +
                '}';
    }
}
