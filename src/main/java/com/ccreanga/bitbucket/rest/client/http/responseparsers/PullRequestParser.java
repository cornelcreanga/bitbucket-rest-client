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

import com.ccreanga.bitbucket.rest.client.model.pull.PullRequest;
import com.ccreanga.bitbucket.rest.client.model.pull.PullRequestBranch;
import com.ccreanga.bitbucket.rest.client.model.pull.PullRequestParticipant;
import com.ccreanga.bitbucket.rest.client.model.pull.PullRequestState;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

import static com.ccreanga.bitbucket.rest.client.http.responseparsers.ParserUtil.optionalJsonBoolean;
import static com.ccreanga.bitbucket.rest.client.http.responseparsers.ParserUtil.optionalJsonString;
import static com.ccreanga.bitbucket.rest.client.http.responseparsers.Parsers.*;

public class PullRequestParser implements Function<JsonElement, PullRequest> {
    @Override
    public PullRequest apply(JsonElement jsonElement) {
        if (jsonElement == null || !jsonElement.isJsonObject()) {
            return null;
        }
        JsonObject json = jsonElement.getAsJsonObject();
        //PullRequest pullRequest = new PullRequest();

        PullRequestParticipant author = pullRequestParticipantParser().apply(json.getAsJsonObject("author"));
        List<PullRequestParticipant> reviewers = listParser(pullRequestParticipantParser()).apply(json.get("reviewers"));
        List<PullRequestParticipant> participants = listParser(pullRequestParticipantParser()).apply(json.get("participants"));
        PullRequestBranch from = pullRequestBranchParser().apply(json.getAsJsonObject("fromRef"));
        PullRequestBranch to = pullRequestBranchParser().apply(json.getAsJsonObject("toRef"));

        String selfUrl = null;

        if (json.has("links")) {
            selfUrl = linkParser().apply(json.getAsJsonObject("links").get("self").getAsJsonArray().get(0)).getHref();
        }

        return new PullRequest(
                json.get("id").getAsLong(),
                json.get("version").getAsLong(),
                json.get("title").getAsString(),
                optionalJsonString(json, "description"),
                PullRequestState.valueOf(json.get("state").getAsString()),
                json.get("open").getAsBoolean(),
                json.get("closed").getAsBoolean(),
                new Date(json.get("createdDate").getAsLong()),
                new Date(json.get("updatedDate").getAsLong()),
                from,
                to,
                optionalJsonBoolean(json,"locked"),
                author,
                reviewers,
                participants,
                selfUrl
        );

    }
}
