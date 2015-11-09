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

package com.ccreanga.bitbucket.rest.client.model;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Objects;

public class CommentAnchor implements Serializable {

    private String fromHash;
    @Nonnull
    private String toHash;
    private long line;
    private LineType lineType;
    private FileType fileType;
    @Nonnull
    private String path;
    private String srcPath;
    private boolean orphaned;

    public CommentAnchor(String fromHash, String toHash, long line, LineType lineType, FileType fileType, String path, String srcPath, boolean orphaned) {
        this.fromHash = fromHash;
        this.toHash = toHash;
        this.line = line;
        this.lineType = lineType;
        this.fileType = fileType;
        this.path = path;
        this.srcPath = srcPath;
        this.orphaned = orphaned;
    }

    public String getFromHash() {
        return fromHash;
    }

    public String getToHash() {
        return toHash;
    }

    public long getLine() {
        return line;
    }

    public LineType getLineType() {
        return lineType;
    }

    public FileType getFileType() {
        return fileType;
    }

    public String getPath() {
        return path;
    }

    public String getSrcPath() {
        return srcPath;
    }

    public boolean isOrphaned() {
        return orphaned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentAnchor that = (CommentAnchor) o;
        return Objects.equals(line, that.line) &&
                Objects.equals(orphaned, that.orphaned) &&
                Objects.equals(fromHash, that.fromHash) &&
                Objects.equals(toHash, that.toHash) &&
                Objects.equals(lineType, that.lineType) &&
                Objects.equals(fileType, that.fileType) &&
                Objects.equals(path, that.path) &&
                Objects.equals(srcPath, that.srcPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromHash, toHash, line, lineType, fileType, path, srcPath, orphaned);
    }

    @Override
    public String toString() {
        return "CommentAnchor{" +
                "fromHash='" + fromHash + '\'' +
                ", toHash='" + toHash + '\'' +
                ", line=" + line +
                ", lineType=" + lineType +
                ", fileType=" + fileType +
                ", path='" + path + '\'' +
                ", srcPath='" + srcPath + '\'' +
                ", orphaned=" + orphaned +
                '}';
    }
}
