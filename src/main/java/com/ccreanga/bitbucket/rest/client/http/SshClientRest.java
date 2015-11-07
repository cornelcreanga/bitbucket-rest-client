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

package com.ccreanga.bitbucket.rest.client.http;

import com.ccreanga.bitbucket.rest.client.Range;
import com.ccreanga.bitbucket.rest.client.SshClient;
import com.ccreanga.bitbucket.rest.client.model.Page;
import com.ccreanga.bitbucket.rest.client.model.RepositorySshKey;
import com.ccreanga.bitbucket.rest.client.http.responseparsers.Parsers;
import com.ccreanga.bitbucket.rest.client.model.UserSshKey;
import com.google.gson.JsonElement;


import static com.ccreanga.bitbucket.rest.client.http.responseparsers.Parsers.*;

class SshClientRest extends BitBucketClient implements SshClient {

    public SshClientRest(BitBucketHttpExecutor bitBucketHttpExecutor) {
        super(bitBucketHttpExecutor);
    }

    @Override
    public Page<RepositorySshKey> getRepositoryKeys(String projectKey, String repositorySlug, Range range) {
        String requestUrl = String.format("/rest/keys/1.0/projects/%s/repos/%s/ssh", projectKey, repositorySlug) + addLimits(range);
        JsonElement jsonElement = execute(requestUrl, HttpMethod.GET, null).get();
        return pageParser(repositorySshKeyParser()).apply(jsonElement);
    }

    @Override
    public Page<UserSshKey> getCurrentUserKeys(Range range) {
        String requestUrl = "/rest/ssh/1.0/keys" + addLimits(range);
        JsonElement jsonElement = execute(requestUrl, HttpMethod.GET, null).get();
        return pageParser(Parsers.userSshKeyParser()).apply(jsonElement);
    }

}
