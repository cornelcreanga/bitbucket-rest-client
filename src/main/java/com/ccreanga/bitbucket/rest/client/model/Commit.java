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

package com.ccreanga.bitbucket.rest.client.model;

import com.google.common.collect.ImmutableList;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Commit implements Serializable {

    private String id;
    private String displayId;
    private String authorName;
    private String authorEmail;
    private long authorTimestamp;
    private String message;
    private List<MinimalCommit> parentsIds;

    public Commit(String id, String displayId, String authorName, String authorEmail, long authorTimestamp, String message, List<MinimalCommit> parentsIds) {
        this.id = id;
        this.displayId = displayId;
        this.authorName = authorName;
        this.authorEmail = authorEmail;
        this.authorTimestamp = authorTimestamp;
        this.message = message;
        this.parentsIds = ImmutableList.copyOf(parentsIds);
    }

    @Override
    public String toString() {
        return "Commit{" +
                "id='" + id + '\'' +
                ", displayId='" + displayId + '\'' +
                ", authorName='" + authorName + '\'' +
                ", authorEmail='" + authorEmail + '\'' +
                ", authorTimestamp=" + authorTimestamp +
                ", message='" + message + '\'' +
                ", parentsIds=" + parentsIds +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commit changeSet = (Commit) o;
        return Objects.equals(authorTimestamp, changeSet.authorTimestamp) &&
                Objects.equals(id, changeSet.id) &&
                Objects.equals(displayId, changeSet.displayId) &&
                Objects.equals(authorName, changeSet.authorName) &&
                Objects.equals(authorEmail, changeSet.authorEmail) &&
                Objects.equals(message, changeSet.message) &&
                Objects.equals(parentsIds, changeSet.parentsIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, displayId, authorName, authorEmail, authorTimestamp, message, parentsIds);
    }

}
