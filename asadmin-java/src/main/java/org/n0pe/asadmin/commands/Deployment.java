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
package org.n0pe.asadmin.commands;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import org.n0pe.asadmin.AbstractAsAdminCmd;

/**
 * @author Paul Merlin
 */
public class Deployment
        extends AbstractAsAdminCmd
{

    public static final String DEPLOY = "deploy";
    public static final String UNDEPLOY = "undeploy";
    public static final String FORCE_OPT = "--force";
    public static final String CONTEXTROOT_OPT = "--contextroot";
    public static final String NAME_OPT = "--name";
    public static final String TARGET_OPT = "--target";
    private static final int DEPLOY_MODE = 1;
    private static final int UNDEPLOY_MODE = 2;
    private int ACTION = -1;
    private String archive;
    private String component;
    private String contextRoot;
    private String appName;
    private String target;
    private boolean force;

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

    public boolean needCredentials()
    {
        return true;
    }

    public String getActionCommand()
    {
        if ( ACTION == DEPLOY_MODE ) {
            return DEPLOY;
        } else if ( ACTION == UNDEPLOY_MODE ) {
            return UNDEPLOY;
        } else {
            throw new IllegalStateException();
        }
    }

    public String[] getParameters()
    {
        final List parameters = new ArrayList();
        if ( !StringUtils.isEmpty( target ) ) {
            parameters.add( TARGET_OPT );
            parameters.add( target );
        }
        if ( ACTION == DEPLOY_MODE ) {
            if ( archive == null ) {
                throw new IllegalStateException( "Cannot deploy without an archive" );
            }
            if ( force ) {
                parameters.add( FORCE_OPT );
            }
            if ( !StringUtils.isEmpty( contextRoot ) ) {
                parameters.add( CONTEXTROOT_OPT );
                parameters.add( contextRoot );
            }
            if ( !StringUtils.isEmpty( appName ) ) {
                parameters.add( NAME_OPT );
                parameters.add( appName );
            }
            parameters.add( archive );
        } else if ( ACTION == UNDEPLOY_MODE ) {
            if ( component == null ) {
                throw new IllegalStateException( "Cannot undeploy without a component" );
            }
            parameters.add( component );
        } else {
            throw new IllegalStateException( "No action given" );
        }
        return ( String[] ) parameters.toArray( new String[ parameters.size() ] );
    }

}
