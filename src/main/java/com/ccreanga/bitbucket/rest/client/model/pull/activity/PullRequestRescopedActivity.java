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

package com.ccreanga.bitbucket.rest.client.model.pull.activity;

import com.ccreanga.bitbucket.rest.client.model.Commit;
import com.ccreanga.bitbucket.rest.client.model.User;
import com.google.common.collect.ImmutableList;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PullRequestRescopedActivity extends PullRequestActivity{

    private String fromHash;
    private String previousFromHash;
    private String previousToHash;
    private String toHash;

    private List<Commit> added;
    private List<Commit> removed;

    public PullRequestRescopedActivity(Long id, Date createdDate, User user, String fromHash, String previousFromHash, String previousToHash, String toHash, List<Commit> added, List<Commit> removed) {
        this.id = id;
        this.createdDate = createdDate;
        this.user = user;
        this.fromHash = fromHash;
        this.previousFromHash = previousFromHash;
        this.previousToHash = previousToHash;
        this.toHash = toHash;
        this.added = ImmutableList.copyOf(added);
        this.removed = ImmutableList.copyOf(removed);
        this.actionType = PullRequestActivityActionType.RESCOPED;
    }

    public String getFromHash() {
        return fromHash;
    }

    public String getPreviousFromHash() {
        return previousFromHash;
    }

    public String getPreviousToHash() {
        return previousToHash;
    }

    public String getToHash() {
        return toHash;
    }

    public List<Commit> getAdded() {
        return added;
    }

    public List<Commit> getRemoved() {
        return removed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PullRequestRescopedActivity that = (PullRequestRescopedActivity) o;
        return Objects.equals(fromHash, that.fromHash) &&
                Objects.equals(previousFromHash, that.previousFromHash) &&
                Objects.equals(previousToHash, that.previousToHash) &&
                Objects.equals(toHash, that.toHash) &&
                Objects.equals(added, that.added) &&
                Objects.equals(removed, that.removed) &&
                Objects.equals(id, that.id) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(user, that.user) &&
                Objects.equals(actionType, that.actionType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromHash, previousFromHash, previousToHash, toHash, added, removed, id, createdDate, user, actionType);
    }

    @Override
    public String toString() {
        return "PullRequestRescopedActivity{" +
                "fromHash='" + fromHash + '\'' +
                ", previousFromHash='" + previousFromHash + '\'' +
                ", previousToHash='" + previousToHash + '\'' +
                ", toHash='" + toHash + '\'' +
                ", added=" + added +
                ", removed=" + removed +
                ", id=" + id +
                ", createdDate=" + createdDate +
                ", user=" + user +
                ", actionType=" + actionType +
                '}';
    }
}
