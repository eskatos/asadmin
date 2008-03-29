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


import java.io.File;

import org.apache.commons.lang.SystemUtils;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import org.apache.maven.project.MavenProject;
import org.n0pe.asadmin.commands.IAsAdminCredentials;


/**
 * @author Paul Merlin <eskatos@n0pe.org>
 */
public abstract class AbstractAsadminMojo
        extends AbstractMojo
        implements IAsAdminCredentials {


    protected static final String ASADMIN = "asadmin";


    /**
     * The location to Glassfish Home. This is a required configuration
     * parameter (unless GLASSFISH_HOME or AS_HOME is set).
     * 
     * @parameter default-value="ENV"
     * @required
     */
    protected String glassfishHome;


    /**
     * The user name of admin in Glassfish server
     * 
     * @parameter default-value="admin"
     * @required
     */
    protected String user;


    /**
     * The password of admin in Glassfish server
     * 
     * @parameter default-value="HOME"
     * @required
     */
    protected String passwordfile;


    /**
     * The password of admin in Glassfish server
     * 
     * @parameter default-value="localhost"
     * @required
     */
    protected String host;


    /**
     * The password of admin in Glassfish server
     * 
     * @parameter default-value="4848"
     * @required
     */
    protected String port;


    /**
     * The password of admin in Glassfish server
     * 
     * @parameter default-value="domain1"
     * @required
     */
    protected String domain;


    /**
     * The name of the file or directory to deploy or undeploy.
     * 
     * @parameter expression="${project.build.directory}/${project.build.finalName}.${project.packaging}"
     * @required
     */
    protected String appArchive;


    /**
     * @parameter expression="${project.build.finalName}"
     * @required
     */
    protected String appName;


    /**
     * @parameter expression="${project.build.directory}"
     * @required
     */
    protected String buildDir;


    /**
     * Overrided context root for WAR archives, default to project.build.finalName.
     * 
     * @parameter expression="${project.build.finalName}"
     */
    protected String contextRoot;


    /**
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    protected MavenProject mavenProject;


    protected File glassfishHomeDir;


    public void execute()
            throws MojoExecutionException, MojoFailureException {
        displayCopyrightNotice();
        checkConfig();
    }


    public String getUser() {
        return user;
    }


    public String getPasswordFile() {
        return passwordfile;
    }


    private void checkConfig()
            throws MojoExecutionException,
                   MojoFailureException {
        if (glassfishHome == null || glassfishHome.equals("ENV")) {
            if (SystemUtils.JAVA_VERSION_FLOAT < 1.5) {
                throw new MojoExecutionException(
                        "Neither GLASSFISH_HOME, AS_HOME nor the glassfishHome configuration parameter is set! Also, to save you the trouble, environment cannot be read running maven with a VM < 1.5, so set the glassFishHome configuration parameter or use -D.");
            }
            glassfishHome = System.getenv("GLASSFISH_HOME");
            if (glassfishHome == null) {
                glassfishHome = System.getenv("AS_HOME");
            }
        }
        if (glassfishHome == null) {
            throw new MojoExecutionException(
                    "Neither GLASSFISH_HOME, AS_HOME nor the glassfishHome configuration parameter is set!");
        }
        glassfishHomeDir = new File(glassfishHome);
        if (!glassfishHomeDir.exists()) {
            throw new MojoFailureException(
                    "The specifed glassfishHome does not exist.");
        }
        if (passwordfile == null || passwordfile.equals("HOME")) {
            passwordfile = System.getenv("HOME") + File.separator +
                    ".asadminpass";
        }
    }


    private void displayCopyrightNotice() {
        getLog().info("asadmin-glassfish-plugin  Copyright (C) 2008 Paul Merlin");
        getLog().info("This program comes with ABSOLUTELY NO WARRANTY; for details type `mvn asadmin:help'.");
        getLog().info("This is free software, and you are welcome to redistribute it");
        getLog().info("under certain conditions; type `mvn asadmin:help' for details.");
    }


}
