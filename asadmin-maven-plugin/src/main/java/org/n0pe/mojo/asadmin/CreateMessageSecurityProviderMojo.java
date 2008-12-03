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

import java.util.Iterator;
import java.util.Map;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.n0pe.asadmin.AsAdminCmdList;
import org.n0pe.asadmin.commands.CreateMessageSecurityProvider;

/**
 * CreateMessageSecurityProvider.
 *
 * @author Christophe SOUVIGNIER <chris.so@free.fr>
 */
public class CreateMessageSecurityProviderMojo extends AbstractAsadminMojo {

    /**
     * @parameter
     * @required
     */
    private String providerName;

    /**
     * @parameter
     * @required
     */
    private String className;

    /**
     * @parameter default-value="SOAP"
     * @required
     */
    private String layer;

    /**
     * @parameter default-value="server"
     * @required
     */
    private String providerType;

    /**
     * @parameter
     */
    private Map securityProviderProperties;

    protected AsAdminCmdList getAsCommandList() throws MojoExecutionException, MojoFailureException {
        getLog().info("Creating auth realm: " + providerName);
        final AsAdminCmdList list = new AsAdminCmdList();
        final CreateMessageSecurityProvider cmd = new CreateMessageSecurityProvider(providerName).withClassName(className).
                withLayer(layer).withProviderType(providerType);
        if (securityProviderProperties != null && !securityProviderProperties.isEmpty()) {
            final Iterator it = securityProviderProperties.keySet().iterator();
            while (it.hasNext()) {
                final String key = (String) it.next();
                cmd.addProperty(key, (String) securityProviderProperties.get(key));
            }
        }
        list.add(cmd);
        return list;
    }

}
