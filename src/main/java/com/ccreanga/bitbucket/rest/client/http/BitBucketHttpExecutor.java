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

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.BasicHttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Map;

class BitBucketHttpExecutor {

    private static Logger LOGGER = LoggerFactory.getLogger(BitBucketHttpExecutor.class);
    private CloseableHttpClient httpClient;
    private String baseUrl;
    private HttpClientContext context;

    public BitBucketHttpExecutor(String baseUrl, BitBucketCredentials credentials) {
        this.baseUrl = baseUrl;

        HttpHost targetHost = HttpHost.create(baseUrl);
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(5);
        connectionManager.setDefaultMaxPerRoute(4);


        BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                new AuthScope(targetHost),
                new UsernamePasswordCredentials(credentials.getUsername(), credentials.getPassword())
        );

        AuthCache authCache = new BasicAuthCache();
        BasicScheme basicAuth = new BasicScheme();
        authCache.put(targetHost, basicAuth);

        context = HttpClientContext.create();
        context.setCredentialsProvider(credentialsProvider);
        context.setAuthCache(authCache);

        httpClient =
                HttpClients.custom().
                        setConnectionManager(connectionManager).
                        setDefaultCredentialsProvider(credentialsProvider).
                        build();

    }

    public HttpResponse execute(HttpRequest httpRequest) throws BitBucketException {
        HttpRequestBase request;
        try {
            request = createRequest(httpRequest);

            org.apache.http.HttpResponse response = httpClient.execute(request, context);
            StatusLine status = response.getStatusLine();
            Map<String, String> headers = toHeaderMultimap(response.getAllHeaders());

            String body = null;
            if (response.getEntity() != null) {
                ByteArrayOutputStream out = new ByteArrayOutputStream(2048);
                response.getEntity().writeTo(out);
                body = new String(out.toByteArray(), "UTF-8");
            }
            return new HttpResponse(status.getStatusCode(), status.getReasonPhrase(), headers, body);

        } catch (IOException e) {
            throw new BitBucketException(e);
        }
    }

    private Map<String, String> toHeaderMultimap(Header[] headers) {
        Map<String, String> headerMap = Maps.newLinkedHashMap();

        for (Header header : headers) {
            String name = header.getName().toLowerCase();
            String existingValue = headerMap.get(name);
            headerMap.put(name, existingValue == null ? header.getValue() : existingValue + "," + header.getValue());
        }

        return ImmutableMap.copyOf(headerMap);
    }

    private HttpRequestBase createRequest(HttpRequest httpRequest) {
        URI fullUri = URI.create(baseUrl + "/" + httpRequest.getUrl()).normalize();
        String payload;
        switch (httpRequest.getMethod()) {
            case GET:
                return new HttpGet(fullUri);
            case POST:
                HttpPost request = new HttpPost(fullUri);
                payload = httpRequest.getPayload();
                if (payload != null) {
                    request.setEntity(new StringEntity(payload, ContentType.APPLICATION_JSON));
                }
                return request;
            case DELETE:
                return new HttpDelete(fullUri);

            default:
                throw new UnsupportedOperationException(String.format("http method %s is not supported", httpRequest.getMethod()));
        }
    }

    public void shutdown() {

        try {
            httpClient.close();
        } catch (IOException e) {
            LOGGER.warn("exception when trying to close the http client",e);
        }
    }

}
