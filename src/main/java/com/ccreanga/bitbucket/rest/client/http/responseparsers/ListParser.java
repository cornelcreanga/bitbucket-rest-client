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

import java.util.function.Function;

import com.google.gson.JsonElement;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

class ListParser<T> implements Function<JsonElement, List<T>> {
    private Function<JsonElement, T> parseFunction;

    public ListParser(Function<JsonElement, T> parseFunction) {
        this.parseFunction = parseFunction;
    }

    @Override
    public List<T> apply(JsonElement json) {
        if ((json == null) || (json.isJsonNull())) {
            return Collections.emptyList();
        } else {
            return StreamSupport.
                    stream(json.getAsJsonArray().spliterator(),false).
                    map(parseFunction).
                    collect(Collectors.toList());
        }
    }
}
