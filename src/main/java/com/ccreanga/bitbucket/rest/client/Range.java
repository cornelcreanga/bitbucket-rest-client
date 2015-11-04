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

package com.ccreanga.bitbucket.rest.client;

public class Range {

    private int start;
    private int limit;
    public static Range ALL = new Range(0,Integer.MAX_VALUE);

    public Range(int start, int limit) {
        if (start < 0)
            throw new IllegalArgumentException("start should be greater than 0 not:" + start);
        if (limit < 0)
            throw new IllegalArgumentException("limit should be greater than 0 not:" + start);

        this.start = start;
        this.limit = limit;
    }

    public int getStart() {
        return start;
    }

    public int getLimit() {
        return limit;
    }
}
