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

import com.ccreanga.bitbucket.rest.client.model.*;
import com.ccreanga.bitbucket.rest.client.http.dto.BitBucketError;
import com.ccreanga.bitbucket.rest.client.model.Link;
import com.ccreanga.bitbucket.rest.client.model.diff.Diff;
import com.ccreanga.bitbucket.rest.client.model.diff.DiffHunk;
import com.ccreanga.bitbucket.rest.client.model.diff.DiffLine;
import com.ccreanga.bitbucket.rest.client.model.diff.DiffSegment;
import com.ccreanga.bitbucket.rest.client.model.pull.PullRequest;
import com.ccreanga.bitbucket.rest.client.model.pull.activity.PullRequestActivity;
import com.ccreanga.bitbucket.rest.client.model.pull.PullRequestBranch;
import com.ccreanga.bitbucket.rest.client.model.pull.PullRequestChange;
import com.ccreanga.bitbucket.rest.client.model.pull.PullRequestParticipant;
import java.util.function.Function;
import com.google.gson.JsonElement;

import java.util.List;

public class Parsers {
    private Parsers() {
    }

    public static <T> Function<JsonElement, List<T>> listParser(Function<JsonElement, T> elementParser) {
        return new ListParser<>(elementParser);
    }

    public static Function<JsonElement, Branch> branchParser() {
        return BRANCH_PARSER;
    }


    public static Function<JsonElement, Link> linkParser() {
        return new LinkParser();
    }

    public static Function<JsonElement, Link> linkParser(String linkName,String textName) {
        return new LinkParser(linkName,textName);
    }

    public static <T> Function<JsonElement, Page<T>> pageParser(Function<JsonElement, T> valueParser) {
        return new PageParser<>(valueParser);
    }

    public static Function<JsonElement, Project> projectParser() {
        return PROJECT_PARSER;
    }

    public static Function<JsonElement, Repository> repositoryParser() {
        return REPOSITORY_PARSER;
    }

    public static Function<JsonElement, RepositorySshKey> repositorySshKeyParser() {
        return REPOSITORY_SSH_KEY_PARSER;
    }

    public static Function<JsonElement, UserSshKey> userSshKeyParser() {
        return USER_SSH_KEY_PARSER;
    }

    public static Function<JsonElement, User> userParser() {
        return USER_PARSER;
    }

    public static Function<JsonElement, PullRequest> pullRequestParser() {
        return PULL_REQUEST_PARSER;
    }

    public static Function<JsonElement, PullRequestParticipant> pullRequestParticipantParser() {
        return PULL_REQUEST_PARTICIPANT_PARSER;
    }

    public static Function<JsonElement, PullRequestBranch> pullRequestBranchParser() {
        return PULL_REQUEST_BRANCH_PARSER;
    }

    public static Function<JsonElement, PullRequestChange> pullRequestChangeParser() {
        return PULL_REQUEST_CHANGE_PARSER;
    }

    public static Function<JsonElement, PullRequestActivity> pullRequestActivityParser(long pullRequestId) {
        return new PullRequestActivityParser(pullRequestId);
    }

    public static Function<JsonElement, Comment> commentParser() {
        return COMMENT_PARSER;
    }

    public static Function<JsonElement, Path> pathParser() {
        return PATH_PARSER;
    }

    public static Function<JsonElement, BitBucketError> errorParser() {
        return ERROR_PARSER;
    }

    public static Function<JsonElement, List<BitBucketError>> errorsParser() {
        return ERRORS_PARSER;
    }

    public static Function<JsonElement, Commit> commitParser() {
        return COMMIT_PARSER;
    }
    public static Function<JsonElement, MinimalCommit> minimalCommitParser() {
        return MINIMAL_COMMIT_PARSER;
    }

    public static Function<JsonElement, CommentAnchor> commentAnchorParser() {
        return COMMENT_ANCHOR_PARSER;
    }

    public static Function<JsonElement, Diff> diffParser() {
        return DIFF_PARSER;
    }
    public static Function<JsonElement, DiffLine> diffLineParser() {
        return DIFF_LINE_PARSER;
    }
    public static Function<JsonElement, DiffHunk> diffHunkParser() {
        return DIFF_HUNK_PARSER;
    }
    public static Function<JsonElement, DiffSegment> diffSegmentParser() {
        return DIFF_SEGMENT_PARSER;
    }

    public static Function<JsonElement,Task> taskParser(){return  TASK_PARSER;}
    public static Function<JsonElement,TaskOperations> taskOperationsParser(){return  TASK_OPERATIONS_PARSER;}
    public static Function<JsonElement,PermittedOperations> permittedOperationsParser(){return  PERMITTED_OPERATIONS_PARSER;}

    private static BranchParser BRANCH_PARSER = new BranchParser();
    private static ProjectParser PROJECT_PARSER = new ProjectParser();
    private static RepositoryParser REPOSITORY_PARSER = new RepositoryParser();
    private static RepositorySshKeyParser REPOSITORY_SSH_KEY_PARSER = new RepositorySshKeyParser();
    private static UserSshKeyParser USER_SSH_KEY_PARSER = new UserSshKeyParser();

    private static UserParser USER_PARSER = new UserParser();
    private static PullRequestParser PULL_REQUEST_PARSER = new PullRequestParser();
    private static PullRequestParticipantParser PULL_REQUEST_PARTICIPANT_PARSER = new PullRequestParticipantParser();
    private static PullRequestBranchParser PULL_REQUEST_BRANCH_PARSER = new PullRequestBranchParser();

    private static PullRequestChangeParser PULL_REQUEST_CHANGE_PARSER = new PullRequestChangeParser();
    private static PathParser PATH_PARSER = new PathParser();
    private static CommentParser COMMENT_PARSER = new CommentParser();
    private static CommitParser COMMIT_PARSER = new CommitParser();
    private static MinimalCommitParser MINIMAL_COMMIT_PARSER = new MinimalCommitParser();
    private static CommentAnchorParser COMMENT_ANCHOR_PARSER = new CommentAnchorParser();

    private static DiffParser DIFF_PARSER = new DiffParser();
    private static DiffLineParser DIFF_LINE_PARSER = new DiffLineParser();
    private static DiffHunkParser DIFF_HUNK_PARSER = new DiffHunkParser();
    private static DiffSegmentParser DIFF_SEGMENT_PARSER = new DiffSegmentParser();

    private static TaskParser TASK_PARSER = new TaskParser();
    private static TaskOperationsParser TASK_OPERATIONS_PARSER = new TaskOperationsParser();
    private static PermittedOperationsParser PERMITTED_OPERATIONS_PARSER = new PermittedOperationsParser();



    private static ErrorParser ERROR_PARSER = new ErrorParser();
    private static ErrorsParser ERRORS_PARSER = new ErrorsParser();
}
