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

import com.ccreanga.bitbucket.rest.client.model.User;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class PullRequestApprovedActivity extends PullRequestActivity{

    protected PullRequestApprovedActivity() {
    }

    public PullRequestApprovedActivity(Long id, Date createdDate, User user, long pullRequestId) {
        super(id, createdDate, user, pullRequestId, PullRequestActivityActionType.APPROVED);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PullRequestApprovedActivity that = (PullRequestApprovedActivity) o;

        if (pullRequestId != that.pullRequestId) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        return actionType == that.actionType;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (int) (pullRequestId ^ (pullRequestId >>> 32));
        result = 31 * result + (actionType != null ? actionType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PullRequestApprovedActivity{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", user=" + user +
                ", pullRequestId=" + pullRequestId +
                ", actionType=" + actionType +
                '}';
    }
}
