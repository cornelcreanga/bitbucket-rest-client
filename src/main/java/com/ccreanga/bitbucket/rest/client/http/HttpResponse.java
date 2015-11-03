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

import com.google.common.collect.Maps;

import java.util.Map;

class HttpResponse {
    private int statusCode;
    private String statusMessage;
    private Map<String, String> headers;
    private String body;


    public HttpResponse(int statusCode, String statusMessage, Map<String, String> headers, String body) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.headers = Maps.newTreeMap(String.CASE_INSENSITIVE_ORDER);
        this.headers.putAll(headers);
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getBody() {
        return body;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public String getContentType() {
        return HttpUtil.getContentType(headers);
    }

    public String getContentEncoding(String defaultEncoding) {
        return HttpUtil.getContentEncoding(headers, defaultEncoding);
    }


    public Map<String, String> getHeaders() {
        return headers;
    }

    public boolean isSuccessful() {
        return statusCode >= 200 && statusCode < 400;
    }
}
