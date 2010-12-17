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

import org.n0pe.asadmin.AbstractAsAdminCmd;


public class UpdateFileUser extends AbstractAsAdminCmd {

    public static final String UPDATE_FILE_USER = "update-file-user";
    public static final String USER_PASSWORD_FILE = "--passwordfile";
    private String passwordFile;
    private String userName;

    /**
     * UpdateFileUser default constructor.
     */
    private UpdateFileUser() {
    }

    public UpdateFileUser(String userName) {
        this.userName = userName;
    }

    public UpdateFileUser withPasswordFile(String passwordFile) {
        this.passwordFile = passwordFile;
        return this;
    }

    @Override
    public boolean needCredentials() {
        return true;
    }

    @Override
    public String getActionCommand() {
        if (userName == null) {
            throw new IllegalStateException();
        }
        return UPDATE_FILE_USER;
    }

    @Override
    public String[] getParameters() {
        final String[] params;
        params = new String[]{USER_PASSWORD_FILE, passwordFile, userName};
        return params;
    }

}
