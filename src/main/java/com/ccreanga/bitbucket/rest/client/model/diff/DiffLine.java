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
import java.util.List;
import java.util.Objects;

public class DiffLine {

    private List<Long> commentIds;
    private ConflictMarker conflictMarker;
    private int destination;
    @Nonnull
    private String line;
    private int source;
    private boolean conflicting;
    private boolean truncated;

    public DiffLine(/**List<Long> commentIds, ConflictMarker conflictMarker,*/int destination, @Nonnull String line, int source, /**boolean conflicting,*/ boolean truncated) {
//        this.commentIds = ImmutableList.copyOf(commentIds);
//        this.conflictMarker = conflictMarker;
        this.destination = destination;
        this.line = line;
        this.source = source;
//        this.conflicting = conflicting;
        this.truncated = truncated;
    }

    public List<Long> getCommentIds() {
        return commentIds;
    }

    public ConflictMarker getConflictMarker() {
        return conflictMarker;
    }

    public int getDestination() {
        return destination;
    }

    @Nonnull
    public String getLine() {
        return line;
    }

    public int getSource() {
        return source;
    }

    public boolean isConflicting() {
        return conflicting;
    }

    public boolean isTruncated() {
        return truncated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiffLine diffLine = (DiffLine) o;
        return Objects.equals(destination, diffLine.destination) &&
                Objects.equals(source, diffLine.source) &&
                Objects.equals(conflicting, diffLine.conflicting) &&
                Objects.equals(truncated, diffLine.truncated) &&
                Objects.equals(commentIds, diffLine.commentIds) &&
                Objects.equals(conflictMarker, diffLine.conflictMarker) &&
                Objects.equals(line, diffLine.line);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentIds, conflictMarker, destination, line, source, conflicting, truncated);
    }

    @Override
    public String toString() {
        return "DiffLine{" +
                "commentIds=" + commentIds +
                ", conflictMarker=" + conflictMarker +
                ", destination=" + destination +
                ", line='" + line + '\'' +
                ", source=" + source +
                ", conflicting=" + conflicting +
                ", truncated=" + truncated +
                '}';
    }
}
