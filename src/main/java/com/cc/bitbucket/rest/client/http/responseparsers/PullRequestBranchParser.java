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

import com.cc.bitbucket.rest.client.model.pull.PullRequestBranch;
import java.util.function.Function;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import static com.cc.bitbucket.rest.client.http.responseparsers.ParserUtil.optionalJsonString;

public class PullRequestBranchParser implements Function<JsonElement, PullRequestBranch> {
    @Override
    public PullRequestBranch apply(JsonElement json) {
        if (json == null || !json.isJsonObject()) {
            return null;
        }
        JsonObject jsonObject = json.getAsJsonObject();
        JsonObject repository = jsonObject.getAsJsonObject("repository");
        JsonObject project = repository.getAsJsonObject("project");
        return new PullRequestBranch(
                jsonObject.get("id").getAsString(),
                repository.get("slug").getAsString(),
                optionalJsonString(repository, "name"),
                project.get("key").getAsString()
        );
    }
}

