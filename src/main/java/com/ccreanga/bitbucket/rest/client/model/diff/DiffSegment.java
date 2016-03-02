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

package com.ccreanga.bitbucket.rest.client.model.diff;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class DiffSegment  implements Serializable {

    @Nonnull
    private List<DiffLine> lines;
    @Nonnull
    private DiffSegmentType type;
    private boolean truncated;

    private DiffSegment() {
    }

    public DiffSegment(@Nonnull List<DiffLine> lines, @Nonnull DiffSegmentType type, boolean truncated) {
        this.lines = lines;
        this.type = type;
        this.truncated = truncated;
    }

    @Nonnull
    public List<DiffLine> getLines() {
        return lines;
    }

    @Nonnull
    public DiffSegmentType getType() {
        return type;
    }

    public boolean isTruncated() {
        return truncated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiffSegment that = (DiffSegment) o;
        return Objects.equals(truncated, that.truncated) &&
                Objects.equals(lines, that.lines) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lines, type, truncated);
    }

    @Override
    public String toString() {
        return "DiffSegment{" +
                "lines=" + lines +
                ", type=" + type +
                ", truncated=" + truncated +
                '}';
    }
}
