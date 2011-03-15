/*
 * Copyright (c) 2011, Paul Merlin. All Rights Reserved.
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

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import junit.framework.TestCase;

public class ExternalProcessTest
        extends TestCase
{

    public void testEnvVars()
    {
        Map<String, String> env = new LinkedHashMap<String, String>();
        env.put( "JAVA_HOME", "/home/paul/opt/jdk" );
        env.put( "JAVA_HOME_WITH_SPACES", "/home/paul/with spaces/opt/jdk" );
        String[] envStrings = AsAdmin.buildEnvironmentStrings( env );
        assertEquals( "[JAVA_HOME=/home/paul/opt/jdk, JAVA_HOME_WITH_SPACES=\"/home/paul/with spaces/opt/jdk\"]", Arrays.toString( envStrings ) );

        env.put( "WRONG NAME", "useless" );
        try {
            AsAdmin.buildEnvironmentStrings( env );
            fail( "Environment variable with spaces in name are illegal" );
        } catch ( IllegalArgumentException ex ) {
            // expected
        }
    }

}
