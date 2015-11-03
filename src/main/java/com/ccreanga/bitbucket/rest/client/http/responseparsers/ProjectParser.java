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

import com.ccreanga.bitbucket.rest.client.model.Project;

import java.util.function.Function;

import com.ccreanga.bitbucket.rest.client.model.ProjectType;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import static com.ccreanga.bitbucket.rest.client.http.responseparsers.ParserUtil.optionalJsonBoolean;
import static com.ccreanga.bitbucket.rest.client.http.responseparsers.ParserUtil.optionalJsonString;
import static com.ccreanga.bitbucket.rest.client.http.responseparsers.Parsers.linkParser;

class ProjectParser implements Function<JsonElement, Project> {
    @Override
    public Project apply(JsonElement jsonElement) {
        JsonObject json = jsonElement.getAsJsonObject();
        String selfUrl = null;

        if (json.has("links")) {
            selfUrl = linkParser().apply(json.getAsJsonObject("links").get("self").getAsJsonArray().get(0)).getHref();
        }


        return new Project(
                json.get("key").getAsString(),
                json.get("id").getAsLong(),
                json.get("name").getAsString(),
                optionalJsonString(json, "description"),
                optionalJsonBoolean(json, "public"),
                optionalJsonBoolean(json, "isPersonal"),
                ProjectType.valueOf(json.get("type").getAsString()),
                selfUrl
        );
    }

}
