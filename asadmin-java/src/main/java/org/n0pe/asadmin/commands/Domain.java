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
    public static final String RESTART = "restart-domain";
    public static final int START_MODE = 1;
    public static final int STOP_MODE = 2;
    public static final int RESTART_MODE = 3;
    private int ACTION = -1;
    private String domain;

    public Domain()
    {
        //restart-domain does not require a domain name to be
        //provided.
    }

    public Domain( String domain )
    {
        this.domain = domain;
    }

    public Domain start()
    {
        ACTION = START_MODE;
        return this;
    }

    public Domain stop()
    {
        ACTION = STOP_MODE;
        return this;
    }

    public Domain restart()
    {
        ACTION = RESTART_MODE;
        return this;
    }

    public boolean needCredentials()
    {
        return (ACTION == RESTART_MODE);
    }

    public String getActionCommand()
    {
        if (ACTION == START_MODE) {
            return START;
        } else if (ACTION == STOP_MODE) {
            return STOP;
        } else if (ACTION == RESTART_MODE) {
            return RESTART;
        } else {
            throw new IllegalStateException();
        }
    }

    public String[] getParameters()
    {
        if ( ACTION == -1 ) {
            throw new IllegalStateException();
        }
        return (ACTION == RESTART_MODE)
                ? new String[]{}
                : new String[]{ domain };
    }

}
