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
import org.n0pe.asadmin.commands.asadmin.Set;


/**
 * @goal set-ports
 * @author Paul Merlin <eskatos@n0pe.org>
 */
public class SetPortsMojo
        extends AbstractAsadminMojo {


    private static final String HTTP_LISTENER = "server.http-service.http-listener.http-listener-1.port";


    private static final String HTTPS_LISTENER = "server.http-service.http-listener.http-listener-2.port";


    /**
     * @parameter default-value="8081"
     * @required
     */
    private String httpListenerPort;


    /**
     * @parameter default-value="8181"
     * @required
     */
    private String httpsListenerPort;


    public void execute()
            throws MojoExecutionException, MojoFailureException {
        super.execute();
        getLog().info("Setting listeners ports : HTTP(" + httpListenerPort +
                      ") HTTPS(" + httpsListenerPort + ")");
        final AsAdmin asadmin = AsAdmin.getInstance(this);
        try {
            asadmin.run(new Set(HTTP_LISTENER, httpListenerPort));
            asadmin.run(new Set(HTTPS_LISTENER, httpsListenerPort));
        } catch (AsAdminException ex) {
            throw new MojoExecutionException(ex.getMessage(), ex);
        }
    }


}
