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

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Map;

class BitBucketHttpExecutor {


    private DefaultHttpClient httpClient;
    private URL baseUrl;
    private BasicHttpContext forceBasicAuthContext;

    public BitBucketHttpExecutor(URL baseUrl, BitBucketCredentials credentials) {
        this.baseUrl = baseUrl;
        this.httpClient = new DefaultHttpClient();
        this.forceBasicAuthContext = new BasicHttpContext();

        initCredentials(credentials);
    }

    private void initCredentials(BitBucketCredentials config) {
        if (!Strings.isNullOrEmpty(config.getUsername())) {
            String scheme = urlScheme(baseUrl.getProtocol());
            int port = baseUrl.getPort() != -1 ? baseUrl.getPort() : (scheme.equals("https") ? 443 : 80);
            HttpHost targetHost = new HttpHost(baseUrl.getHost(), port, scheme);

            Credentials credentials = new UsernamePasswordCredentials(config.getUsername(), config.getPassword());
            httpClient.getCredentialsProvider().setCredentials(new AuthScope(targetHost), credentials);

            AuthCache authCache = new BasicAuthCache();
            authCache.put(targetHost, new BasicScheme());
            forceBasicAuthContext.setAttribute(ClientContext.AUTH_CACHE, authCache);
        }
    }
    private static String urlScheme(String url){
        if (url.toLowerCase().startsWith("https://"))
            return "https";
        return "http";
    }
    public HttpResponse execute(HttpRequest httpRequest) throws BitBucketException {
        HttpRequestBase request = null;
        try {
            request = createRequest(httpRequest);

            org.apache.http.HttpResponse response = httpClient.execute(request, httpRequest.isAnonymous() ? null : forceBasicAuthContext);
            StatusLine status = response.getStatusLine();
            Map<String, String> headers = toHeaderMultimap(response.getAllHeaders());

            ByteArrayOutputStream out = new ByteArrayOutputStream(2048);
            response.getEntity().writeTo(out);
            return new HttpResponse(status.getStatusCode(), status.getReasonPhrase(), headers, new String(out.toByteArray(),"UTF-8"));

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
        switch (httpRequest.getMethod()) {
            case GET:
                return new HttpGet(fullUri);
            case POST:
                HttpPost request = new HttpPost(fullUri);
                String payload = httpRequest.getPayload();
                if (payload != null) {
                    request.setEntity(new StringEntity(payload, ContentType.APPLICATION_JSON));
                }
                return request;
            default:
                throw new UnsupportedOperationException(String.format("http method %s is not supported", httpRequest.getMethod()));
        }
    }

    public void shutdown(){
        httpClient.getConnectionManager().shutdown();
    }

}
