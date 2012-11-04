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

import java.util.ArrayList;
import java.util.List;
import org.n0pe.asadmin.AbstractAsAdminCmd;

public class AddResources
    extends AbstractAsAdminCmd
{

    private Boolean upload = null;
    private String target = null;
    private String fileLocation = null;

    public Boolean isUpload()
    {
        return upload;
    }

    public AddResources setUpload( Boolean upload )
    {
        this.upload = upload;
        return this;
    }

    public String getTarget()
    {
        return target;
    }

    public AddResources setTarget( String target )
    {
        this.target = target;
        return this;
    }

    public String getFileLocation()
    {
        return fileLocation;
    }

    public AddResources setFileLocation( String fileLocation )
    {
        this.fileLocation = fileLocation;
        return this;
    }

    @Override
    public boolean needCredentials()
    {
        return true;
    }

    @Override
    public String getActionCommand()
    {
        return "add-resources";
    }

    @Override
    public String[] getParameters()
    {
        List<String> params = new ArrayList<String>( 6 );
        if( target != null )
        {
            params.add( "--target" );
            params.add( target );
        }
        if( upload != null )
        {
            params.add( "--upload" );
            params.add( upload.toString() );
        }
        if( fileLocation == null )
        {
            throw new IllegalStateException( "asadmin add-resources requires a file parameter" );
        }
        params.add( fileLocation );
        return params.toArray( new String[ params.size() ] );
    }

}
