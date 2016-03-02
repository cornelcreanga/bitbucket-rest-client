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

public abstract class PullRequestActivity implements Serializable {

    protected Long id;
    protected Date createdDate;
    protected User user;
    protected PullRequestActivityActionType actionType;

    protected PullRequestActivity() {
    }

    public Long getId() {
        return id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public User getUser() {
        return user;
    }

    public PullRequestActivityActionType getActionType() {
        return actionType;
    }

    public boolean isComment(){
        return  actionType.equals(PullRequestActivityActionType.COMMENTED);
    }
    public boolean isRescope(){
        return  actionType.equals(PullRequestActivityActionType.RESCOPED);
    }
    public boolean isMerge(){
        return  actionType.equals(PullRequestActivityActionType.MERGED);
    }
    public boolean isApprove(){
        return  actionType.equals(PullRequestActivityActionType.APPROVED);
    }
    public boolean isDecline(){
        return  actionType.equals(PullRequestActivityActionType.DECLINED);
    }
    public boolean isOpen(){
        return  actionType.equals(PullRequestActivityActionType.OPENED);
    }
    public boolean isReopen(){
        return  actionType.equals(PullRequestActivityActionType.REOPENED);
    }
    public boolean isUnapprove(){
        return  actionType.equals(PullRequestActivityActionType.UNAPPROVED);
    }
    public boolean isUpdate(){
        return  actionType.equals(PullRequestActivityActionType.UPDATED);
    }

}
