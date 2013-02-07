/*
 * Copyright (c) 2010, Paul Merlin.
 * Copyright (c) 2011, J. Francis.
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
package org.n0pe.mojo.asadmin;

import org.apache.commons.lang.StringUtils;
import org.n0pe.asadmin.AsAdminCmdList;
import org.n0pe.asadmin.commands.Deployment;

/**
 * @goal redeploy
 * @description AsAdmin redeploy mojo
 */
public class RedeployMojo
    extends AbstractAsadminMojo
{

    /**
     * @parameter default-value="false"
     */
    private boolean precompilejsp;
    /**
     * @parameter default-value=""
     */
    private String  virtualservers;

    /**
     * @parameter default-value="false"
     */
    private boolean upload;

    @Override
    protected AsAdminCmdList getAsCommandList()
    {
        getLog().info( "Redeploying application archive: " + appArchive );
        final AsAdminCmdList list = new AsAdminCmdList();
        final Deployment d = new Deployment();
        if( "war".equalsIgnoreCase( mavenProject.getPackaging() ) && !StringUtils.isEmpty( contextRoot ) )
        {
            d.withContextRoot( contextRoot );
        }
        if( !StringUtils.isEmpty( appName ) )
        {
            d.appName( appName );
        }
        list.add( d.virtualServers(virtualservers).upload( upload ).archive( appArchive ).precompilejsp( precompilejsp ).redeploy() );
        setPatterns( d );
        return list;
    }
}
