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

package com.cc.bitbucket.rest.client.http.responseparsers;

import com.cc.bitbucket.rest.client.model.Project;
import com.cc.bitbucket.rest.client.model.ProjectType;
import com.cc.bitbucket.rest.client.model.Repository;
import com.cc.bitbucket.rest.client.model.RepositoryState;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import junit.framework.Assert;
import org.junit.Test;

public class RepositoryParserTest{

    @Test
    public void testApply() throws Exception {
        Repository repository = new Repository(
                null,
                1,
                "my-repo",
                "My repo",
                true,
                false,
                true,
                "ssh://git@<baseURL>/PRJ/my-repo.git",
                "https://<baseURL>/scm/PRJ/my-repo.git",
                "http://link/to/repository",
                new Project("PRJ",1,"My Cool Project","The description for my cool project.",true,false, ProjectType.NORMAL,"http://link/to/project"),
                null,
                "git",
                RepositoryState.AVAILABLE,
                "Available"
        );

        JsonElement element = new JsonParser().parse(TestUtil.loadString("repo.json"));
        Repository parsedRepository = Parsers.repositoryParser().apply(element);
        System.out.println(parsedRepository);
        Assert.assertEquals(repository, parsedRepository);
    }
}