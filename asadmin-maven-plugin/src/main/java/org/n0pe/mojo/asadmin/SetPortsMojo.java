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
import org.n0pe.asadmin.commands.Set;

/**
 * @goal set-ports
 * @description AsAdmin set-ports mojo
 * @author Paul Merlin
 */
public class SetPortsMojo
        extends AbstractAsadminMojo
{

    private static final String HTTP_LISTENER = "server.http-service.http-listener.http-listener-1.port";
    private static final String HTTPS_LISTENER = "server.http-service.http-listener.http-listener-2.port";
    /**
     * @parameter default-value="8081"
     * @required
     */
    private String httpListenerPort;
    /**
     * @parameter default-value="8181"
     * @required
     */
    private String httpsListenerPort;

    @Override
    protected AsAdminCmdList getAsCommandList()
    {
        getLog().info( "Setting listeners ports : HTTP(" + httpListenerPort
                       + ") HTTPS(" + httpsListenerPort + ")" );
        final AsAdminCmdList list = new AsAdminCmdList();
        list.add( new Set( HTTP_LISTENER, httpListenerPort ) );
        list.add( new Set( HTTPS_LISTENER, httpsListenerPort ) );
        return list;
    }

}
