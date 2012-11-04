/*
 * Copyright (c) 2010, Christophe Souvignier.
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

public class DeleteMessageSecurityProvider
    extends AbstractAsAdminCmd
{

    public static final String SECURITY_PROVIDER = "delete-message-security-provider";
    public static final String LAYER_OPT = "--layer";
    private String providerName;
    private String layer;

    private DeleteMessageSecurityProvider()
    {
    }

    public DeleteMessageSecurityProvider( String providerName )
    {
        this.providerName = providerName;
    }

    public DeleteMessageSecurityProvider withLayer( String layer )
    {
        this.layer = layer;
        return this;
    }

    public boolean needCredentials()
    {
        return true;
    }

    public String getActionCommand()
    {
        if( providerName == null )
        {
            throw new IllegalStateException();
        }
        return SECURITY_PROVIDER;
    }

    public String[] getParameters()
    {
        if( layer == null )
        {
            throw new IllegalStateException();
        }
        return new String[]
            {
                LAYER_OPT, layer, providerName
            };
    }

}
