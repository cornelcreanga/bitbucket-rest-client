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

import com.ccreanga.bitbucket.rest.client.model.pull.PullRequest;
import com.ccreanga.bitbucket.rest.client.model.pull.PullRequestBranch;
import com.ccreanga.bitbucket.rest.client.model.pull.PullRequestParticipant;
import com.ccreanga.bitbucket.rest.client.model.pull.PullRequestRole;
import com.ccreanga.bitbucket.rest.client.model.pull.PullRequestState;
import com.ccreanga.bitbucket.rest.client.model.User;
import com.ccreanga.bitbucket.rest.client.model.UserType;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class PullRequestParserTest {

    @Test
    public void testApply() throws Exception {
        PullRequestParser pullRequestParser = new PullRequestParser();

        PullRequest pullRequest = new PullRequest(
                101,
                1,
                "Talking Nerdy",
                "It’s a kludge, but put the tuple from the database in the cache.",
                PullRequestState.OPEN,
                true,
                false,
                new Date(1359075920),
                new Date(1359085920),
                new PullRequestBranch("refs/heads/feature-ABC-123", "my-repo", null, "PRJ"),
                new PullRequestBranch("refs/heads/master", "my-repo", null, "PRJ"),
                false,
                new PullRequestParticipant(
                        new User(115026, "tom", "tom@example.com", "Tom", true, "tom", UserType.NORMAL),
                        PullRequestRole.AUTHOR,
                        true
                ),
                Collections.singletonList(new PullRequestParticipant(
                        new User(101, "jcitizen", "jane@example.com", "Jane Citizen", true, "jcitizen", UserType.NORMAL),
                        PullRequestRole.REVIEWER,
                        true

                )),
                Arrays.asList(new PullRequestParticipant(
                                new User(3083181, "dick", "dick@example.com", "Dick", true, "dick", UserType.NORMAL),
                                PullRequestRole.PARTICIPANT,
                                false

                        ),
                        new PullRequestParticipant(
                                new User(99049120, "harry", "harry@example.com", "Harry", true, "harry", UserType.NORMAL),
                                PullRequestRole.PARTICIPANT,
                                true

                        )),
                "http://link/to/pullrequest"
        );

        JsonElement element = new JsonParser().parse(TestUtil.loadString("pull_requests.json"));
        PullRequest parsedPullRequest = pullRequestParser.apply(element);
        System.out.println(pullRequest);

        Assert.assertEquals(pullRequest, parsedPullRequest);

    }
}