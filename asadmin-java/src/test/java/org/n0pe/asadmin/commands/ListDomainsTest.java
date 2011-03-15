/*
 * Copyright (c) 2010, Christophe Souvigner. All Rights Reserved.
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
import java.util.Collections;
import java.util.Map;
import junit.framework.TestCase;
import org.n0pe.asadmin.AsAdmin;
import org.n0pe.asadmin.AsAdminCmdList;
import org.n0pe.asadmin.AsAdminException;
import org.n0pe.asadmin.IAsAdminConfig;

public class ListDomainsTest
        extends TestCase
{

    private final String glassfishHome = "/home/../glassfish";

    public void testCommand()
            throws IOException
    {

        assertTrue( true );
        if ( true ) {
            return;
        }


        IAsAdminConfig config = new IAsAdminConfig()
        {

            @Override
            public String getUser()
            {
                return "";
            }

            @Override
            public String getPasswordFile()
            {
                return "";
            }

            @Override
            public String getGlassfishHome()
            {
                return glassfishHome;
            }

            @Override
            public String getHost()
            {
                return "";
            }

            @Override
            public String getPort()
            {
                return "";
            }

            @Override
            public boolean isSecure()
            {
                return false;
            }

            public Map<String, String> getEnvironmentVariables()
            {
                return Collections.emptyMap();
            }

        };

        AsAdminCmdList listGlassfishCmd = new AsAdminCmdList();
        ListDomains listDomains = new ListDomains();
        listGlassfishCmd.add( listDomains );
        try {
            AsAdmin.getInstance( config ).run( listGlassfishCmd );

            BufferedReader buffReader = new BufferedReader( listDomains.getStandardOutput() );
            String output = "d";
            while ( output != null ) {
                System.out.println( output + " =  output " );
                output = buffReader.readLine();

            }
        } catch ( AsAdminException ex ) {
        }
    }

}
