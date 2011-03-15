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

import java.io.Reader;

public interface IAsAdminCmd
{

    Reader getStandardOutput();

    Reader getErrorOutput();

    boolean needCredentials();

    /**
     * Hooks called by AsAdmin to allow commands to impact the configured password file.
     *
     * @param configuredPasswordFile    Configured password file
     * @return                          Password file, same or changed if needed
     */
    String handlePasswordFile( String configuredPasswordFile )
            throws AsAdminException;

    String getActionCommand();

    String[] getParameters();

}
