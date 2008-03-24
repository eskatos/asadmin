/**
 * asadmin-glassfish-plugin : a maven plugin for glassfish administratives tasks
 * 
 * Copyright (C) 2008  Paul Merlin
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.n0pe.mojo.asadmin;


import java.io.File;

import org.apache.maven.plugin.testing.AbstractMojoTestCase;


/**
 * DatabaseTest.
 *
 * @author Paul Merlin <eskatos@n0pe.org>
 */
public class DatabaseTest
        extends AbstractMojoTestCase {


    private File pluginXml = new File(getBasedir(), "src/test/resources/unit/database.xml");


    public void testMojoLookup()
            throws Exception {
        StartDatabaseMojo startMojo = (StartDatabaseMojo) lookupMojo("start-database", pluginXml);
        assertNotNull(startMojo);
        StopDatabaseMojo stopMojo = (StopDatabaseMojo) lookupMojo("stop-database", pluginXml);
        assertNotNull(stopMojo);

    }


}
