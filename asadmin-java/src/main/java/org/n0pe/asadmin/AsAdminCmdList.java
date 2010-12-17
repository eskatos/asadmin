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
package org.n0pe.asadmin;

import java.util.ArrayList;

/**
 * An ArrayList containing IAsCommands.
 * Generics in maven ?
 *
 * @author Paul Merlin <eskatos@n0pe.org>
 */
public class AsAdminCmdList
        extends ArrayList
{

    private static final long serialVersionUID = 1L;

    /**
     * AsAdminCmdList CTOR.
     */
    public AsAdminCmdList()
    {
        super();
    }

    public boolean add( IAsAdminCmd e )
    {
        return super.add( e );
    }

    public void add( int index, IAsAdminCmd element )
    {
        super.add( index, element );
    }

    public boolean contains( IAsAdminCmd o )
    {
        return super.contains( o );
    }

    public int indexOf( IAsAdminCmd o )
    {
        return super.indexOf( o );
    }

    public int lastIndexOf( IAsAdminCmd o )
    {
        return super.lastIndexOf( o );
    }

    public boolean remove( IAsAdminCmd o )
    {
        return super.remove( o );
    }

    public IAsAdminCmd set( int index, IAsAdminCmd element )
    {
        return ( IAsAdminCmd ) super.set( index, element );
    }

    public IAsAdminCmd[] toArray( IAsAdminCmd[] a )
    {
        return ( IAsAdminCmd[] ) super.toArray( a );
    }

}
