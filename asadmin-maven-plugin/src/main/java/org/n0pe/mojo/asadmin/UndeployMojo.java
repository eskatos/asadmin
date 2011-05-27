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

import org.n0pe.asadmin.AsAdminCmdList;
import org.n0pe.asadmin.commands.Deployment;

/**
 * @goal undeploy
 * @description AsAdmin undeploy mojo
 * @author Paul Merlin
 */
public class UndeployMojo
        extends AbstractAsadminMojo
{

    /**
     * @parameter
     */
    private String target;

    @Override
    protected AsAdminCmdList getAsCommandList()
    {
        getLog().info( "Undeploying application: " + appName );
        final AsAdminCmdList list = new AsAdminCmdList();
        Deployment deployment = new Deployment().undeploy().component( appName ).target( target );
        setPatterns(deployment);
        list.add( deployment );
        setPatterns(deployment);
        return list;
    }

}
