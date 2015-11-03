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
import java.util.function.Function;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import static com.ccreanga.bitbucket.rest.client.http.responseparsers.Parsers.listParser;
import static com.ccreanga.bitbucket.rest.client.http.responseparsers.Parsers.minimalCommitParser;

public class CommitParser implements Function<JsonElement, Commit> {
    @Override
    public Commit apply(JsonElement jsonElement) {

        if (jsonElement == null || !jsonElement.isJsonObject()) {
            return null;
        }
        JsonObject json = jsonElement.getAsJsonObject();

        return new Commit(
                json.get("id").getAsString(),
                json.get("displayId").getAsString(),
                json.get("author").getAsJsonObject().get("name").getAsString(),
                json.get("author").getAsJsonObject().get("emailAddress").getAsString(),
                json.get("authorTimestamp").getAsLong(),
                json.get("message").getAsString(),
                listParser(minimalCommitParser()).apply(json.get("parents"))
                );
    }
}
