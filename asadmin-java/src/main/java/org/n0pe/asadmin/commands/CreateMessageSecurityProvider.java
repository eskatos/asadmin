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
 * @author Christophe SOUVIGNIER
 */
public class CreateMessageSecurityProvider
        extends AbstractAsAdminCmd
{

    public static final String SECURITY_PROVIDER = "create-message-security-provider";
    public static final String LAYER_OPT = "--layer";
    public static final String PROVIDER_TYPE_OPT = "--providertype";
    public static final String CLASSNAME = "--classname";
    public static final String PROPERTY_OPT = "--property";
    private String providerName;
    private String providerType;
    private String layer;
    private String classname;
    private Map properties;

    private CreateMessageSecurityProvider()
    {
    }

    public CreateMessageSecurityProvider( String providerName )
    {
        this.providerName = providerName;
    }

    public CreateMessageSecurityProvider withLayer( String layer )
    {
        this.layer = layer;
        return this;
    }

    public CreateMessageSecurityProvider withProviderType( String providerType )
    {
        this.providerType = providerType;
        return this;
    }

    public CreateMessageSecurityProvider withClassName( String classname )
    {
        this.classname = classname;
        return this;
    }

    public CreateMessageSecurityProvider addProperty( String key, String value )
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
        if ( providerName == null ) {
            throw new IllegalStateException();
        }
        return SECURITY_PROVIDER;
    }

    public String[] getParameters()
    {
        if ( ( providerType == null ) || ( layer == null ) || ( classname == null ) ) {
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
            params = new String[]{ LAYER_OPT, layer, PROVIDER_TYPE_OPT, providerType, CLASSNAME, classname, PROPERTY_OPT, sw.toString(), providerName };

        } else {
            params = new String[]{ LAYER_OPT, layer, PROVIDER_TYPE_OPT, providerType, CLASSNAME, classname, providerName };
        }
        return params;
    }

}
