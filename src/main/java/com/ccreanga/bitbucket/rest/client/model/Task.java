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
import java.util.Date;
import java.util.Objects;

public class Task implements Serializable {

    private long id;
    private TaskState taskState;
    private String text;
    private Date created;
    private Comment comment;
    private User user;
    private TaskOperations taskOperations;

    private Task() {
    }

    public Task(long id, TaskState taskState, String text, Date created, Comment comment, User user, TaskOperations taskOperations) {
        this.id = id;
        this.taskState = taskState;
        this.text = text;
        this.created = created;
        this.comment = comment;
        this.user = user;
        this.taskOperations = taskOperations;
    }

    public long getId() {
        return id;
    }

    public TaskState getTaskState() {
        return taskState;
    }

    public String getText() {
        return text;
    }

    public Date getCreated() {
        return created;
    }

    public Comment getComment() {
        return comment;
    }

    public User getUser() {
        return user;
    }

    public TaskOperations getTaskOperations() {
        return taskOperations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) &&
                Objects.equals(taskState, task.taskState) &&
                Objects.equals(text, task.text) &&
                Objects.equals(created, task.created) &&
                Objects.equals(comment, task.comment) &&
                Objects.equals(user, task.user) &&
                Objects.equals(taskOperations, task.taskOperations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskState, text, created, comment, user, taskOperations);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskState=" + taskState +
                ", text='" + text + '\'' +
                ", created=" + created +
                ", comment=" + comment +
                ", user=" + user +
                ", taskOperations=" + taskOperations +
                '}';
    }
}
