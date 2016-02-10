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

package com.ccreanga.bitbucket.rest.client.integrationtests;

import com.ccreanga.bitbucket.rest.client.ProjectClient;
import com.ccreanga.bitbucket.rest.client.http.BitBucketClientFactory;
import com.ccreanga.bitbucket.rest.client.http.BitBucketCredentials;
import com.ccreanga.bitbucket.rest.client.model.ProjectType;

import java.net.MalformedURLException;
import java.net.URL;

public class IntegrationTestHelper {

    public static String testServer = "http://localhost:7990/";
    public static String testUser = "teststash";
    public static String testPassword = "teststash";
    BitBucketClientFactory factory;
    ProjectClient projectClient;


    public IntegrationTestHelper() {
        factory = new BitBucketClientFactory(testServer,new BitBucketCredentials(testUser,testPassword));
        projectClient = factory.getProjectClient();
    }

    public void insertTestData(){
        projectClient.createProject("TESTPROJECT1","NAME1", ProjectType.NORMAL,"DESCRIPTION1");
    }

    public void cleanTestData(){
        projectClient.deleteProject("TESTPROJECT1");
    }

}
