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

import com.ccreanga.bitbucket.rest.client.model.Task;
import com.ccreanga.bitbucket.rest.client.model.TaskState;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Date;
import java.util.function.Function;

import static com.ccreanga.bitbucket.rest.client.http.responseparsers.Parsers.commentParser;
import static com.ccreanga.bitbucket.rest.client.http.responseparsers.Parsers.taskOperationsParser;
import static com.ccreanga.bitbucket.rest.client.http.responseparsers.Parsers.userParser;

public class TaskParser implements Function<JsonElement, Task> {

    @Override
    public Task apply(JsonElement jsonElement) {
        if (jsonElement == null || !jsonElement.isJsonObject()) {
            return null;
        }
        JsonObject json = jsonElement.getAsJsonObject();
        return new Task(
                json.get("id").getAsLong(),
                TaskState.valueOf(json.get("state").getAsString()),
                json.get("text").getAsString(),
                new Date(json.get("createdDate").getAsLong()),
                commentParser().apply(json.getAsJsonObject("anchor")),
                userParser().apply(json.getAsJsonObject("author")),
                taskOperationsParser().apply(json.getAsJsonObject("permittedOperations"))
        );
    }

}
