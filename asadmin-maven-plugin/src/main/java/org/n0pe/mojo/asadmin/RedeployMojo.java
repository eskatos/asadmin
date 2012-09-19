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

import org.apache.commons.lang.StringUtils;

import org.n0pe.asadmin.AsAdminCmdList;
import org.n0pe.asadmin.commands.Deployment;

/**
 * @goal redeploy
 * @description AsAdmin redeploy mojo
 * @author Paul Merlin
 */
public class RedeployMojo
        extends AbstractAsadminMojo
{

    /**
     * @parameter
     */
    private String target;

    /**
     * @parameter
     */
    private Boolean availabilityenabled = null;

	/**
	 * @parameter
	 */
	private Boolean precompilejsp = null;

    @Override
    protected AsAdminCmdList getAsCommandList()
    {
        getLog().info( "Redeploying application archive: " + appArchive );
        final AsAdminCmdList list = new AsAdminCmdList();
        final Deployment d = new Deployment().archive( appArchive ).target( target ).precompilejsp(precompilejsp);
        if ( "war".equalsIgnoreCase( mavenProject.getPackaging() ) && !StringUtils.isEmpty( contextRoot ) ) {
            d.withContextRoot( contextRoot );
        }
        if ( !StringUtils.isEmpty( appName ) ) {
            d.appName( appName );
        }
        list.add( d.force( true ).availability(availabilityenabled).precompilejsp(precompilejsp).deploy() );
        setPatterns(d);
        return list;
    }

}
