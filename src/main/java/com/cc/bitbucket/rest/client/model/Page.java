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

package com.cc.bitbucket.rest.client.model;

import com.google.common.collect.ImmutableList;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * Generic Page class containing a list of values
 * @param <T>
 */
public class Page<T> {
    private int size;
    private int limit;
    private boolean lastPage;
    private int start;
    @Nullable
    private Integer nextPageStart;
    @Nonnull
    private List<T> values;

    public Page(int size, int limit, boolean lastPage, int start, @Nullable Integer nextPageStart, @Nonnull Iterable<T> values) {
        this.size = size;
        this.limit = limit;
        this.lastPage = lastPage;
        this.values = ImmutableList.copyOf(values);
        this.start = start;
        this.nextPageStart = nextPageStart;
    }

    public int getSize() {
        return size;
    }

    public int getLimit() {
        return limit;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    @Nonnull
    public List<T> getValues() {
        return values;
    }

    public int getStart() {
        return start;
    }

    @Nullable
    public Integer getNextPageStart() {
        return nextPageStart;
    }

    @Override
    public String toString() {
        return "Page{" +
                "size=" + size +
                ", limit=" + limit +
                ", lastPage=" + lastPage +
                ", start=" + start +
                ", nextPageStart=" + nextPageStart +
                ", values=" + values +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page<?> page = (Page<?>) o;
        return java.util.Objects.equals(size, page.size) &&
                java.util.Objects.equals(limit, page.limit) &&
                java.util.Objects.equals(lastPage, page.lastPage) &&
                java.util.Objects.equals(start, page.start) &&
                java.util.Objects.equals(nextPageStart, page.nextPageStart) &&
                java.util.Objects.equals(values, page.values);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(size, limit, lastPage, start, nextPageStart, values);
    }
}
