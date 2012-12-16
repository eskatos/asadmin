/*
 * Copyright (c) 2012, Charles Brown.
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
package org.n0pe.asadmin.commands;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.n0pe.asadmin.AbstractAsAdminCmd;

public class Deployment
    extends AbstractAsAdminCmd
{

    public static final String DEPLOY = "deploy";
    public static final String REDEPLOY = "redeploy";
    public static final String UNDEPLOY = "undeploy";
    public static final String FORCE_OPT = "--force";
    public static final String UPLOAD_OPT = "--upload";
    public static final String CONTEXTROOT_OPT = "--contextroot";
    public static final String NAME_OPT = "--name";
    public static final String TARGET_OPT = "--target";
    public static final String AVAILABILITY_OPT = "--availabilityenabled";
    public static final String PRECOMPILE_JSP_OPT = "--precompilejsp";
    public static final String VIRTUAL_SERVERS_OPT = "--virtualservers";
    private static final int DEPLOY_MODE = 1;
    private static final int UNDEPLOY_MODE = 2;
    private static final int REDEPLOY_MODE = 3;
    private int ACTION = -1;
    private String archive;
    private String component;
    private String contextRoot;
    private String appName;
    private String target;
    private String virtualServers;
    private boolean force;
    private boolean upload;
    private Boolean precompilejsp = null;
    private Boolean availability = null;

    public Deployment deploy()
    {
        ACTION = DEPLOY_MODE;
        return this;
    }

    public Deployment undeploy()
    {
        ACTION = UNDEPLOY_MODE;
        return this;
    }
    
    public Deployment redeploy()
    {
    	ACTION = REDEPLOY_MODE;
    	return this;
    }

    public Deployment archive( String archive )
    {
        this.archive = archive;
        return this;
    }

    public Deployment component( String component )
    {
        this.component = component;
        return this;
    }

    public Deployment withContextRoot( String contextRoot )
    {
        this.contextRoot = contextRoot;
        return this;
    }

    public Deployment force( boolean force )
    {
        this.force = force;
        return this;
    }

    public Deployment upload( boolean upload )
    {
        this.upload = upload;
        return this;
    }

    public Deployment precompilejsp( boolean precompilejsp )
    {
        this.precompilejsp = precompilejsp;
        return this;
    }

    public Deployment appName( String appName )
    {
        this.appName = appName;
        return this;
    }

    public Deployment target( String target )
    {
        this.target = target;
        return this;
    }

    public Deployment availability( boolean haEnable )
    {
        this.availability = haEnable;
        return this;
    }

    public Deployment virtualServers( String virtualServers)
    {
    	this.virtualServers = virtualServers;
    	return this;
    }
    
    public boolean needCredentials()
    {
        return true;
    }

    public String getActionCommand()
    {
        if( ACTION == DEPLOY_MODE )
        {
            return DEPLOY;
        }
        else if( ACTION == UNDEPLOY_MODE )
        {
            return UNDEPLOY;
        }
        else if( ACTION == REDEPLOY_MODE )
        {
        	return REDEPLOY;
        }
        else
        {
            throw new IllegalStateException();
        }
    }
    
    public String[] getParameters()
    {
        final List<String> parameters = new ArrayList<String>();
        if( !StringUtils.isEmpty( target ) && ACTION != REDEPLOY_MODE)
        {
            parameters.add( TARGET_OPT );
            parameters.add( target );
        }
        if( ACTION == DEPLOY_MODE )
        {
            if( archive == null )
            {
                throw new IllegalStateException( "Cannot deploy without an archive" );
            }
            if( force )
            {
                parameters.add( FORCE_OPT );
            }
            if( upload )
            {
                parameters.add( UPLOAD_OPT );
            }
            if( availability != null )
            {
                parameters.add( AVAILABILITY_OPT );
                parameters.add( availability.toString() );
            }
            if( precompilejsp != null )
            {
                parameters.add( PRECOMPILE_JSP_OPT );
                parameters.add( precompilejsp.toString() );
            }
            if( !StringUtils.isEmpty( contextRoot ) )
            {
                parameters.add( CONTEXTROOT_OPT );
                parameters.add( contextRoot );
            }
            if( !StringUtils.isEmpty(virtualServers)){
            	parameters.add( VIRTUAL_SERVERS_OPT );
            	parameters.add(virtualServers);
            }
            if( !StringUtils.isEmpty( appName ) )
            {
                parameters.add( NAME_OPT );
                parameters.add( appName );
            }
            parameters.add( archive );
        }
        else if( ACTION == UNDEPLOY_MODE )
        {
            if( component == null )
            {
                throw new IllegalStateException( "Cannot undeploy without a component" );
            }
            parameters.add( component );
        }
        else if ( ACTION == REDEPLOY_MODE )
        {
        	if( StringUtils.isEmpty( appName ) )
        	{
                throw new IllegalStateException( "Cannot redeploy without sepcifying a name" );
        	}
        	parameters.add( NAME_OPT );
        	parameters.add( appName );
            if( upload )
            {
                parameters.add( UPLOAD_OPT );
            }
            if( precompilejsp != null )
            {
                parameters.add( PRECOMPILE_JSP_OPT );
                parameters.add( precompilejsp.toString() );
            }
            if( !StringUtils.isEmpty( contextRoot ) )
            {
                parameters.add( CONTEXTROOT_OPT );
                parameters.add( contextRoot );
            }
            if( !StringUtils.isEmpty(virtualServers)){
            	parameters.add( VIRTUAL_SERVERS_OPT );
            	parameters.add(virtualServers);
            }
            if( !StringUtils.isEmpty(archive) )
            {
            	parameters.add( archive );
            }
        }
        else
        {
            throw new IllegalStateException( "No action given" );
        }
        return (String[]) parameters.toArray( new String[ parameters.size() ] );
    }

}
