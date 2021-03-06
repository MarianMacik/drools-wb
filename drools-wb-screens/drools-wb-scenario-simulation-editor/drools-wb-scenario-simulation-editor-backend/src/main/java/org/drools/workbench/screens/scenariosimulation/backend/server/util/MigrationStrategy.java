/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.workbench.screens.scenariosimulation.backend.server.util;

import java.util.function.Function;

/**
 * Interface to define the migration strategy for scesim files
 */
public interface MigrationStrategy {

    /**
     * Method to initialize migration strategy composition
     * @return
     */
    default Function<String, String> start() {
        return Function.identity();
    }

    /**
     * Method to obtain the migration function from 1.0 to 1.1
     * @return
     */
    Function<String, String> from1_0to1_1();

    /**
     * Method to obtain the migration function from 1.1 to 1.2
     * @return
     */
    Function<String, String> from1_1to1_2();

    /**
     * Method to complete the migration. For instance it can be used to store the new value
     * @return
     */
    default Function<String, String> end() {
        return Function.identity();
    }
}
