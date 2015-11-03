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

package com.ccreanga.bitbucket.rest.client.http.dto;


import com.google.gson.JsonObject;

public class UserSshKeyRequest {
    private String label;
    private String publicKey;

    public UserSshKeyRequest(String label, String publicKey) {
        this.label = label;
        this.publicKey = publicKey;
    }

    public JsonObject toJson() {
        JsonObject key = new JsonObject();
        key.addProperty("label", label);
        key.addProperty("text", publicKey);
        return key;
    }

    public String getLabel() {
        return label;
    }

    public String getPublicKey() {
        return publicKey;
    }
}
