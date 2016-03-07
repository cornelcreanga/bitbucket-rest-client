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

import com.ccreanga.bitbucket.rest.client.model.Comment;
import com.ccreanga.bitbucket.rest.client.model.CommentAnchor;
import com.ccreanga.bitbucket.rest.client.model.diff.Diff;
import com.ccreanga.bitbucket.rest.client.model.User;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class PullRequestCommentActivity extends PullRequestActivity{

    private String actionComment;
    private Comment comment;
    private CommentAnchor commentAnchor;
    private Diff diff;

    protected PullRequestCommentActivity() {
    }

    public PullRequestCommentActivity(Long id, Date createdDate, User user,long pullRequestId, String actionComment, Comment comment, CommentAnchor commentAnchor, Diff diff) {
        this.actionComment = actionComment;
        this.comment = comment;
        this.commentAnchor = commentAnchor;
        this.id = id;
        this.createdDate = createdDate;
        this.user = user;
        this.pullRequestId = pullRequestId;
        this.diff = diff;
        this.actionType = PullRequestActivityActionType.COMMENTED;
    }

    public String getActionComment() {
        return actionComment;
    }

    public Comment getComment() {
        return comment;
    }

    public CommentAnchor getCommentAnchor() {
        return commentAnchor;
    }

    public Diff getDiff() {
        return diff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PullRequestCommentActivity that = (PullRequestCommentActivity) o;

        if (pullRequestId != that.pullRequestId) return false;
        if (actionComment != null ? !actionComment.equals(that.actionComment) : that.actionComment != null)
            return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (commentAnchor != null ? !commentAnchor.equals(that.commentAnchor) : that.commentAnchor != null)
            return false;
        if (diff != null ? !diff.equals(that.diff) : that.diff != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        return actionType == that.actionType;

    }

    @Override
    public int hashCode() {
        int result = actionComment != null ? actionComment.hashCode() : 0;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (commentAnchor != null ? commentAnchor.hashCode() : 0);
        result = 31 * result + (diff != null ? diff.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (int) (pullRequestId ^ (pullRequestId >>> 32));
        result = 31 * result + (actionType != null ? actionType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PullRequestCommentActivity{" +
                "actionComment='" + actionComment + '\'' +
                ", comment=" + comment +
                ", commentAnchor=" + commentAnchor +
                ", diff=" + diff +
                ", id=" + id +
                ", createdDate=" + createdDate +
                ", user=" + user +
                ", pullRequestId=" + pullRequestId +
                ", actionType=" + actionType +
                '}';
    }
}
