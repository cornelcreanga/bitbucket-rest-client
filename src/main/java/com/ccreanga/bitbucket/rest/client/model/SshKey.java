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

package com.ccreanga.bitbucket.rest.client.model;

import java.io.Serializable;

/**
 * Generic representation of SSH public key
 */
public class SshKey implements Serializable {
    protected long id;
    protected String text;
    protected String label;

    public SshKey(String text, String label, long id) {
        this.text = text;
        this.label = label;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    /**
     * @return the public key value in PKCS format
     */
    public String getText() {
        return text;
    }

    /**
     * @return the public key label
     */
    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "SshKey{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", label='" + label + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SshKey sshKey = (SshKey) o;
        return java.util.Objects.equals(id, sshKey.id) &&
                java.util.Objects.equals(text, sshKey.text) &&
                java.util.Objects.equals(label, sshKey.label);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, text, label);
    }
}
