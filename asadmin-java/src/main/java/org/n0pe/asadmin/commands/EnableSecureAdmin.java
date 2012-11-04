/*
 * Copyright (c) 2010, Christophe Souvignier.
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
 * This method enable DAS for remote admin console access on Glassfish v3
 */
public class EnableSecureAdmin
    extends AbstractAsAdminCmd
{

    public static final String ENABLE_SECURE_ADMIN = "enable-secure-admin";
    public static final String ADMIN_ALIAS = "--adminalias";
    public static final String INSTANCE_ALIAS = "--instancealias";
    private String adminAlias = "s1as";
    private String instanceAlias = "s1as";

    public EnableSecureAdmin withAdminAlias( String adminAlias )
    {
        this.adminAlias = adminAlias;
        return this;
    }

    public EnableSecureAdmin withInstanceAlias( String instanceAlias )
    {
        this.instanceAlias = instanceAlias;
        return this;
    }

    public boolean needCredentials()
    {
        return true;
    }

    public String getActionCommand()
    {
        return ENABLE_SECURE_ADMIN;
    }

    public String[] getParameters()
    {
        final String[] params;
        params = new String[]
        {
            ADMIN_ALIAS, adminAlias, INSTANCE_ALIAS, instanceAlias
        };
        return params;
    }

}
