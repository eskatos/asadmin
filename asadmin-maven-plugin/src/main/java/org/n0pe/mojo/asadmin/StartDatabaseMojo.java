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
package org.n0pe.mojo.asadmin;


import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import org.n0pe.asadmin.commands.AsAdmin;
import org.n0pe.asadmin.commands.AsAdminException;
import org.n0pe.asadmin.commands.asadmin.Database;


/**
 * @goal start-database
 * @description Maven 2 Glassfish plugin
 * @author Paul Merlin <eskatos@n0pe.org>
 */
public class StartDatabaseMojo
        extends AbstractAsadminMojo {


    public void execute()
            throws MojoExecutionException, MojoFailureException {
        super.execute();
        getLog().info("Starting database");
        final AsAdmin asadmin = AsAdmin.getInstance(this);
        try {
            asadmin.run(new Database().start());
        } catch (AsAdminException ex) {
            throw new MojoExecutionException(ex.getMessage(), ex);
        }
    }


}
