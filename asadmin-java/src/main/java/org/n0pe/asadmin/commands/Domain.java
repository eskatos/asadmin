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

import org.n0pe.asadmin.AbstractAsAdminCmd;

/**
 * @author Paul Merlin
 */
public class Domain
        extends AbstractAsAdminCmd
{

    public static final String START = "start-domain";
    public static final String STOP = "stop-domain";
    private Boolean start = null;
    private String domain;

    private Domain()
    {
    }

    public Domain( String domain )
    {
        this.domain = domain;
    }

    public Domain start()
    {
        start = Boolean.TRUE;
        return this;
    }

    public Domain stop()
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
        if ( start == null ) {
            throw new IllegalStateException();
        }
        return start.booleanValue()
               ? START
               : STOP;
    }

    public String[] getParameters()
    {
        if ( start == null ) {
            throw new IllegalStateException();
        }
        return new String[]{ domain };
    }

}
