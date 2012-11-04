/*
 * Copyright (c) 2011, J. Francis.
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

import org.n0pe.asadmin.AbstractAsAdminCmd;

public class GetHealth
    extends AbstractAsAdminCmd
{

    private String clusterName = null;

    public String getClusterName()
    {
        return clusterName;
    }

    public void setClusterName( String clusterName )
    {
        this.clusterName = clusterName;
    }

    @Override
    public boolean needCredentials()
    {
        return true;
    }

    @Override
    public String getActionCommand()
    {
        return "get-health";
    }

    @Override
    public String[] getParameters()
    {
        if( clusterName == null )
        {
            return EMPTY_ARRAY;
        }
        else
        {
            return new String[]
                {
                    clusterName
                };
        }
    }

}
