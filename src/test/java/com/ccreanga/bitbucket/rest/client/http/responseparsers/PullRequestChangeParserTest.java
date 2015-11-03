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

import com.ccreanga.bitbucket.rest.client.model.pull.PullRequestChange;
import com.ccreanga.bitbucket.rest.client.model.FileChangeType;
import com.ccreanga.bitbucket.rest.client.model.NodeType;
import com.ccreanga.bitbucket.rest.client.model.Path;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.Assert;
import org.junit.Test;

public class PullRequestChangeParserTest {

    @Test
    public void testApply() throws Exception {
        //FileChange{contentId='abcdef0123abcdef4567abcdef8987abcdef6543', fromContentId='bcdef0123abcdef4567abcdef8987abcdef6543a',
        // path=Path{new/path/to/file.txt}, srcPath=Path{path/to/file.txt}, type=MOVE, executable=false, percentUnchanged=98, nodeType=FILE,
        // srcExecutable=false, selfUrl='http://link/to/restchange'}

        PullRequestChange pullRequestChange = new PullRequestChange(
                "abcdef0123abcdef4567abcdef8987abcdef6543",
                "bcdef0123abcdef4567abcdef8987abcdef6543a",
                new Path(new String[]{"new", "path", "to", "file.txt"}),
                new Path(new String[]{"path", "to", "file.txt"}),
                FileChangeType.MOVE,
                false,
                98,
                NodeType.FILE,
                false,
                "http://link/to/restchange"
        );

        PullRequestChangeParser pullRequestChangeParser = new PullRequestChangeParser();
        JsonElement element = new JsonParser().parse(TestUtil.loadString("pull_requests_changes.json"));
        PullRequestChange pullRequestChangeRequest = pullRequestChangeParser.apply(element);

        Assert.assertEquals(pullRequestChange, pullRequestChangeRequest);
    }
}