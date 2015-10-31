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

package com.cc.bitbucket.rest.client.model.pull;

import com.cc.bitbucket.rest.client.model.User;

public class PullRequestParticipant {

    private User user;
    private PullRequestRole role;
    private boolean approved;

    public PullRequestParticipant(User user, PullRequestRole role, boolean approved) {
        this.user = user;
        this.role = role;
        this.approved = approved;
    }

    public User getUser() {
        return user;
    }

    public PullRequestRole getRole() {
        return role;
    }

    public boolean isApproved() {
        return approved;
    }

    @Override
    public String toString() {
        return "PullRequestParticipant{" +
                "user=" + user +
                ", role='" + role + '\'' +
                ", approved=" + approved +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PullRequestParticipant that = (PullRequestParticipant) o;
        return java.util.Objects.equals(approved, that.approved) &&
                java.util.Objects.equals(user, that.user) &&
                java.util.Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(user, role, approved);
    }
}
