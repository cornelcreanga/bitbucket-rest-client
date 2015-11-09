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

package com.ccreanga.bitbucket.rest.client.model.pull;

import com.google.common.collect.ImmutableList;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PullRequest  implements Serializable {

    private long id;
    private long version;
    @Nonnull
    private String title;
    private String description;
    @Nonnull
    private PullRequestState state;
    private boolean open;
    private boolean closed;
    @Nonnull
    private Date createdDate;
    @Nonnull
    private Date updatedDate;
    @Nonnull
    private PullRequestBranch from;
    @Nonnull
    private PullRequestBranch to;
    private boolean locked;
    @Nonnull
    private PullRequestParticipant author;
    @Nonnull
    private List<PullRequestParticipant> reviewers;
    @Nonnull
    private List<PullRequestParticipant> participants;

    private String selfUrl;

    public PullRequest(long id, long version, String title, String description, PullRequestState state,
                       boolean open, boolean closed, Date createdDate, Date updatedDate, PullRequestBranch from, PullRequestBranch to, boolean locked,
                       PullRequestParticipant author, List<PullRequestParticipant> reviewers, List<PullRequestParticipant> participants,
                       String selfUrl) {
        this.id = id;
        this.version = version;
        this.title = title;
        this.description = description;
        this.state = state;
        this.open = open;
        this.closed = closed;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.from = from;
        this.to = to;
        this.locked = locked;
        this.author = author;
        this.reviewers = ImmutableList.copyOf(reviewers);
        this.participants = ImmutableList.copyOf(participants);
        this.selfUrl = selfUrl;
    }

    public long getId() {
        return id;
    }

    public long getVersion() {
        return version;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public PullRequestState getState() {
        return state;
    }

    public boolean isOpen() {
        return open;
    }

    public boolean isClosed() {
        return closed;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public PullRequestBranch getFrom() {
        return from;
    }

    public PullRequestBranch getTo() {
        return to;
    }

    public boolean isLocked() {
        return locked;
    }

    public PullRequestParticipant getAuthor() {
        return author;
    }

    public List<PullRequestParticipant> getReviewers() {
        return reviewers;
    }

    public List<PullRequestParticipant> getParticipants() {
        return participants;
    }

    public String getSelfUrl() {
        return selfUrl;
    }

    @Override
    public String toString() {
        return "PullRequest{" +
                "id=" + id +
                ", version=" + version +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                ", open=" + open +
                ", closed=" + closed +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", from=" + from +
                ", to=" + to +
                ", locked=" + locked +
                ", author=" + author +
                ", reviewers=" + reviewers +
                ", participants=" + participants +
                ", selfUrl='" + selfUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PullRequest that = (PullRequest) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(version, that.version) &&
                Objects.equals(open, that.open) &&
                Objects.equals(closed, that.closed) &&
                Objects.equals(locked, that.locked) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(state, that.state) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(updatedDate, that.updatedDate) &&
                Objects.equals(from, that.from) &&
                Objects.equals(to, that.to) &&
                Objects.equals(author, that.author) &&
                Objects.equals(reviewers, that.reviewers) &&
                Objects.equals(participants, that.participants) &&
                Objects.equals(selfUrl, that.selfUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, title, description, state, open, closed, createdDate, updatedDate, from, to, locked, author, reviewers, participants, selfUrl);
    }
}
