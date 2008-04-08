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


import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import org.n0pe.asadmin.IAsCommand;


/**
 * CreateAuthRealm.
 *
 * @author Paul Merlin <eskatos@n0pe.org>
 */
public class CreateAuthRealm
        implements IAsCommand {


    private static final String CLASSNAME_OPT = "--classname";


    private static final String PROPERTY_OPT = "--property";


    private String realmName;


    private String className;


    private Map properties;


    private CreateAuthRealm() {
    }


    public CreateAuthRealm(String realmName) {
        this.realmName = realmName;
    }


    public CreateAuthRealm withClassName(String className) {
        this.className = className;
        return this;
    }


    public CreateAuthRealm addProperty(String key, String value) {
        if (properties == null) {
            properties = new HashMap();
        }
        properties.put(key, value);
        return this;
    }


    public boolean needCredentials() {
        return true;
    }


    public String getActionCommand() {
        return "create-auth-realm";
    }


    public String[] getParameters() {
        if (StringUtils.isEmpty(className)) {
            throw new IllegalStateException();
        }
        final String[] params;
        if (properties != null && !properties.isEmpty()) {
            final StringWriter sw = new StringWriter();
            String key;
            for (final Iterator it = properties.keySet().iterator(); it.hasNext();) {
                key = (String) it.next();
                sw.append(key).append("=").append((String) properties.get(key));
                if (it.hasNext()) {
                    sw.append(":");
                }
            }
            params = new String[]{CLASSNAME_OPT, className, PROPERTY_OPT, sw.toString(), realmName};

        } else {
            params = new String[]{CLASSNAME_OPT, className, realmName};
        }
        return params;
    }


}
