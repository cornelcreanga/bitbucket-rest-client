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

public class PullRequestDeclinedActivity extends PullRequestActivity{
    protected PullRequestDeclinedActivity() {
    }

    public PullRequestDeclinedActivity(Long id, Date createdDate, User user) {
        this.id = id;
        this.createdDate = createdDate;
        this.user = user;
        this.actionType = PullRequestActivityActionType.DECLINED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PullRequestDeclinedActivity that = (PullRequestDeclinedActivity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(user, that.user) &&
                Objects.equals(actionType, that.actionType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdDate, user, actionType);
    }

    @Override
    public String toString() {
        return "PullRequestDeclinedActivity{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", user=" + user +
                ", actionType=" + actionType +
                '}';
    }

}
