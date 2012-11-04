/*
 * Copyright (c) 2010, J. Francis.
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

public class Cluster
    extends AbstractAsAdminCmd
{

    public static final String START = "start-cluster";
    public static final String STOP = "stop-cluster";
    private Boolean start = null;
    private String cluster;

    public Cluster( String cluster )
    {
        this.cluster = cluster;
    }

    public Cluster start()
    {
        start = Boolean.TRUE;
        return this;
    }

    public Cluster stop()
    {
        start = Boolean.FALSE;
        return this;
    }

    public boolean needCredentials()
    {
        return true;
    }

    public String getActionCommand()
    {
        if( start == null )
        {
            throw new IllegalStateException();
        }
        return start.booleanValue()
               ? START
               : STOP;
    }

    public String[] getParameters()
    {
        if( start == null )
        {
            throw new IllegalStateException();
        }
        return new String[]
            {
                cluster
            };
    }

}
