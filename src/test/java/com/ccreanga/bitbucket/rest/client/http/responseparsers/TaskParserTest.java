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
import com.ccreanga.bitbucket.rest.client.model.PermittedOperations;
import com.ccreanga.bitbucket.rest.client.model.Task;
import com.ccreanga.bitbucket.rest.client.model.TaskOperations;
import com.ccreanga.bitbucket.rest.client.model.TaskState;
import com.ccreanga.bitbucket.rest.client.model.User;
import com.ccreanga.bitbucket.rest.client.model.UserType;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import junit.framework.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.Date;

public class TaskParserTest {

    @Test
    public void testApply() throws Exception {

        Task task = new Task(
                98,
                TaskState.OPEN,
                "Fix typos on the constructor's arguments",
                new Date(1442553509203l),
                new Comment(
                        1, 1, "An insightful comment.",
                        new User(101, "jcitizen", "jane@example.com", "Jane Citizen", true, "jcitizen", UserType.NORMAL),
                        new Date(1442553508956l),
                        new Date(1442553508956l),
                        Collections.<Comment>emptyList(), new PermittedOperations(true, true)
                ),
                new User(101, "jcitizen", "jane@example.com", "Jane Citizen", true, "jcitizen", UserType.NORMAL),
                new TaskOperations(true, true, true)
        );

        JsonElement element = new JsonParser().parse(TestUtil.loadString("pull_requests_tasks.json"));
        Task parsedTask = Parsers.taskParser().apply(element);
        Assert.assertEquals(task, parsedTask);
    }
}