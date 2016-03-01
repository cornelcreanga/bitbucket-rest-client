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

package com.ccreanga.bitbucket.rest.client.http.responseparsers;

import com.ccreanga.bitbucket.rest.client.model.Commit;
import com.ccreanga.bitbucket.rest.client.model.pull.activity.*;
import java.util.function.Function;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.ccreanga.bitbucket.rest.client.http.responseparsers.Parsers.*;

public class PullRequestActivityParser implements Function<JsonElement, PullRequestActivity> {
    @Override
    public PullRequestActivity apply(JsonElement jsonElement) {
        if (jsonElement == null || !jsonElement.isJsonObject()) {
            return null;
        }
        JsonObject json = jsonElement.getAsJsonObject();

        PullRequestActivityActionType actionType = PullRequestActivityActionType.valueOf(json.get("action").getAsString());
        if (actionType == PullRequestActivityActionType.COMMENTED) {
            return new PullRequestCommentActivity(
                    json.get("id").getAsLong(),
                    new Date(json.get("createdDate").getAsLong()),
                    userParser().apply(json.getAsJsonObject("user")),
                    json.get("commentAction").getAsString(),
                    commentParser().apply(json.getAsJsonObject("comment")),
                    commentAnchorParser().apply(json.getAsJsonObject("commentAnchor")),
                    json.has("diff")?diffParser().apply(json.getAsJsonObject("diff")):null

            );
        } else if (actionType == PullRequestActivityActionType.RESCOPED) {
            List<Commit> added = new ArrayList<>();
            List<Commit> removed = new ArrayList<>();
            if (json.has("added")) {
                added = getCommits(json.get("added").getAsJsonObject());
            }
            if (json.has("removed")) {
                removed = getCommits(json.get("removed").getAsJsonObject());
            }

            return new PullRequestRescopedActivity(
                    json.get("id").getAsLong(),
                    new Date(json.get("createdDate").getAsLong()),
                    userParser().apply(json.getAsJsonObject("user")),
                    json.get("fromHash").getAsString(),
                    json.get("previousFromHash").getAsString(),
                    json.get("previousToHash").getAsString(),
                    json.get("toHash").getAsString(),
                    added,
                    removed
            );

        }
        else if (actionType == PullRequestActivityActionType.MERGED) {
            return new PullRequestMergedActivity(
                    json.get("id").getAsLong(),
                    new Date(json.get("createdDate").getAsLong()),
                    userParser().apply(json.getAsJsonObject("user"))
            );
        }
        else if (actionType == PullRequestActivityActionType.APPROVED) {
            return new PullRequestApprovedActivity(
                    json.get("id").getAsLong(),
                    new Date(json.get("createdDate").getAsLong()),
                    userParser().apply(json.getAsJsonObject("user"))
            );
        }
        else if (actionType == PullRequestActivityActionType.DECLINED) {
            return new PullRequestDeclinedActivity(
                    json.get("id").getAsLong(),
                    new Date(json.get("createdDate").getAsLong()),
                    userParser().apply(json.getAsJsonObject("user"))
            );
        }
        else if (actionType == PullRequestActivityActionType.UNAPPROVED) {
            return new PullRequestUnapprovedActivity(
                    json.get("id").getAsLong(),
                    new Date(json.get("createdDate").getAsLong()),
                    userParser().apply(json.getAsJsonObject("user"))
            );
        }
        else if (actionType == PullRequestActivityActionType.OPENED) {
            return new PullRequestOpenedActivity(
                    json.get("id").getAsLong(),
                    new Date(json.get("createdDate").getAsLong()),
                    userParser().apply(json.getAsJsonObject("user"))
            );
        }
        else if (actionType == PullRequestActivityActionType.REOPENED) {
            return new PullRequestReOpenedActivity(
                    json.get("id").getAsLong(),
                    new Date(json.get("createdDate").getAsLong()),
                    userParser().apply(json.getAsJsonObject("user"))
            );
        }  else
            throw new RuntimeException("cannot parse action:" + actionType);

    }


    private List<Commit> getCommits(JsonObject json){
        List<Commit> commits = new ArrayList<>();
        if (json.has("changesets")) {
            commits.addAll(listParser(commitParser()).apply(json.get("changesets")));
        }
        if (json.has("commits")) {
            commits.addAll(listParser(commitParser()).apply(json.get("commits")));
        }
        return commits;
    }
}
