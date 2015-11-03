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

package com.ccreanga.bitbucket.rest.client.http.dto;

import com.google.gson.JsonObject;

public class CreateProjectKeyRequest {

    private final String key;
    private final String name;
    private final String type;
    private final String description;

    public CreateProjectKeyRequest(final String key, final String name, final String type, final String description) {
        this.key = key;
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public JsonObject toJson() {
        JsonObject req = new JsonObject();

        req.addProperty("key", key);
        req.addProperty("name", name);
        req.addProperty("type", type);
        req.addProperty("description", description);

        return req;
    }

}
