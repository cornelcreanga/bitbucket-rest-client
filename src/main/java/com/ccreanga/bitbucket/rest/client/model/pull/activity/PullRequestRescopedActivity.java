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

public class PullRequestRescopedActivity extends PullRequestActivity{

    private String fromHash;
    private String previousFromHash;
    private String previousToHash;
    private String toHash;

    private List<Commit> added;
    private List<Commit> removed;

    protected PullRequestRescopedActivity() {
    }

    public PullRequestRescopedActivity(Long id, Date createdDate, User user, Long pullRequestId, String fromHash, String previousFromHash, String previousToHash, String toHash, List<Commit> added, List<Commit> removed) {
        this.id = id;
        this.createdDate = createdDate;
        this.user = user;
        this.pullRequestId = pullRequestId;
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

        if (pullRequestId != that.pullRequestId) return false;
        if (fromHash != null ? !fromHash.equals(that.fromHash) : that.fromHash != null) return false;
        if (previousFromHash != null ? !previousFromHash.equals(that.previousFromHash) : that.previousFromHash != null)
            return false;
        if (previousToHash != null ? !previousToHash.equals(that.previousToHash) : that.previousToHash != null)
            return false;
        if (toHash != null ? !toHash.equals(that.toHash) : that.toHash != null) return false;
        if (added != null ? !added.equals(that.added) : that.added != null) return false;
        if (removed != null ? !removed.equals(that.removed) : that.removed != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        return actionType == that.actionType;

    }

    @Override
    public int hashCode() {
        int result = fromHash != null ? fromHash.hashCode() : 0;
        result = 31 * result + (previousFromHash != null ? previousFromHash.hashCode() : 0);
        result = 31 * result + (previousToHash != null ? previousToHash.hashCode() : 0);
        result = 31 * result + (toHash != null ? toHash.hashCode() : 0);
        result = 31 * result + (added != null ? added.hashCode() : 0);
        result = 31 * result + (removed != null ? removed.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (int) (pullRequestId ^ (pullRequestId >>> 32));
        result = 31 * result + (actionType != null ? actionType.hashCode() : 0);
        return result;
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
                ", pullRequestId=" + pullRequestId +
                ", actionType=" + actionType +
                '}';
    }
}
