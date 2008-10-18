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


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import org.n0pe.asadmin.IAsAdminCmd;


/**
 * Deployment.
 *
 * @author Paul Merlin <eskatos@n0pe.org>
 */
public class Deployment
        implements IAsAdminCmd {


    public static final String DEPLOY = "deploy";


    public static final String UNDEPLOY = "undeploy";


    public static final String FORCE_OPT = "--force";


    public static final String CONTEXTROOT_OPT = "--contextroot";


    public static final String NAME_OPT = "--name";


    private static final int DEPLOY_MODE = 1;


    private static final int UNDEPLOY_MODE = 2;


    private int ACTION = -1;


    private String archive;


    private String component;


    private String contextRoot;


    private String appName;


    private boolean force;


    public Deployment deploy() {
        ACTION = DEPLOY_MODE;
        return this;
    }


    public Deployment undeploy() {
        ACTION = UNDEPLOY_MODE;
        return this;
    }


    public Deployment archive(String archive) {
        this.archive = archive;
        return this;
    }


    public Deployment component(String component) {
        this.component = component;
        return this;
    }


    public Deployment withContextRoot(String contextRoot) {
        this.contextRoot = contextRoot;
        return this;
    }


    public Deployment force(boolean force) {
        this.force = force;
        return this;
    }


    public Deployment appName(String appName) {
        this.appName = appName;
        return this;
    }


    public boolean needCredentials() {
        return true;
    }


    public String getActionCommand() {
        if (ACTION == DEPLOY_MODE) {
            return DEPLOY;
        } else if (ACTION == UNDEPLOY_MODE) {
            return UNDEPLOY;
        } else {
            throw new IllegalStateException();
        }
    }


    public String[] getParameters() {
        if (ACTION == DEPLOY_MODE) {
            if (archive == null) {
                throw new IllegalStateException("Cannot deploy without an archive");
            }
            final List parameters = new ArrayList();
            if (force) {
                parameters.add(FORCE_OPT);
            }
            if (!StringUtils.isEmpty(contextRoot)) {
                parameters.add(CONTEXTROOT_OPT);
                parameters.add(contextRoot);
            }
            if (!StringUtils.isEmpty(appName)) {
                parameters.add(NAME_OPT);
                parameters.add(appName);
            }
            parameters.add(archive);
            return (String[]) parameters.toArray(new String[parameters.size()]);
        } else if (ACTION == UNDEPLOY_MODE) {
            if (component == null) {
                throw new IllegalStateException("Cannot undeploy without a component");
            }
            return new String[]{component};
        }
        throw new IllegalStateException();
    }


}
