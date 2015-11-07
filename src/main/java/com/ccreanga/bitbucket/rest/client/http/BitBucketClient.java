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
import com.ccreanga.bitbucket.rest.client.http.dto.BitBucketError;
import com.ccreanga.bitbucket.rest.client.http.responseparsers.Parsers;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Strings.isNullOrEmpty;


abstract class BitBucketClient {

    private static Logger LOGGER = LoggerFactory.getLogger(BitBucketClient.class);

    private BitBucketHttpExecutor bitBucketHttpExecutor;


    public BitBucketClient(BitBucketHttpExecutor bitBucketHttpExecutor) {
        this.bitBucketHttpExecutor = bitBucketHttpExecutor;
    }

    protected String addLimits(Range range) {
        StringBuilder sb = new StringBuilder(20);
        sb.append("?start=").append(range.getStart());
        if (range.getLimit() > 0)
            sb.append("&limit=").append(range.getLimit());
        return sb.toString();
    }

    protected String addParameter(String paramName, String paramValue) {
        if (!isNullOrEmpty(paramValue)) {
            return "&" + paramName + "=" + encode(paramValue);
        }
        return "";
    }

    protected Optional<JsonElement> execute(String requestUrl, HttpMethod method, JsonObject requestJson) {

        String requestData = requestJson != null ? requestJson.toString() : null;
        HttpResponse response = bitBucketHttpExecutor.execute(new HttpRequest(requestUrl, method, requestData));

        String responseString = response.getBody();
        LOGGER.trace(String.format("doRestCall response: code=%d; response='%s'", response.getStatusCode(), responseString));

        Optional<JsonElement> jsonElement = getJsonElement(response, responseString);
        if (response.isSuccessful()) {
            return jsonElement;
        } else {
            List<BitBucketError> errors;
            errors = jsonElement.get().isJsonObject() ?
                    Parsers.errorsParser().apply(jsonElement.get()) :
                    BitBucketException.toErrors("Request to Stash failed. Returned with " + response.getStatusCode() + ". Response body is empty.");
            throw createStashRestException(response, errors, responseString);
        }

    }

    private Optional<JsonElement> getJsonElement(HttpResponse response, String responseString) {
        try {
            return responseString==null?Optional.empty():Optional.of(new JsonParser().parse(responseString));
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
