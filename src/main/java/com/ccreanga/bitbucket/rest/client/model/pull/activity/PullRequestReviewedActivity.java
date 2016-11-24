package com.ccreanga.bitbucket.rest.client.model.pull.activity;

import com.ccreanga.bitbucket.rest.client.model.User;

import java.util.Date;

public class PullRequestReviewedActivity extends PullRequestActivity {

    public PullRequestReviewedActivity() {
    }

    public PullRequestReviewedActivity(Long id, Date createdDate, User user, long pullRequestId) {
        super(id, createdDate, user, pullRequestId, PullRequestActivityActionType.REVIEWED);
    }
}
