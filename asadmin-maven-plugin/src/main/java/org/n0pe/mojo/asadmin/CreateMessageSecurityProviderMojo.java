/*
 * Copyright (c) 2010, Christophe Souvignier. All Rights Reserved.
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

import java.util.Iterator;
import java.util.Map;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import org.n0pe.asadmin.AsAdminCmdList;
import org.n0pe.asadmin.commands.CreateMessageSecurityProvider;

/**
 * @goal create-message-security-provider
 */
public class CreateMessageSecurityProviderMojo
        extends AbstractAsadminMojo
{

    /**
     * @parameter
     * @required
     */
    private String providerName;
    /**
     * @parameter
     * @required
     */
    private String className;
    /**
     * @parameter default-value="SOAP"
     * @required
     */
    private String layer;
    /**
     * @parameter default-value="server"
     * @required
     */
    private String providerType;
    /**
     * @parameter
     */
    private Map securityProviderProperties;

    @Override
    protected AsAdminCmdList getAsCommandList()
            throws MojoExecutionException, MojoFailureException
    {
        getLog().info( "Creating auth realm: " + providerName );
        final AsAdminCmdList list = new AsAdminCmdList();
        final CreateMessageSecurityProvider cmd = new CreateMessageSecurityProvider( providerName ).withClassName( className ).
                withLayer( layer ).withProviderType( providerType );
        if ( securityProviderProperties != null && !securityProviderProperties.isEmpty() ) {
            final Iterator it = securityProviderProperties.keySet().iterator();
            while ( it.hasNext() ) {
                final String key = ( String ) it.next();
                cmd.addProperty( key, ( String ) securityProviderProperties.get( key ) );
            }
        }
        list.add( cmd );
        return list;
    }

}
