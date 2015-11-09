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

package com.ccreanga.bitbucket.rest.client.model.pull;

import com.ccreanga.bitbucket.rest.client.model.FileChangeType;
import com.ccreanga.bitbucket.rest.client.model.NodeType;
import com.ccreanga.bitbucket.rest.client.model.Path;

import java.io.Serializable;
import java.util.Objects;

public class PullRequestChange implements Serializable {

    private String contentId;
    private String fromContentId;
    private Path path;
    private Path srcPath;
    private FileChangeType type;
    private boolean executable;
    private int percentUnchanged;
    private NodeType nodeType;
    private boolean srcExecutable;
    private String selfUrl;

    public PullRequestChange(String contentId, String fromContentId, Path path, Path srcPath, FileChangeType type, boolean executable, int percentUnchanged, NodeType nodeType, boolean srcExecutable, String selfUrl) {
        this.contentId = contentId;
        this.fromContentId = fromContentId;
        this.path = path;
        this.srcPath = srcPath;
        this.type = type;
        this.executable = executable;
        this.percentUnchanged = percentUnchanged;
        this.nodeType = nodeType;
        this.srcExecutable = srcExecutable;
        this.selfUrl = selfUrl;
    }

    public String getContentId() {
        return contentId;
    }

    public String getFromContentId() {
        return fromContentId;
    }

    public Path getPath() {
        return path;
    }

    public Path getSrcPath() {
        return srcPath;
    }

    public FileChangeType getType() {
        return type;
    }

    public boolean isExecutable() {
        return executable;
    }

    public int getPercentUnchanged() {
        return percentUnchanged;
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public boolean isSrcExecutable() {
        return srcExecutable;
    }

    public String getSelfUrl() {
        return selfUrl;
    }

    @Override
    public String toString() {
        return "FileChange{" +
                "contentId='" + contentId + '\'' +
                ", fromContentId='" + fromContentId + '\'' +
                ", path=" + path +
                ", srcPath=" + srcPath +
                ", type=" + type +
                ", executable=" + executable +
                ", percentUnchanged=" + percentUnchanged +
                ", nodeType=" + nodeType +
                ", srcExecutable=" + srcExecutable +
                ", selfUrl='" + selfUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PullRequestChange that = (PullRequestChange) o;
        return Objects.equals(executable, that.executable) &&
                Objects.equals(percentUnchanged, that.percentUnchanged) &&
                Objects.equals(srcExecutable, that.srcExecutable) &&
                Objects.equals(contentId, that.contentId) &&
                Objects.equals(fromContentId, that.fromContentId) &&
                Objects.equals(path, that.path) &&
                Objects.equals(srcPath, that.srcPath) &&
                Objects.equals(type, that.type) &&
                Objects.equals(nodeType, that.nodeType) &&
                Objects.equals(selfUrl, that.selfUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentId, fromContentId, path, srcPath, type, executable, percentUnchanged, nodeType, srcExecutable, selfUrl);
    }
}
