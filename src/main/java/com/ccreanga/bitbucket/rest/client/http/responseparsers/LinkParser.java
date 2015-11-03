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
import java.util.function.Function;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import static com.ccreanga.bitbucket.rest.client.http.responseparsers.ParserUtil.optionalJsonString;

class LinkParser implements Function<JsonElement, Link> {

    private String linkName="href";
    private String textName="name";

    public LinkParser() {
    }

    public LinkParser(String linkName, String textName) {
        this.linkName = linkName;
        this.textName = textName;
    }

    @Override
    public Link apply(JsonElement jsonElement) {
        JsonObject json = jsonElement.getAsJsonObject();
        String href = optionalJsonString(json,linkName);
        String name = optionalJsonString(json,textName);
        return new Link(href, name);
    }

}
