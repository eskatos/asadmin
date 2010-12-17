/*
 * Copyright (c) 2010, Christophe Souvignier. All Rights Reserved.
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
import org.n0pe.asadmin.commands.CreateJdbcConnectionPool;

/**
 * @author Christophe SOUVIGNIER
 */
public class CreateJdbcConnectionPoolMojo
        extends AbstractAsadminMojo
{

    /**
     * @parameter default-value="org.apache.derby.jdbc.ClientXADataSource"
     * @required
     */
    private String poolDataSource;
    /**
     * @parameter
     * @required
     */
    private String poolName;
    /**
     * @parameter default-value="javax.sql.XADataSource"
     * @required
     */
    private String restype;
    /**
     * @parameter
     */
    private Map poolProperties;

    @Override
    protected AsAdminCmdList getAsCommandList()
            throws MojoExecutionException, MojoFailureException
    {
        getLog().info( "Creating auth realm: " + poolName );
        final AsAdminCmdList list = new AsAdminCmdList();
        final CreateJdbcConnectionPool cmd = new CreateJdbcConnectionPool( poolName ).withDataSource( poolDataSource ).withRestype( restype );
        if ( poolProperties != null && !poolProperties.isEmpty() ) {
            final Iterator it = poolProperties.keySet().iterator();
            while ( it.hasNext() ) {
                final String key = ( String ) it.next();
                cmd.addProperty( key, ( String ) poolProperties.get( key ) );
            }
        }
        list.add( cmd );
        return list;
    }

}
