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

import java.util.Collections;
import java.util.List;

public class BitBucketException extends RuntimeException {
    private List<BitBucketError> errors;

    public BitBucketException(String message) {
        super(message);
        this.errors = toErrors(getMessage(), this);
    }

    public BitBucketException(String message, Throwable cause) {
        super(message, cause);
        this.errors = toErrors(getMessage(), cause);
    }

    public BitBucketException(Throwable cause) {
        super(cause);
        this.errors = toErrors(getMessage(), cause);
    }

    public BitBucketException(List<BitBucketError> errors) {
        super(errors.isEmpty() ? null : errors.get(0).getMessage());
        this.errors = errors;
    }

    public List<BitBucketError> getErrors() {
        return errors;
    }

    public static List<BitBucketError> toErrors(String message) {
        return toErrors(message, null);
    }

    public static List<BitBucketError> toErrors(String message, Throwable e) {
        return Collections.singletonList(new BitBucketError(message, null, e != null ? e.getClass().getSimpleName() : null));
    }
}
