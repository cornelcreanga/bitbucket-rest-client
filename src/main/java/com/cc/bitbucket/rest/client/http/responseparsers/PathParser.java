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

import com.cc.bitbucket.rest.client.model.Path;
import java.util.function.Function;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Iterator;

public class PathParser implements Function<JsonElement, Path> {
    @Override
    public Path apply(JsonElement json) {
        if (json == null || !json.isJsonObject()) {
            return null;
        }
        JsonObject jsonObject = json.getAsJsonObject();
        JsonArray array = jsonObject.getAsJsonArray("components");
        String[] components = new String[array.size()];
        Iterator<JsonElement> it = array.iterator();
        int i = 0;
        while (it.hasNext()) {
            components[i++]=it.next().getAsString();
        }

        return new Path(components);
    }
}
