/*
 * Copyright (c) 2010, Christophe Souvignier. All Rights Reserved.
 * Copyright (c) 2011, Paul Merlin. All Rights Reserved.
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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import org.n0pe.asadmin.AbstractAsAdminCmd;
import org.n0pe.asadmin.AsAdminException;

public class CreateFileUser
        extends AbstractAsAdminCmd
{

    public static final String CREATE_FILE_USER = "create-file-user";
    public static final String GROUPS = "--groups";
    private String userName;
    private char[] password;
    private String group;

    private CreateFileUser()
    {
    }

    public CreateFileUser( String userName )
    {
        this.userName = userName;
    }

    public CreateFileUser withPassword( char[] password )
    {
        this.password = password;
        return this;
    }

    public CreateFileUser withGroup( String group )
    {
        this.group = group;
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
        params = new String[]{ GROUPS, group, userName };
        return params;
    }

    @Override
    public String handlePasswordFile( String configuredPasswordFile )
            throws AsAdminException
    {
        try {
            File passwordTempFile = File.createTempFile( "asadmin-create-file-user", ".pwd" );
            passwordTempFile.deleteOnExit();
            FileUtils.copyFile( new File( configuredPasswordFile ), passwordTempFile );
            BufferedWriter out = new BufferedWriter( new FileWriter( passwordTempFile ) );
            out.write( "AS_ADMIN_USERPASSWORD=" + new String( password ) );
            out.close();
            return passwordTempFile.getAbsolutePath();
        } catch ( IOException ex ) {
            throw new AsAdminException( "Unable to handle password file for CreateFileUser command", ex );
        }
    }

}
