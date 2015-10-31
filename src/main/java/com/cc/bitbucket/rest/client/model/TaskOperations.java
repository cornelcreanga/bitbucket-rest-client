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

package com.cc.bitbucket.rest.client.model;

import java.util.Objects;

public class TaskOperations{

    private boolean editable;
    private boolean deletable;
    private boolean transitionable;

    public TaskOperations(boolean editable, boolean deletable, boolean transitionable) {
        this.editable = editable;
        this.deletable = deletable;
        this.transitionable = transitionable;
    }

    public boolean isEditable() {
        return editable;
    }

    public boolean isDeletable() {
        return deletable;
    }

    public boolean isTransitionable() {
        return transitionable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskOperations that = (TaskOperations) o;
        return Objects.equals(editable, that.editable) &&
                Objects.equals(deletable, that.deletable) &&
                Objects.equals(transitionable, that.transitionable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(editable, deletable, transitionable);
    }

    @Override
    public String toString() {
        return "TaskOperations{" +
                "editable=" + editable +
                ", deletable=" + deletable +
                ", transitionable=" + transitionable +
                '}';
    }
}
