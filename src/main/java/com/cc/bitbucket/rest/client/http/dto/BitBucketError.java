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

package com.cc.bitbucket.rest.client.http.dto;

import com.google.common.base.Objects;

import javax.annotation.Nullable;

/**
 * Describes an Stash error cause
 */
public class BitBucketError {
    private String message;
    private String context;
    private String exceptionName;

    public BitBucketError(String message, String context, String exceptionName) {
        this.context = context;
        this.message = message;
        this.exceptionName = exceptionName;
    }

    /**
     * @return name of field with problem, null if it is not a field error
     */
    @Nullable
    public String getContext() {
        return context;
    }

    /**
     * @return the error message
     */
    @Nullable
    public String getMessage() {
        return message;
    }

    /**
     * @return the error exception name as provided by Stash
     */
    @Nullable
    public String getExceptionName() {
        return exceptionName;
    }

    @Override
    public String toString() {
        return "BitBucketError{" +
                "message='" + message + '\'' +
                ", context='" + context + '\'' +
                ", exceptionName='" + exceptionName + '\'' +
                '}';
    }
}
