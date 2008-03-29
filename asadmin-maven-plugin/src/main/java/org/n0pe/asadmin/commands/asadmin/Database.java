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
package org.n0pe.asadmin.commands.asadmin;


import org.n0pe.asadmin.commands.IAsCommand;


/**
 * Database.
 *
 * @author Paul Merlin <eskatos@n0pe.org>
 */
public class Database
        implements IAsCommand {


    private static final String START = "start-database";


    private static final String STOP = "stop-database;";


    private Boolean start = null;


    /**
     * Database CTOR.
     */
    public Database() {
    }


    public Database start() {
        start = Boolean.TRUE;
        return this;
    }


    public Database stop() {
        start = Boolean.FALSE;
        return this;
    }


    public boolean needCredentials() {
        return false;
    }


    public String getActionCommand() {
        if (start == null) {
            throw new IllegalStateException();
        }
        return start.booleanValue()
                ? START
                : STOP;
    }


    public String[] getParameters() {
        return new String[]{};
    }


}
