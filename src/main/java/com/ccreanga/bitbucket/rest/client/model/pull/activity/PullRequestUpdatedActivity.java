package com.ccreanga.bitbucket.rest.client.model.pull.activity;

import com.ccreanga.bitbucket.rest.client.model.User;

import java.util.Date;

public class PullRequestUpdatedActivity extends PullRequestActivity {

    public PullRequestUpdatedActivity() {
    }

    public PullRequestUpdatedActivity(Long id, Date createdDate, User user, long pullRequestId) {
        super(id, createdDate, user, pullRequestId, PullRequestActivityActionType.UPDATED);
    }
}
