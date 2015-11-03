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

public class CreateRepositoryRequest {

    private final String name;
    private final String scmId;
    private final boolean forkable;

    public CreateRepositoryRequest(final String name, final String scmId, final boolean forkable) {
        this.name = name;
        this.scmId = scmId;
        this.forkable = forkable;
    }

    public JsonObject toJson() {
        JsonObject req = new JsonObject();

        req.addProperty("name", name);
        req.addProperty("scmId", scmId);
        req.addProperty("forkable", forkable);

        return req;
    }

}
