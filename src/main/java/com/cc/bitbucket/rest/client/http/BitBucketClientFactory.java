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

package com.cc.bitbucket.rest.client.http;

import com.cc.bitbucket.rest.client.ProjectClient;
import com.cc.bitbucket.rest.client.SshClient;

import java.net.URL;

public class BitBucketClientFactory {

    private ProjectClient projectClient;
    private SshClient sshClient;
    private BitBucketHttpExecutor bitBucketHttpExecutor;

    public BitBucketClientFactory(URL baseUrl, BitBucketCredentials credentials) {
        bitBucketHttpExecutor = new BitBucketHttpExecutor(baseUrl, credentials);
        projectClient = new ProjectClientRest(bitBucketHttpExecutor);
        sshClient = new SshClientRest(bitBucketHttpExecutor);
    }

    public void shutdown(){
        bitBucketHttpExecutor.shutdown();
    }

    public ProjectClient getProjectClient() {
        return projectClient;
    }

    public SshClient getSshClient() {
        return sshClient;
    }
}
