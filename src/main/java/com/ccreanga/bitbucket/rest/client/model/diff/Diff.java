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

import com.ccreanga.bitbucket.rest.client.model.Path;
import com.google.common.collect.ImmutableList;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Diff  implements Serializable {

    private Path destination;
    @Nonnull
    private List<DiffHunk> hunks;
    private Path source;
    private boolean binary;
    private boolean truncated;

    private Diff() {
    }

    public Diff(Path destination, @Nonnull List<DiffHunk> hunks, Path source, boolean binary, boolean truncated) {
        this.destination = destination;
        this.hunks = ImmutableList.copyOf(hunks);
        this.source = source;
        this.binary = binary;
        this.truncated = truncated;
    }

    public Path getDestination() {
        return destination;
    }

    @Nonnull
    public List<DiffHunk> getHunks() {
        return hunks;
    }

    public Path getSource() {
        return source;
    }

    public boolean isBinary() {
        return binary;
    }

    public boolean isTruncated() {
        return truncated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diff diff = (Diff) o;
        return Objects.equals(binary, diff.binary) &&
                Objects.equals(truncated, diff.truncated) &&
                Objects.equals(destination, diff.destination) &&
                Objects.equals(hunks, diff.hunks) &&
                Objects.equals(source, diff.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(destination, hunks, source, binary, truncated);
    }

    @Override
    public String toString() {
        return "Diff{" +
                "destination=" + destination +
                ", hunks=" + hunks +
                ", source=" + source +
                ", binary=" + binary +
                ", truncated=" + truncated +
                '}';
    }
}
