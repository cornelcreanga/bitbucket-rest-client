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

    public PullRequestCommentActivity(Long id, Date createdDate, User user, String actionComment, Comment comment, CommentAnchor commentAnchor, Diff diff) {
        this.actionComment = actionComment;
        this.comment = comment;
        this.commentAnchor = commentAnchor;
        this.id = id;
        this.createdDate = createdDate;
        this.user = user;
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
        return Objects.equals(actionComment, that.actionComment) &&
                Objects.equals(comment, that.comment) &&
                Objects.equals(commentAnchor, that.commentAnchor) &&
                Objects.equals(diff, that.diff);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actionComment, comment, commentAnchor, diff);
    }

    @Override
    public String toString() {
        return "PullRequestCommentActivity{" +
                "actionComment='" + actionComment + '\'' +
                ", comment=" + comment +
                ", commentAnchor=" + commentAnchor +
                ", diff=" + diff +
                '}';
    }
}
