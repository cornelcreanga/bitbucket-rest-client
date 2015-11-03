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
import com.ccreanga.bitbucket.rest.client.model.UserType;
import java.util.function.Function;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class UserParser implements Function<JsonElement, User> {
    @Override
    public User apply(JsonElement jsonElement) {
        JsonObject json = jsonElement.getAsJsonObject();
        return new User(
                json.get("id").getAsLong(),
                json.get("name").getAsString(),
                json.get("emailAddress").getAsString(),
                json.get("displayName").getAsString(),
                json.get("active").getAsBoolean(),
                json.get("slug").getAsString(),
                UserType.valueOf(json.get("type").getAsString())
        );

    }
}
