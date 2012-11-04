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
package org.n0pe.asadmin.commands;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.n0pe.asadmin.AbstractAsAdminCmd;
import org.n0pe.asadmin.Util;

public class CreateAuthRealm
    extends AbstractAsAdminCmd
{

    private static final String CLASSNAME_OPT = "--classname";
    private static final String PROPERTY_OPT = "--property";
    private String realmName;
    private String className;
    private Map properties;

    private CreateAuthRealm()
    {
    }

    public CreateAuthRealm( String realmName )
    {
        this.realmName = realmName;
    }

    public CreateAuthRealm withClassName( String className )
    {
        this.className = className;
        return this;
    }

    public CreateAuthRealm addProperty( String key, String value )
    {
        if( properties == null )
        {
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
        return "create-auth-realm";
    }

    public String[] getParameters()
    {
        if( StringUtils.isEmpty( className ) )
        {
            throw new IllegalStateException();
        }
        final String[] params;
        if( properties != null && !properties.isEmpty() )
        {
            final StringWriter sw = new StringWriter();
            String key;
            for( final Iterator it = properties.keySet().iterator(); it.hasNext(); )
            {
                key = (String) it.next();
                sw.append( key ).append( "=" ).append( Util.quoteCommandArgument( (String) properties.get( key ) ) );
                if( it.hasNext() )
                {
                    sw.append( ":" );
                }
            }
            params = new String[]
            {
                CLASSNAME_OPT, className, PROPERTY_OPT, sw.toString(), realmName
            };

        }
        else
        {
            params = new String[]
            {
                CLASSNAME_OPT, className, realmName
            };
        }
        return params;
    }

}
