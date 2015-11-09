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

import com.google.common.collect.ImmutableList;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class DiffHunk  implements Serializable {

    private int destinationLine;
    private int destinationSpan;
    @Nonnull
    private List<DiffSegment> segments;
    private int sourceLine;
    private int sourceSpan;
    private boolean truncated;

    public DiffHunk(int destinationLine, int destinationSpan, @Nonnull List<DiffSegment> segments, int sourceLine, int sourceSpan, boolean truncated) {
        this.destinationLine = destinationLine;
        this.destinationSpan = destinationSpan;
        this.segments = ImmutableList.copyOf(segments);
        this.sourceLine = sourceLine;
        this.sourceSpan = sourceSpan;
        this.truncated = truncated;
    }

    public int getDestinationLine() {
        return destinationLine;
    }

    public int getDestinationSpan() {
        return destinationSpan;
    }

    @Nonnull
    public List<DiffSegment> getSegments() {
        return segments;
    }

    public int getSourceLine() {
        return sourceLine;
    }

    public int getSourceSpan() {
        return sourceSpan;
    }

    public boolean isTruncated() {
        return truncated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiffHunk diffHunk = (DiffHunk) o;
        return Objects.equals(destinationLine, diffHunk.destinationLine) &&
                Objects.equals(destinationSpan, diffHunk.destinationSpan) &&
                Objects.equals(sourceLine, diffHunk.sourceLine) &&
                Objects.equals(sourceSpan, diffHunk.sourceSpan) &&
                Objects.equals(truncated, diffHunk.truncated) &&
                Objects.equals(segments, diffHunk.segments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(destinationLine, destinationSpan, segments, sourceLine, sourceSpan, truncated);
    }

    @Override
    public String toString() {
        return "DiffHunk{" +
                "destinationLine=" + destinationLine +
                ", destinationSpan=" + destinationSpan +
                ", segments=" + segments +
                ", sourceLine=" + sourceLine +
                ", sourceSpan=" + sourceSpan +
                ", truncated=" + truncated +
                '}';
    }
}
