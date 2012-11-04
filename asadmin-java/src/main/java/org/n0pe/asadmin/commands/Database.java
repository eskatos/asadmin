/*
 * Copyright (c) 2010, Paul Merlin. All Rights Reserved.
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
package org.n0pe.asadmin.commands;

import java.util.ArrayList;
import java.util.List;
import org.n0pe.asadmin.AbstractAsAdminCmd;

public class Database
    extends AbstractAsAdminCmd
{

    public static final String START = "start-database";
    public static final String STOP = "stop-database";
    public static final String DB_HOST = "--dbhost";
    public static final String DB_PORT = "--dbport";
    private Boolean start = null;
    private String dbHost;
    private String dbPort;

    /**
     * Database CTOR.
     */
    public Database()
    {
    }

    public Database( String dbHost, String dbPort )
    {
        this.dbHost = dbHost;
        this.dbPort = dbPort;
    }

    public Database setDbHost( String dbHost )
    {
        this.dbHost = dbHost;
        return this;
    }

    public Database setDbPort( String dbPort )
    {
        this.dbPort = dbPort;
        return this;
    }

    public Database start()
    {
        start = Boolean.TRUE;
        return this;
    }

    public Database stop()
    {
        start = Boolean.FALSE;
        return this;
    }

    public boolean needCredentials()
    {
        return false;
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
        final List<String> params = new ArrayList<String>();
        if( isSet( dbHost ) )
        {
            params.add( DB_HOST );
            params.add( dbHost );
        }
        if( isSet( dbPort ) )
        {
            params.add( DB_PORT );
            params.add( dbPort );
        }
        return params.toArray( new String[ 0 ] );
    }

    private final boolean isSet( String str )
    {
        return str != null && str.trim().length() > 0;
    }

}
