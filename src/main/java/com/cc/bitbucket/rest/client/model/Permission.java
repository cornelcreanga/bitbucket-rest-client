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

package com.cc.bitbucket.rest.client.model;

/**
 * com.cc.stash.rest.client.domain.Repository permissions available in Stash
 */
public enum Permission {
    /**
     * Allows read access to a repository.
     * <p/>
     * This allows cloning and pulling changes from a repository, adding comments and declining pull requests
     * that target the repository. It also allows creating pull requests if the user has the permission
     * on <em>both</em> the source and target repository.
     * <p/>
     * See com.atlassian.stash.user.com.cc.stash.rest.client.domain.Permission.REPO_READ
     */
    REPO_READ,
    /**
     * Allows write access to a repository.
     * <p/>
     * In addition to the permissions already granted by {@link #REPO_READ}, this allows pushing changes
     * to a repository and merging pull requests targeting the repository.
     * <p/>
     * See com.atlassian.stash.user.com.cc.stash.rest.client.domain.Permission.REPO_WRITE
     */
    REPO_WRITE,
    /**
     * Allows to administer a repository.
     * <p/>
     * In addition to the permissions already granted by {@link #REPO_WRITE}, this allows accessing and updating
     * the configuration of the repository, such as adding or revoking branch permissions, adding or revoking other
     * repository permissions, renaming or deleting the repository.
     * <p/>
     * See com.atlassian.stash.user.com.cc.stash.rest.client.domain.Permission.REPO_ADMIN
     */
    REPO_ADMIN,
}
