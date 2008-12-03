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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.n0pe.asadmin.IAsAdminCmd;
import org.n0pe.asadmin.Util;

/**
 * CreateMessageSecurityProvider.
 *
 * @author Christophe SOUVIGNIER <chris.so@free.fr>
 */
public class CreateMessageSecurityProvider implements IAsAdminCmd {

    public static final String SECURITY_PROVIDER = "create-message-security-provider";
    public static final String LAYER_OPT = "--layer";
    public static final String PROVIDER_TYPE_OPT = "--providertype";
    public static final String CLASSNAME = "--classname";
    public static final String PROPERTY_OPT = "--property";
    private String providerName;
    private String providerType;
    private String layer;
    private String classname;
    private Map properties;

    private CreateMessageSecurityProvider() {
    }

    public CreateMessageSecurityProvider(String providerName) {
        this.providerName = providerName;
    }

    public CreateMessageSecurityProvider withLayer(String layer) {
        this.layer = layer;
        return this;
    }

    public CreateMessageSecurityProvider withProviderType(String providerType) {
        this.providerType = providerType;
        return this;
    }

    public CreateMessageSecurityProvider withClassName(String classname) {
        this.classname = classname;
        return this;
    }

    public CreateMessageSecurityProvider addProperty(String key, String value) {
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
        if (providerName == null) {
            throw new IllegalStateException();
        }
        return SECURITY_PROVIDER;
    }

    public String[] getParameters() {
        if ((providerType == null) || (layer == null) || (classname == null)) {
            throw new IllegalStateException();
        }
        final String[] params;
        if (properties != null && !properties.isEmpty()) {
            final StringBuffer sw = new StringBuffer();
            String key;
            for (final Iterator it = properties.keySet().iterator(); it.hasNext();) {
                key = (String) it.next();
                sw.append(key).append("=").append(Util.quoteCommandArgument((String) properties.get(key)));
                if (it.hasNext()) {
                    sw.append(":");
                }
            }
            params = new String[]{LAYER_OPT, layer, PROVIDER_TYPE_OPT, providerType, CLASSNAME, classname, PROPERTY_OPT, sw.toString(), providerName};

        } else {
            params = new String[]{LAYER_OPT, layer, PROVIDER_TYPE_OPT, providerType, CLASSNAME, classname, providerName};
        }
        return params;
    }
}
