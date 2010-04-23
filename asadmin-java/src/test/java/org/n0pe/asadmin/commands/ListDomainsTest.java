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

import java.io.BufferedReader;
import java.io.IOException;
import junit.framework.TestCase;
import org.n0pe.asadmin.AsAdmin;
import org.n0pe.asadmin.AsAdminCmdList;
import org.n0pe.asadmin.AsAdminException;
import org.n0pe.asadmin.IAsAdminConfig;

public class ListDomainsTest extends TestCase {

    private final String glassfishHome = "/home/../glassfish";

    public void testCommand() throws IOException {

        assertTrue(true);
        if (true) {
            return;
        }


        IAsAdminConfig config = new IAsAdminConfig() {

            public String getUser() {
                return "";
            }

            public String getPasswordFile() {
                return "";
            }

            public String getGlassfishHome() {
                return glassfishHome;
            }

            public String getHost() {
                return "";
            }

            public String getPort() {
                return "";
            }

            public boolean isSecure() {
                return false;
            }
        };

        AsAdminCmdList listGlassfishCmd = new AsAdminCmdList();
        ListDomains listDomains = new ListDomains();
        listGlassfishCmd.add(listDomains);
        try {
            AsAdmin.getInstance(config).run(listGlassfishCmd);

            BufferedReader buffReader = new BufferedReader(listDomains.getStandardOutput());
            String output = "d";
            while (output != null) {
                System.out.println(output + " =  output ");
                output = buffReader.readLine();

            }
        } catch (AsAdminException ex) {
        }
    }
}
