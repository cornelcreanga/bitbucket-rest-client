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

import com.cc.bitbucket.rest.client.model.Branch;
import java.util.function.Function;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

class BranchParser implements Function<JsonElement, Branch> {
    @Override
    public Branch apply(JsonElement jsonElement) {
        JsonObject json = jsonElement.getAsJsonObject();
        return new Branch(
                json.get("id").getAsString(),
                json.get("displayId").getAsString(),
                json.get("latestChangeset").getAsString(),
                json.get("isDefault").getAsBoolean()
        );
    }
}
