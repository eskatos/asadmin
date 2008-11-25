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


import org.n0pe.asadmin.IAsAdminConfig;


/**
 * TestAsConfigProvider.
 *
 * @author Paul Merlin <eskatos@n0pe.org>
 */
public class TestAsConfigProvider
        implements IAsAdminConfig {


    public static final String HOST = "deploy.host";


    public static final String PORT = "deploy.port";


    public static final boolean SECURE = false;


    public static final String USER_NAME = "user.name";


    public static final String PASSWORD_FILE = "path.to.password.file";


    public static final String GLASSFISH_HOME = "path.to.gf.home";


    private static TestAsConfigProvider instance;


    public static TestAsConfigProvider getInstance() {
        if (instance == null) {
            instance = new TestAsConfigProvider();
        }
        return instance;
    }


    private TestAsConfigProvider() {
    }


    public String getUser() {
        return USER_NAME;
    }


    public String getPasswordFile() {
        return PASSWORD_FILE;
    }


    public String getGlassfishHome() {
        return GLASSFISH_HOME;
    }


    public String getHost() {
        return HOST;
    }


    public String getPort() {
        return PORT;
    }


    public boolean isSecure() {
        return SECURE;
    }


}
