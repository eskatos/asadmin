/*
 * Copyright (c) 2010, Paul Merlin.
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

import org.apache.maven.plugin.AbstractMojo;

/**
 * @goal help
 * @description AsAdmin help mojo
 */
public class HelpMojo
    extends AbstractMojo
{

    @Override
    public void execute()
    {
        getLog().info( "asadmin-glassfish-plugin help" );
        displayCopyrightNotice();
    }

    protected final void displayCopyrightNotice()
    {
        getLog().info( "asadmin-glassfish-plugin  Copyright (C) 2008 Paul Merlin" );
        getLog().info( "This program comes with ABSOLUTELY NO WARRANTY; for details type `mvn asadmin:help'." );
        getLog().info( "This is free software, and you are welcome to redistribute it" );
        getLog().info( "under certain conditions; type `mvn asadmin:help' for details." );
    }

}
