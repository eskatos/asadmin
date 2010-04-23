/**
 * asadmin-glassfish-plugin : a maven plugin for glassfish administratives tasks
 *
 * Copyright (C) 2008  Paul Merlin
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.n0pe.asadmin.commands;

import org.n0pe.asadmin.AbstractAsAdminCmd;

/**
 * CreateJdbcResource.
 *
 * @author Christophe SOUVIGNIER <chris.so@free.fr>
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
