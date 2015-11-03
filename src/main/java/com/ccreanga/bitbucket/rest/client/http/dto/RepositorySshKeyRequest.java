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

public class RepositorySshKeyRequest {
    private String projectKey;
    private String repositorySlug;
    private String label;
    private String publicKey;
    private String permission;

    public RepositorySshKeyRequest(String projectKey, String repositorySlug, String label, String publicKey, String permission) {
        this.projectKey = projectKey;
        this.repositorySlug = repositorySlug;
        this.label = label;
        this.publicKey = publicKey;
        this.permission = permission;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public String getRepositorySlug() {
        return repositorySlug;
    }

    public String getLabel() {
        return label;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPermission() {
        return permission;
    }

    public JsonObject toJson() {
        JsonObject key = new JsonObject();
        key.addProperty("id", 0);
        key.addProperty("label", label);
        key.addProperty("text", publicKey);

        JsonObject keyPayload = new JsonObject();
        keyPayload.addProperty("project", projectKey);
        keyPayload.addProperty("repository", repositorySlug);
        keyPayload.addProperty("permission", permission);
        keyPayload.add("key", key);
        return keyPayload;
    }
}
