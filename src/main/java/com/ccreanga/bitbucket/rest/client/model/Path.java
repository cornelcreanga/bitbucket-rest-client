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

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

public class Path {

    private String[] components;


    public Path(String[] components) {
        this.components = Arrays.copyOf(components,components.length);
    }

    public String toFilePath(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < components.length; i++) {
            sb.append(components[i]);
            if (i!=(components.length-1))
                sb.append(File.separatorChar);
        }
        return sb.toString();
    }

    private String getName(){
        return components[components.length-1];
    };

    @Override
    public String toString() {
        return "Path{"+toFilePath()+"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Path path = (Path) o;
        return Objects.equals(toFilePath(), path.toFilePath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(components);
    }
}
