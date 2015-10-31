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

import com.cc.bitbucket.rest.client.Limit;
import com.cc.bitbucket.rest.client.http.dto.BitBucketError;
import com.cc.bitbucket.rest.client.http.responseparsers.Parsers;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;


abstract class BitBucketClient {

    private static Logger LOGGER = LoggerFactory.getLogger(BitBucketClient.class);

    private BitBucketHttpExecutor bitBucketHttpExecutor;


    public BitBucketClient(BitBucketHttpExecutor bitBucketHttpExecutor) {
        this.bitBucketHttpExecutor = bitBucketHttpExecutor;
    }

    protected String addLimits(Limit limit) {
        StringBuilder sb = new StringBuilder(20);
        sb.append("?start=").append(limit.getStart());
        if (limit.getEnd() > 0)
            sb.append("&limit=").append(limit.getEnd());
        return sb.toString();
    }

    protected String addParameter(String paramName, String paramValue) {
        if (!isNullOrEmpty(paramValue)) {
            return "&" + paramName + "=" + encode(paramValue);
        }
        return "";
    }

    protected JsonElement execute(String requestUrl, HttpMethod method, JsonObject requestJson, boolean anonymous) {

        String requestData = requestJson != null ? requestJson.toString() : null;
        HttpResponse response = bitBucketHttpExecutor.execute(new HttpRequest(requestUrl, method, requestData, anonymous));

        String responseString = response.getBody();
        LOGGER.trace(String.format("doRestCall response: code=%d; response='%s'", response.getStatusCode(), responseString));

        if (response.isSuccessful()) {
            return getJsonElement(response, responseString);
        } else {
            List<BitBucketError> errors;
            JsonElement jsonElement = getJsonElement(response, responseString);
            errors = jsonElement.isJsonObject() ?
                    Parsers.errorsParser().apply(jsonElement) :
                    BitBucketException.toErrors("Request to Stash failed. Returned with " + response.getStatusCode() + ". Response body is empty.");
            throw createStashRestException(response, errors, responseString);
        }

    }

    private JsonElement getJsonElement(HttpResponse response, String responseString) {
        try {
            return new JsonParser().parse(responseString);
        } catch (JsonSyntaxException e) {
            throw createStashRestException(response, BitBucketException.toErrors("Failed to parse response: " + e.getMessage()), responseString);
        }
    }

    private GenericException createStashRestException(HttpResponse response, List<BitBucketError> errors, String responseString) {
        int statusCode = response.getStatusCode();
        String statusMessage = response.getStatusMessage();
        switch (statusCode) {
            case 401:
                return new UnauthorizedException(errors, statusCode, statusMessage, responseString);
            case 404:
                return new ResourceNotFoundException(responseString, statusCode, statusMessage);
            default:
                return new GenericException(errors, statusCode, statusMessage, responseString);
        }
    }

    private static String encode(String queryString) {
        try {
            return URLEncoder.encode(queryString, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("UTF-8 not supported", ex);
        }
    }
}
