/*
 * Copyright (c) 2010, Christophe Souvignier.
 * Copyright (c) 2010, Paul Merlin.
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

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.n0pe.asadmin.AbstractAsAdminCmd;
import org.n0pe.asadmin.AsAdminException;

public class ListFileUsers
    extends AbstractAsAdminCmd
{

    public static final String LIST_FILE_USER = "list-file-users";

    @Override
    public boolean needCredentials()
    {
        return true;
    }

    @Override
    public String getActionCommand()
    {
        return LIST_FILE_USER;
    }

    @Override
    public String[] getParameters()
    {
        return new String[]
            {
            };
    }

    public List<String> getUsers()
        throws AsAdminException
    {
        try
        {
            String strReader;
            List<String> asadminResultList = new ArrayList<String>();
            BufferedReader reader = new BufferedReader( getStandardOutput() );
            while( ( strReader = reader.readLine() ) != null )
            {
                if( strReader.length() > 0 )
                {
                    asadminResultList.add( strReader );
                }
            }
            return asadminResultList;
        }
        catch( IOException ex )
        {
            throw new AsAdminException( ex.getCause().getMessage(), ex );
        }
    }

}
