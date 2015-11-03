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

import com.ccreanga.bitbucket.rest.client.model.Comment;
import com.ccreanga.bitbucket.rest.client.model.CommentAnchor;
import com.ccreanga.bitbucket.rest.client.model.Commit;
import com.ccreanga.bitbucket.rest.client.model.FileType;
import com.ccreanga.bitbucket.rest.client.model.LineType;
import com.ccreanga.bitbucket.rest.client.model.MinimalCommit;
import com.ccreanga.bitbucket.rest.client.model.PermittedOperations;
import com.ccreanga.bitbucket.rest.client.model.pull.activity.PullRequestActivity;
import com.ccreanga.bitbucket.rest.client.model.pull.activity.PullRequestCommentActivity;
import com.ccreanga.bitbucket.rest.client.model.pull.activity.PullRequestMergeActivity;
import com.ccreanga.bitbucket.rest.client.model.pull.activity.PullRequestRescopeActivity;
import com.ccreanga.bitbucket.rest.client.model.User;
import com.ccreanga.bitbucket.rest.client.model.UserType;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class PullRequestActivityParserTest{


    @Test
    public void testApplyMerged() throws Exception {
        PullRequestActivityParser pullRequestActivityParser = new PullRequestActivityParser();
        PullRequestActivity pullRequestActivity = new PullRequestMergeActivity(101l,new Date(1359085920l),new User(101,"jcitizen", "jane@example.com", "Jane Citizen", true, "jcitizen", UserType.NORMAL));

        JsonElement element = new JsonParser().parse(TestUtil.loadString("pull_requests_activities_merged.json"));
        PullRequestActivity parsedPullRequestActivity = pullRequestActivityParser.apply(element);
        Assert.assertEquals(pullRequestActivity, parsedPullRequestActivity);
    }

    @Test
    public void testApplyCommented() throws Exception {
        PullRequestActivityParser pullRequestActivityParser = new PullRequestActivityParser();
        PullRequestActivity pullRequestActivity =
                new PullRequestCommentActivity(
                        101l,
                        new Date(1359065920l),
                        new User(101,"jcitizen", "jane@example.com", "Jane Citizen", true, "jcitizen", UserType.NORMAL),
                        "ADDED",
                        new Comment(
                                1, 1, "A measured reply.",
                                new User(101, "jcitizen", "jane@example.com", "Jane Citizen", true, "jcitizen", UserType.NORMAL),
                                new Date(1442553508960l),
                                new Date(1442553508960l),
                                Arrays.asList(
                                        new Comment(
                                                1,1,"An insightful comment.",
                                                new User(101,"jcitizen","jane@example.com","Jane Citizen",true,"jcitizen",UserType.NORMAL),
                                                new Date(1442553508956l),
                                                new Date(1442553508956l),
                                                Collections.<Comment>emptyList(),new PermittedOperations(true,true))),
                                new PermittedOperations(true,true)),
                        new CommentAnchor(
                                "8d51122def5632836d1cb1026e879069e10a1e13",
                                "4b12658b6cd2bae179759c21537b4ec6627ff83b",
                                1,
                                LineType.CONTEXT,
                                FileType.FROM,
                                "path/to/file",
                                "path/to/file",
                                false),
                        null
                );


        JsonElement element = new JsonParser().parse(TestUtil.loadString("pull_requests_activities_commented.json"));
        PullRequestActivity parsedPullRequestActivity = pullRequestActivityParser.apply(element);
        System.out.println(parsedPullRequestActivity);
        Assert.assertEquals(pullRequestActivity, parsedPullRequestActivity);

    }

    @Test
    public void testApplyRescoped() throws Exception {
        PullRequestActivityParser pullRequestActivityParser = new PullRequestActivityParser();

        List<Commit> added = new ArrayList<>();
        List<MinimalCommit> minimalCommits = new ArrayList<>();
        minimalCommits.add(new MinimalCommit("abcdef0123abcdef4567abcdef8987abcdef6543","abcdef0"));
        added.add(new Commit("abcdef0123abcdef4567abcdef8987abcdef6543","abcdef0123a","charlie","charlie@example.com",1442553509807l,"WIP on feature 1",minimalCommits));
        added.add(new Commit("abcdef0123abcdef4567abcdef8987abcdef6543","abcdef0123a","charlie","charlie@example.com",1442553509156l,"WIP on feature 1",minimalCommits));

        List<Commit> removed = new ArrayList<>();
        minimalCommits = new ArrayList<>();
        minimalCommits.add(new MinimalCommit("abcdef0123abcdef4567abcdef8987abcdef6543","abcdef0"));
        removed.add(new Commit("def0123abcdef4567abcdef8987abcdef6543abc","def0123abcd","charlie","charlie@example.com",1442553509807l,"More work on feature 1",minimalCommits));
        removed.add(new Commit("def0123abcdef4567abcdef8987abcdef6543abc","def0123abcd","charlie","charlie@example.com",1442553509156l,"More work on feature 1",minimalCommits));

        PullRequestActivity pullRequestActivity = new PullRequestRescopeActivity(
                101l,
                new Date(1359065920),
                new User(101,"jcitizen", "jane@example.com", "Jane Citizen", true, "jcitizen", UserType.NORMAL),
                "abcdeabcdeabcdeabcdeabcdeabcdeabcdeabcde",
                "bcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdea",
                "cdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeab",
                "ddeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabc",
                added,
                removed
                );

        JsonElement element = new JsonParser().parse(TestUtil.loadString("pull_requests_activities_rescoped.json"));
        PullRequestActivity parsedPullRequestActivity = pullRequestActivityParser.apply(element);
        Assert.assertEquals(pullRequestActivity, parsedPullRequestActivity);
    }

}