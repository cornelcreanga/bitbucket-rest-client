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

/**
 * SSH public key for repository
 */
public class RepositorySshKey extends SshKey {
    private Repository repository;
    private String permission;


    public RepositorySshKey(long id, String text, String label, Repository repository, String permission) {
        super(text, label, id);
        this.repository = repository;
        this.permission = permission;
    }

    /**
     * @return repository the key belongs to
     */
    public Repository getRepository() {
        return repository;
    }

    /**
     * @return permission on the repository for this ssh key
     */
    public String getPermission() {
        return permission;
    }

    @Override
    public String toString() {
        return "RepositorySshKey{" +
                "repository=" + repository +
                ", permission='" + permission + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepositorySshKey that = (RepositorySshKey) o;
        return java.util.Objects.equals(repository, that.repository) &&
                java.util.Objects.equals(permission, that.permission);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(repository, permission);
    }
}
