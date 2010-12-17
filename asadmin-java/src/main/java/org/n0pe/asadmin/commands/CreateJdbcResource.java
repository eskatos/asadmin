/*
 * Copyright (c) 2010, Christophe Souvignier. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.n0pe.asadmin.commands;

import org.n0pe.asadmin.AbstractAsAdminCmd;

/**
 * @author Christophe SOUVIGNIER
 */
public class CreateJdbcResource extends  AbstractAsAdminCmd {

    public static final String RESOURCE = "create-jdbc-resource";
    public static final String CONNECTION_POOL_OPT = "--connectionpoolid";
    private String resourceName;
    private String connectionPoolId;

    /**
     * CreateJdbcResource default constructor.
     */
    private CreateJdbcResource() {
    }

    public CreateJdbcResource(String resourceName) {
        this.resourceName = resourceName;
    }

    public CreateJdbcResource withConnectionPool(String connectionPoolId) {
        this.connectionPoolId = connectionPoolId;
        return this;
    }

    public boolean needCredentials() {
        return true;
    }

    public String getActionCommand() {
        if (resourceName == null) {
            throw new IllegalStateException();
        }
        return RESOURCE;
    }

    public String[] getParameters() {
        if (connectionPoolId == null) {
            throw new IllegalStateException();
        }
        final String[] params;
        params = new String[]{CONNECTION_POOL_OPT, connectionPoolId, resourceName};
        return params;
    }
}
