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

import com.ccreanga.bitbucket.rest.client.model.RepositorySshKey;
import com.ccreanga.bitbucket.rest.client.model.Repository;
import java.util.function.Function;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

class RepositorySshKeyParser implements Function<JsonElement, RepositorySshKey> {

    @Override
    public RepositorySshKey apply(JsonElement json) {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonObject repositoryObject = jsonObject.getAsJsonObject("repository");
        Repository repository = Parsers.repositoryParser().apply(repositoryObject);

        JsonObject key = jsonObject.getAsJsonObject("key");

        return new RepositorySshKey(
                key.get("id").getAsLong(),
                key.get("text").getAsString(),
                key.get("label").getAsString(),
                repository,
                jsonObject.get("permission").getAsString()
        );
    }

}

