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

import org.n0pe.asadmin.IAsAdminCmd;

/**
 * DeleteMessageSecurityProvider.
 *
 * @author Christophe SOUVIGNIER <chris.so@free.fr>
 */
public class DeleteMessageSecurityProvider implements IAsAdminCmd {

    public static final String SECURITY_PROVIDER = "delete-message-security-provider";
    public static final String LAYER_OPT = "--layer";
    private String providerName;
    private String layer;

    private DeleteMessageSecurityProvider() {
    }

    public DeleteMessageSecurityProvider(String providerName) {
        this.providerName = providerName;
    }

    public DeleteMessageSecurityProvider withLayer(String layer) {
        this.layer = layer;
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
        if (layer == null) {
            throw new IllegalStateException();
        }
        return new String[]{LAYER_OPT, layer, providerName};
    }
}
