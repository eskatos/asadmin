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

public class CreateFileUser
        extends AbstractAsAdminCmd
{

    public static final String CREATE_FILE_USER = "create-file-user";
    public static final String GROUPS = "--groups";
    public static final String USER_PASSWORD_FILE = "--passwordfile";
    private String passwordFile;
    private String userName;
    private String group;

    /**
     * CreateFileUser default constructor.
     */
    private CreateFileUser()
    {
    }

    public CreateFileUser( String userName )
    {
        this.userName = userName;
    }

    public CreateFileUser withGroup( String group )
    {
        this.group = group;
        return this;
    }

    public CreateFileUser withPasswordFile( String passwordFile )
    {
        this.passwordFile = passwordFile;
        return this;
    }

    @Override
    public boolean needCredentials()
    {
        return true;
    }

    @Override
    public String getActionCommand()
    {
        if ( userName == null ) {
            throw new IllegalStateException();
        }
        return CREATE_FILE_USER;
    }

    @Override
    public String[] getParameters()
    {
        final String[] params;
        params = new String[]{ USER_PASSWORD_FILE, passwordFile, GROUPS, group, userName };
        return params;
    }

}
