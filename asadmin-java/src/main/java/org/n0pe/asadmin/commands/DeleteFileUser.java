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

public class DeleteFileUser
    extends AbstractAsAdminCmd
{

    public static final String DELETE_FILE_USER = "delete-file-user";
    private String userName;

    /**
     * DeleteFileUser default constructor.
     */
    private DeleteFileUser()
    {
    }

    public DeleteFileUser( String userName )
    {
        this.userName = userName;
    }

    @Override
    public boolean needCredentials()
    {
        return true;
    }

    @Override
    public String getActionCommand()
    {
        if( userName == null )
        {
            throw new IllegalStateException();
        }
        return DELETE_FILE_USER;
    }

    @Override
    public String[] getParameters()
    {
        final String[] params;
        params = new String[]
        {
            userName
        };
        return params;
    }

}
