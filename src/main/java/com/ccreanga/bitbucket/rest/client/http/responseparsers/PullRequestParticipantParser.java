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

import com.ccreanga.bitbucket.rest.client.model.User;
import com.ccreanga.bitbucket.rest.client.model.pull.PullRequestParticipant;
import com.ccreanga.bitbucket.rest.client.model.pull.PullRequestRole;
import java.util.function.Function;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import static com.ccreanga.bitbucket.rest.client.http.responseparsers.Parsers.userParser;

public class PullRequestParticipantParser implements Function<JsonElement, PullRequestParticipant> {
    @Override
    public PullRequestParticipant apply(JsonElement jsonElement) {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        User user = userParser().apply(jsonObject.getAsJsonObject("user"));
        return new PullRequestParticipant(
                user,
                PullRequestRole.valueOf(jsonObject.get("role").getAsString()),
                jsonObject.get("approved").getAsBoolean()
        );
    }
}
