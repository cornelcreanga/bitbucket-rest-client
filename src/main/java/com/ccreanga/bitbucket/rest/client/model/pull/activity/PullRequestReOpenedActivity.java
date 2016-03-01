package com.ccreanga.bitbucket.rest.client.model.pull.activity;

import com.ccreanga.bitbucket.rest.client.model.User;

import java.util.Date;
import java.util.Objects;

public class PullRequestReOpenedActivity extends PullRequestActivity{

    protected Long id;
    protected Date createdDate;
    protected User user;
    protected PullRequestActivityActionType actionType;

    public PullRequestReOpenedActivity(Long id, Date createdDate, User user) {
        this.id = id;
        this.createdDate = createdDate;
        this.user = user;
        this.actionType = PullRequestActivityActionType.OPENED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PullRequestReOpenedActivity that = (PullRequestReOpenedActivity) o;
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
        return "PullRequestReOpenedActivity{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", user=" + user +
                ", actionType=" + actionType +
                '}';
    }

}
