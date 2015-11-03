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

import com.ccreanga.bitbucket.rest.client.model.Comment;
import java.util.function.Function;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Date;

import static com.ccreanga.bitbucket.rest.client.http.responseparsers.Parsers.commentParser;
import static com.ccreanga.bitbucket.rest.client.http.responseparsers.Parsers.listParser;
import static com.ccreanga.bitbucket.rest.client.http.responseparsers.Parsers.permittedOperationsParser;
import static com.ccreanga.bitbucket.rest.client.http.responseparsers.Parsers.userParser;

public class CommentParser  implements Function<JsonElement, Comment> {
    @Override
    public Comment apply(JsonElement jsonElement) {
        if (jsonElement == null || !jsonElement.isJsonObject()) {
            return null;
        }
        JsonObject json = jsonElement.getAsJsonObject();
        return new Comment(
                json.get("id").getAsLong(),
                json.get("version").getAsLong(),
                json.get("text").getAsString(),
                userParser().apply(json.getAsJsonObject("author")),
                new Date(json.get("createdDate").getAsLong()),
                new Date(json.get("updatedDate").getAsLong()),
                listParser(commentParser()).apply(json.get("comments")),
                permittedOperationsParser().apply(json.getAsJsonObject("permittedOperations"))
        );

    }
}
