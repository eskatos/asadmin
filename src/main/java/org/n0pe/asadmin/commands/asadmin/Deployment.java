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
 * Deployment.
 *
 * @author Paul Merlin <eskatos@n0pe.org>
 */
public class Deployment
        implements IAsCommand {


    private static final int DEPLOY = 1;


    private static final int UNDEPLOY = 2;


    private int ACTION = -1;


    private String archive;


    private String component;


    private boolean force;


    public Deployment archive(String archive) {
        this.archive = archive;
        return this;
    }


    public Deployment force(boolean force) {
        this.force = force;
        return this;
    }


    public Deployment deploy() {
        ACTION = DEPLOY;
        return this;
    }


    public Deployment undeploy() {
        ACTION = UNDEPLOY;
        return this;
    }


    public Deployment component(String component) {
        this.component = component;
        return this;
    }


    public boolean needCredentials() {
        return true;
    }


    public String getActionCommand() {
        if (ACTION == DEPLOY) {
            return "deploy";
        } else if (ACTION == UNDEPLOY) {
            return "undeploy";
        } else {
            throw new IllegalStateException();
        }
    }


    public String getParameters() {
        if (ACTION == DEPLOY) {
            if (archive == null) {
                throw new IllegalStateException();
            }
            return (force ? "--force " : "") + archive;
        } else if (ACTION == UNDEPLOY) {
            if (component == null) {
                throw new IllegalStateException();
            }
            return component;
        }
        throw new IllegalStateException();
    }


}
