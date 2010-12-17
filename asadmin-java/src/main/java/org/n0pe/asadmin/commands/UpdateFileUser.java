/*
 * Copyright (c) 2010, Paul Merlin. All Rights Reserved.
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

public class UpdateFileUser
        extends AbstractAsAdminCmd
{

    public static final String UPDATE_FILE_USER = "update-file-user";
    public static final String USER_PASSWORD_FILE = "--passwordfile";
    private String passwordFile;
    private String userName;

    /**
     * UpdateFileUser default constructor.
     */
    private UpdateFileUser()
    {
    }

    public UpdateFileUser( String userName )
    {
        this.userName = userName;
    }

    public UpdateFileUser withPasswordFile( String passwordFile )
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
        return UPDATE_FILE_USER;
    }

    @Override
    public String[] getParameters()
    {
        final String[] params;
        params = new String[]{ USER_PASSWORD_FILE, passwordFile, userName };
        return params;
    }

}
