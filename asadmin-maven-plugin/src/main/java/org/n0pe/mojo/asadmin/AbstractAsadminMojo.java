/*
 * Copyright (c) 2010, Paul Merlin. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.n0pe.mojo.asadmin;

import java.io.File;
import java.util.Collections;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

import org.n0pe.asadmin.AsAdmin;
import org.n0pe.asadmin.AsAdminException;
import org.n0pe.asadmin.AsAdminCmdList;
import org.n0pe.asadmin.IAsAdminConfig;

/**
 * Base class of all asadmin mojos.
 * Provides common parameters and basic check configuration, implements asadmin configuration provider.
 * 
 * @author Paul Merlin
 */
@SuppressWarnings( "ProtectedField" )
public abstract class AbstractAsadminMojo
        extends AbstractMojo
        implements IAsAdminConfig
{

    /**
     * @parameter default-value="false"
     */
    private boolean skip;
    /**
     * @parameter default-value="ENV"
     * @required
     */
    private String glassfishHome;
    private File glassfishHomeDir;
    /**
     * @parameter default-value="admin"
     * @required
     */
    private String user;
    /**
     * @parameter default-value="HOME"
     * @required
     */
    private String passwordfile;
    /**
     * @parameter default-value="localhost"
     * @required
     */
    private String host;
    /**
     * @parameter default-value="4848"
     * @required
     */
    private String port;
/**
     * @parameter default-value="localhost"
     * @required
     */
    protected String dbHost;
    /**
     * @parameter default-value="1527"
     * @required
     */
    protected String dbPort;
    /**
     * @parameter default-value="false"
     * @required
     */
    private boolean secure;
    /**
     * @parameter default-value="domain1"
     * @required
     */
    protected String domain;
    /**
     * @parameter expression="${project.build.directory}/${project.build.finalName}.${project.artifact.artifactHandler.extension}"
     * @required
     */
    protected String appArchive;
    /**
     * @parameter expression="${project.build.finalName}"
     * @required
     */
    protected String appName;
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

    @Override
    public final void execute()
            throws MojoExecutionException, MojoFailureException
    {
        if ( skip ) {
            getLog().info( "asadmin-maven-plugin execution is skipped" );
            return;
        }
        try {

            checkConfig();

            AsAdmin.getInstance( this ).run( getAsCommandList() );

        } catch ( AsAdminException ex ) {
            throw new MojoExecutionException( ex.getMessage(), ex );
        }
    }

    public Map<String, String> getEnvironmentVariables()
    {
        return Collections.emptyMap();
    }

    @Override
    public final String getGlassfishHome()
    {
        return glassfishHome;
    }

    @Override
    public final String getUser()
    {
        return user;
    }

    @Override
    public final String getPasswordFile()
    {
        return passwordfile;
    }

    @Override
    public String getHost()
    {
        return host;
    }

    @Override
    public String getPort()
    {
        return port;
    }

    @Override
    public boolean isSecure()
    {
        return secure;
    }

    /**
     * Build and return a list of IAsCommand to be executed.
     * 
     * AbstractAsadminMojo subclasses need to implement this method.
     * 
     * @return A list of IAsCommand to be executed
     * @throws org.apache.maven.plugin.MojoExecutionException MojoExecutionException
     * @throws org.apache.maven.plugin.MojoFailureException MojoFailureException
     */
    protected abstract AsAdminCmdList getAsCommandList()
            throws MojoExecutionException, MojoFailureException;

    private void checkConfig()
            throws MojoExecutionException, MojoFailureException
    {
        if ( StringUtils.isEmpty( glassfishHome ) || "ENV".equals( glassfishHome ) ) {
            if ( SystemUtils.JAVA_VERSION_FLOAT < 1.5 ) {
                throw new MojoExecutionException(
                        "Neither GLASSFISH_HOME, AS_HOME nor the glassfishHome configuration parameter is set! "
                        + "Also, to save you the trouble, environment cannot be read running maven with a VM < 1.5, "
                        + "so set the glassFishHome configuration parameter or use -D." );
            }
            glassfishHome = System.getenv( "GLASSFISH_HOME" );
            if ( StringUtils.isEmpty( glassfishHome ) ) {
                glassfishHome = System.getenv( "AS_HOME" );
            }
        }
        if ( StringUtils.isEmpty( glassfishHome ) ) {
            throw new MojoExecutionException(
                    "Neither GLASSFISH_HOME, AS_HOME nor the glassfishHome configuration parameter is set!" );
        }
        glassfishHomeDir = new File( glassfishHome );
        if ( !glassfishHomeDir.exists() ) {
            throw new MojoFailureException( "The specifed glassfishHome does not exist." );
        }
        if ( StringUtils.isEmpty( passwordfile ) || "HOME".equals( passwordfile ) ) {
            passwordfile = null;
            if ( new File( System.getenv( "HOME" ) + File.separator + ".asadminpass" ).exists() ) {
                passwordfile = System.getenv( "HOME" ) + File.separator + ".asadminpass";
            } else if ( new File( System.getenv( "HOME" ) + File.separator + ".asadmintruststore" ).exists() ) {
                passwordfile = System.getenv( "HOME" ) + File.separator + ".asadmintruststore";
            }
        } else if ( !new File( passwordfile ).exists() ) {
            passwordfile = null;
        }
        if ( StringUtils.isEmpty( passwordfile ) ) {
            throw new MojoFailureException(
                    "Given password file does not exists or cannot find an existing asadmin password file" );
        }
    }

}
