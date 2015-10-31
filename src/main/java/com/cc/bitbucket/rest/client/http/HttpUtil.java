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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class HttpUtil {
    public static String CONTENT_TYPE = "Content-Type";
    public static Pattern CHARSET_REGEX = Pattern.compile("charset\\s*=\\s*(.*)$", Pattern.CASE_INSENSITIVE);

    @Nullable
    public static String getContentEncoding(@Nonnull Map<String, String> responseHeaders, @Nullable String defaultEncoding) {
        String encoding = null;

        String headerValue = responseHeaders.get(CONTENT_TYPE);
        if (headerValue != null) {
            Matcher matcher = CHARSET_REGEX.matcher(headerValue);
            if (matcher.find()) {
                encoding = matcher.group(1);
            }
        }
        if (encoding == null) {
            encoding = defaultEncoding;
        }
        return encoding;
    }

    @Nullable
    public static String getContentType(@Nonnull Map<String, String> responseHeaders) {
        String headerValue = responseHeaders.get(CONTENT_TYPE);
        if (headerValue == null) {
            return null;
        }
        int charsetIdx = headerValue.indexOf(';');
        return (charsetIdx != -1 ? headerValue.substring(0, charsetIdx) : headerValue).trim();
    }
}
