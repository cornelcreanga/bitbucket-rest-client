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
                JsonObject addedJson= json.get("added").getAsJsonObject();
                if (addedJson.has("changesets")) {
                    List<Commit> changesets = listParser(commitParser()).apply(addedJson.get("changesets"));
                    added.addAll(changesets);
                }

                if (addedJson.has("commits")) {
                    List<Commit> changesets = listParser(commitParser()).apply(addedJson.get("commits"));
                    added.addAll(changesets);
                }
            }
            if (json.has("removed")) {
                JsonObject removedJson= json.get("removed").getAsJsonObject();
                if (removedJson.has("changesets")) {
                    List<Commit> changesets = listParser(commitParser()).apply(removedJson.get("changesets"));
                    removed.addAll(changesets);
                }
                if (removedJson.has("commits")) {
                    List<Commit> changesets = listParser(commitParser()).apply(removedJson.get("commits"));
                    removed.addAll(changesets);
                }
            }
            return new PullRequestRescopeActivity(
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
            return new PullRequestMergeActivity(
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
        } else
            throw new RuntimeException("cannot parse action:" + actionType);

    }
}
