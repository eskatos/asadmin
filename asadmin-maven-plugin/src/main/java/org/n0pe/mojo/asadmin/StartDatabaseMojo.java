/*
 * Copyright (c) 2010, Paul Merlin.
 * Copyright (c) 2011, Marenz.
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
import org.n0pe.asadmin.commands.Database;

/**
 * @goal start-database
 * @description AsAdmin start-database mojo
 */
public class StartDatabaseMojo
    extends AbstractAsadminMojo
{

    @Override
    protected AsAdminCmdList getAsCommandList()
    {
        getLog().info( "Starting database" );
        final AsAdminCmdList list = new AsAdminCmdList();
        list.add( new Database( dbHost, dbPort ).start() );
        return list;
    }

}
