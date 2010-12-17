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
package org.n0pe.asadmin.commands;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.n0pe.asadmin.AbstractAsAdminCmd;
import org.n0pe.asadmin.Util;

/**
 * @author Christophe Souvignier
 */
public class CreateJdbcConnectionPool
        extends AbstractAsAdminCmd
{

    public static final String JDBC = "create-jdbc-connection-pool";
    public static final String DATA_SOURCE_OPT = "--datasourceclassname";
    public static final String RESTYPE_OPT = "--restype";
    public static final String PROPERTY_OPT = "--property";
    private String poolName;
    private String dataSource;
    private String restype;
    private Map properties;

    /**
     * CreateJdbcConnectionPool default constructor.
     */
    private CreateJdbcConnectionPool()
    {
    }

    public CreateJdbcConnectionPool( String poolName )
    {
        this.poolName = poolName;
    }

    public CreateJdbcConnectionPool withDataSource( String dataSource )
    {
        this.dataSource = dataSource;
        return this;
    }

    public CreateJdbcConnectionPool withRestype( String restype )
    {
        this.restype = restype;
        return this;
    }

    public CreateJdbcConnectionPool addProperty( String key, String value )
    {
        if ( properties == null ) {
            properties = new HashMap();
        }
        properties.put( key, value );
        return this;
    }

    public boolean needCredentials()
    {
        return true;
    }

    public String getActionCommand()
    {
        if ( poolName == null ) {
            throw new IllegalStateException();
        }
        return JDBC;
    }

    public String[] getParameters()
    {
        if ( ( dataSource == null ) || ( restype == null ) ) {
            throw new IllegalStateException();
        }
        final String[] params;
        if ( properties != null && !properties.isEmpty() ) {
            final StringBuffer sw = new StringBuffer();
            String key;
            for ( final Iterator it = properties.keySet().iterator(); it.hasNext(); ) {
                key = ( String ) it.next();
                sw.append( key ).append( "=" ).append( Util.quoteCommandArgument( ( String ) properties.get( key ) ) );
                if ( it.hasNext() ) {
                    sw.append( ":" );
                }
            }
            params = new String[]{ DATA_SOURCE_OPT, dataSource, RESTYPE_OPT, restype, PROPERTY_OPT, sw.toString(), poolName };

        } else {
            params = new String[]{ DATA_SOURCE_OPT, dataSource, RESTYPE_OPT, restype, poolName };
        }
        return params;
    }

}
