/**
 * asadmin-glassfish-plugin : a maven plugin for glassfish administratives tasks
 *
 * Copyright (C) 2010 Christophe Souvignier
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
import java.util.ArrayList;
import java.util.List;
import org.n0pe.asadmin.AbstractAsAdminCmd;
import org.n0pe.asadmin.AsAdminException;


public class ListFileUser extends AbstractAsAdminCmd {

    public static final String LIST_FILE_USER = "list-file-users";


    @Override
    public boolean needCredentials() {
        return true;
    }

    @Override
    public String getActionCommand() {
        return LIST_FILE_USER;
    }

    @Override
    public String[] getParameters() {
        return new String[]{};
    }

    public List<String> getUsers() throws AsAdminException {
        try {
            String strReader;
            List<String> asadminResultList = new ArrayList<String>();
            BufferedReader reader = new BufferedReader(getStandardOutput());
            while ((strReader = reader.readLine()) != null) {
                if (strReader.length() > 0) {
                    asadminResultList.add(strReader);
                }
            }
            return asadminResultList;
        } catch (IOException ex) {
            throw new AsAdminException(ex.getCause().getMessage(), ex);
        }
    }
}
