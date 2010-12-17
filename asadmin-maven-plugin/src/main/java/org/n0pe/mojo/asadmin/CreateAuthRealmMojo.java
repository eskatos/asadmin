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
package org.n0pe.mojo.asadmin;

import java.util.Iterator;
import java.util.Map;

import org.n0pe.asadmin.AsAdminCmdList;
import org.n0pe.asadmin.commands.CreateAuthRealm;

/**
 * @goal create-auth-realm
 * @description AsAdmin create-auth-realm mojo
 * @author Paul Merlin
 */
public class CreateAuthRealmMojo
        extends AbstractAsadminMojo
{

    /**
     * @parameter default-value="com.sun.enterprise.security.auth.realm.certificate.CertificateRealm"
     * @required
     */
    private String realmClassName;
    /**
     * @parameter
     * @required
     */
    private String realmName;
    /**
     * @parameter
     */
    private Map realmProperties;

    /**
     * Usage : create-auth-realm --classname realm_class [--terse=false] [--echo=false] 
     * [--interactive=true] [--host localhost] [--port 4848|4849] [--secure | -s] 
     * [--user admin_user] [--passwordfile file_name] 
     * [--property (name=value)[:name=value]*] 
     * [--target target(Default server)] auth_realm_name
     * 
     * @return asadmin commands
     */
    @Override
    protected AsAdminCmdList getAsCommandList()
    {
        getLog().info( "Creating auth realm: " + realmName );
        final AsAdminCmdList list = new AsAdminCmdList();
        final CreateAuthRealm cmd = new CreateAuthRealm( realmName ).withClassName( realmClassName );
        if ( realmProperties != null && !realmProperties.isEmpty() ) {
            final Iterator it = realmProperties.keySet().iterator();
            while ( it.hasNext() ) {
                final String key = ( String ) it.next();
                cmd.addProperty( key, ( String ) realmProperties.get( key ) );
            }
        }
        list.add( cmd );
        return list;
    }

}
