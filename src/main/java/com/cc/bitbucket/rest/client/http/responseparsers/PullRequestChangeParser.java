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

package com.cc.bitbucket.rest.client.http.responseparsers;

import com.cc.bitbucket.rest.client.model.pull.PullRequestChange;
import com.cc.bitbucket.rest.client.model.FileChangeType;
import com.cc.bitbucket.rest.client.model.NodeType;
import java.util.function.Function;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import static com.cc.bitbucket.rest.client.http.responseparsers.ParserUtil.*;
import static com.cc.bitbucket.rest.client.http.responseparsers.Parsers.*;


public class PullRequestChangeParser implements Function<JsonElement, PullRequestChange> {
    @Override
    public PullRequestChange apply(JsonElement jsonElement) {
        if (jsonElement == null || !jsonElement.isJsonObject()) {
            return null;
        }
        JsonObject json = jsonElement.getAsJsonObject();
        String selfUrl = null;
        if (json.has("links")) {
            selfUrl = linkParser().apply(json.getAsJsonObject("links").get("self").getAsJsonArray().get(0)).getHref();
        }
        return new PullRequestChange(
                json.get("contentId").getAsString(),
                optionalJsonString(json, "fromContentId"),
                pathParser().apply(json.getAsJsonObject("path")),
                pathParser().apply(json.getAsJsonObject("srcPath")),
                FileChangeType.valueOf(json.get("type").getAsString()),
                json.get("executable").getAsBoolean(),
                json.get("percentUnchanged").getAsInt(),
                NodeType.valueOf(json.get("nodeType").getAsString()),
                json.get("executable").getAsBoolean(),
                selfUrl
                );

    }
}
