/*
 * Copyright (c) 2010, Paul Merlin.
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
package org.n0pe.asadmin;

import java.io.Reader;
import java.util.regex.Pattern;

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

    /**
     * If asadmin command return is non zero the command will be given the change to say whether they wish to fail based on the contents of stdout and stderr they have been passed.
     * For example a DeployMojo executing an undeploy command which has failOnCommandSpecificErrors set to false
     * will elect not to fail if the error output was along the lines of
     * "remote failure: Application my-ear is not deployed on this target [server]"
     * during an mvn clean or testing cycle...
     *
     * @return whether this command should cause the plugin to fail, or whether it is acceptable to continue.
     */
    boolean failOnNonZeroExit();

    /**
     * Pass a Pattern which can be used to match the stderr output if the asadmin exits with a non zero value.
     * If the Pattern matches the stderr output (multiline) the plugin will not throw an exception but just log the fact that it is continuing
     */
    void setOkayErrorPattern( Pattern pattern );

    /**
     * Pass a Pattern which can be used to match the stdout output if the asadmin exits with a non zero value.
     * If the Pattern matches the stdout output (multiline) the plugin will not throw an exception but just log the fact that it is continuing
     * This is necessary as some asadmin subcommands cause it to exit with non zero, but it writes the error to stdout.
     * start-domain writes its output there.
     *
     * If setOkayErrorPattern is not set then only setOkayStdOutPattern will be checked, which by default determines the value returned by failOnNonZeroExit
     * If setOkayErrorPattern is set and setOkayStdOutPattern is not set then only setOkayErrorPattern will determine what failOnNonZeroExit returns
     * If setOkayErrorPattern is set and setOkayStdOutPattern is set then BOTH setOkayErrorPattern and setOkayStdOutPattern must match if failOnNonZeroExit is to return false
     */
    void setOkayStdOutPattern( Pattern pattern );

}
