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

import com.cc.bitbucket.rest.client.http.dto.BitBucketError;

import javax.annotation.Nullable;
import java.util.List;

public class GenericException extends BitBucketException {
    private int statusCode;
    private String statusMessage;
    private String responseBody;

    public GenericException(List<BitBucketError> errors, int statusCode, String statusMessage, String responseBody) {
        super(errors);
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.responseBody = responseBody;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    @Nullable
    public String getResponseBody() {
        return responseBody;
    }

}
