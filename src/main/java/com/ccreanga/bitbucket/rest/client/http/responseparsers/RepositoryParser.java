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

import com.ccreanga.bitbucket.rest.client.model.Link;
import com.ccreanga.bitbucket.rest.client.model.Repository;
import java.util.function.Function;

import com.ccreanga.bitbucket.rest.client.model.RepositoryState;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


import static com.ccreanga.bitbucket.rest.client.http.responseparsers.ParserUtil.optionalJsonBoolean;
import static com.ccreanga.bitbucket.rest.client.http.responseparsers.ParserUtil.optionalJsonString;
import static com.ccreanga.bitbucket.rest.client.http.responseparsers.Parsers.linkParser;
import static com.ccreanga.bitbucket.rest.client.http.responseparsers.Parsers.projectParser;
import static com.ccreanga.bitbucket.rest.client.http.responseparsers.Parsers.repositoryParser;

class RepositoryParser implements Function<JsonElement, Repository> {

    @Override
    public Repository apply(JsonElement jsonElement) {
        if (jsonElement == null || !jsonElement.isJsonObject()) {
            return null;
        }

        JsonObject json = jsonElement.getAsJsonObject();

        String sshCloneUrl = null;
        String httpCloneUrl = null;
        String selfUrl = null;

        if (json.has("links")) {
            selfUrl = linkParser("url","text").apply(json.getAsJsonObject("links").get("self").getAsJsonArray().get(0)).getHref();

            JsonArray arrayCloneLinks = json.getAsJsonObject("links").get("clone").getAsJsonArray();
            Link first = linkParser().apply(arrayCloneLinks.get(0));
            Link second = linkParser().apply(arrayCloneLinks.get(1));
            if (first.getName().equals("http")){
                httpCloneUrl = first.getHref();
                sshCloneUrl = second.getHref();
            }else{
                sshCloneUrl = first.getHref();
                httpCloneUrl = second.getHref();
            }
        }

        return new Repository(
                optionalJsonString(json, "hierarchyId"),
                json.get("id").getAsLong(),
                json.get("slug").getAsString(),
                json.get("name").getAsString(),
                optionalJsonBoolean(json,"public"),
                optionalJsonBoolean(json,"fork"),
                optionalJsonBoolean(json,"forkable"),
                sshCloneUrl,
                httpCloneUrl,
                selfUrl,
                projectParser().apply(json.getAsJsonObject("project")),
                json.has("origin") ?repositoryParser().apply(json.getAsJsonObject("origin")):null,
                json.get("scmId").getAsString(),
                RepositoryState.valueOf(json.get("state").getAsString()),
                json.get("statusMessage").getAsString()
        );

    }

}
