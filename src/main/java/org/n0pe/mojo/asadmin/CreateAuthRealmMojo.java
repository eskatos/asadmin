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


import org.n0pe.asadmin.AsAdminCmdList;
import org.n0pe.asadmin.commands.CreateAuthRealm;


/**
 * @goal create-auth-realm
 * @description AsAdmin create-auth-realm mojo
 * @author Paul Merlin <eskatos@n0pe.org>
 */
public class CreateAuthRealmMojo
        extends AbstractAsadminMojo {


    /**
     * @parameter default-value="com.sun.enterprise.security.auth.realm.certificate.CertificateRealm"
     * @required
     */
    private String realmClassName;


    /**
     * @parameter
     * @required
     */
    private String realmName;


    /**
     * @parameter
     */
    private Map realmProperties;


    /**
     * Usage : create-auth-realm --classname realm_class [--terse=false] [--echo=false] 
     * [--interactive=true] [--host localhost] [--port 4848|4849] [--secure | -s] 
     * [--user admin_user] [--passwordfile file_name] 
     * [--property (name=value)[:name=value]*] 
     * [--target target(Default server)] auth_realm_name
     * 
     * @return asadmin commands
     */
    protected AsAdminCmdList getAsCommandList() {
        getLog().info("Creating auth realm: " + realmName);
        final AsAdminCmdList list = new AsAdminCmdList();
        final CreateAuthRealm cmd = new CreateAuthRealm(realmName).withClassName(realmClassName);
        if (realmProperties != null && !realmProperties.isEmpty()) {
            final Iterator it = realmProperties.keySet().iterator();
            while (it.hasNext()) {
                final String key = (String) it.next();
                cmd.addProperty(key, (String) realmProperties.get(key));
            }
        }
        list.add(cmd);
        return list;
    }


}
