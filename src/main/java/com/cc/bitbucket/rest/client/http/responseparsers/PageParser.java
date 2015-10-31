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

import com.cc.bitbucket.rest.client.model.Page;
import java.util.function.Function;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;

import static com.cc.bitbucket.rest.client.http.responseparsers.ParserUtil.optionalJsonBoolean;
import static com.cc.bitbucket.rest.client.http.responseparsers.ParserUtil.optionalJsonLong;

class PageParser<T> implements Function<JsonElement, Page<T>> {
    Function<JsonElement, T> valueParser;

    public PageParser(Function<JsonElement, T> valueParser) {
        this.valueParser = valueParser;
    }

    @Override
    public Page<T> apply(JsonElement jsonElement) {
        JsonObject json = jsonElement.getAsJsonObject();
        List<T> values = Parsers.listParser(valueParser).apply(json.getAsJsonArray("values"));

        return new Page<T>(
                json.get("size").getAsInt(),
                json.get("limit").getAsInt(),
                optionalJsonBoolean(json, "isLastPage"),
                json.get("start").getAsInt(),
                (int)optionalJsonLong(json,"nextPageStart"),
                values
        );
    }

}
