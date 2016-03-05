package com.ccreanga.bitbucket.rest.client.model.pull.activity;

import com.ccreanga.bitbucket.rest.client.model.User;

import java.util.Date;
import java.util.Objects;

public class PullRequestReOpenedActivity extends PullRequestActivity{
    protected Long id;
    protected Date createdDate;
    protected User user;
    protected long pullRequestId;
    protected PullRequestActivityActionType actionType;

    protected PullRequestReOpenedActivity() {
    }

    public PullRequestReOpenedActivity(Long id, Date createdDate, User user, long pullRequestId) {
        super(id, createdDate, user, pullRequestId, PullRequestActivityActionType.REOPENED);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PullRequestReOpenedActivity that = (PullRequestReOpenedActivity) o;

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
        return "PullRequestReOpenedActivity{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", user=" + user +
                ", pullRequestId=" + pullRequestId +
                ", actionType=" + actionType +
                '}';
    }
}
