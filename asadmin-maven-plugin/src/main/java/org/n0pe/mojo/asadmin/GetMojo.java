/*
 * Copyright (c) 2010, Paul Merlin.
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
import org.n0pe.asadmin.commands.Get;

/**
 * @goal get
 * @description AsAdmin set property
 */
public class GetMojo
    extends AbstractAsadminMojo
{

    /**
     * @parameter
     * @required
     */
    private String[] properties;

    @Override
    protected AsAdminCmdList getAsCommandList()
    {
        getLog().info( "Getting property : " + properties );
        final AsAdminCmdList list = new AsAdminCmdList();
        for( int i = 0; i < properties.length; i++ )
        {
            list.add( new Get( properties[i] ) );
        }
        return list;
    }

}
