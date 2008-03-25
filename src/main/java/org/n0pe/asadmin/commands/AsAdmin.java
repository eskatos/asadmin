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
package org.n0pe.asadmin.commands;


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;


/**
 * TODO : handle asadmin invocation return codes with exceptions
 * TODO : store instance per configuration that contains glassfishHome _and_ credentials
 * TODO : investigate multimode asadmin command invocation
 * 
 * @author Paul Merlin <eskatos@n0pe.org>
 */
public class AsAdmin {


    private static final String ASADMIN_NAME = "asadmin";


    private static final String ASADMIN_FAILED = "failed";


    private static final String USER_OPT = "--user";


    private static final String PASSWORDFILE_OPT = "--passwordfile";


    private static final String SPACE = " ";


    private static Map instances;


    public static AsAdmin getInstance(final String glassfishHome, final IAsAdminCredentials credentials) {
        if (instances == null) {
            instances = new HashMap(1);
        }
        AsAdmin instance = (AsAdmin) instances.get(glassfishHome);
        if (instance == null) {
            instance = new AsAdmin(glassfishHome, credentials);
            instances.put(glassfishHome, instance);
        }
        return instance;
    }


    private String asHome;


    private IAsAdminCredentials credentials;


    private AsAdmin(final String asHome, final IAsAdminCredentials credentials) {
        this.asHome = asHome;
        this.credentials = credentials;
    }


    public void run(final IAsCommand cmd)
            throws AsAdminException {
        final StringWriter sw = new StringWriter();
        sw.append(cmd.getActionCommand());
        if (cmd.needCredentials()) {
            sw.append(SPACE).append(USER_OPT).append(SPACE).append(credentials.getUser()).append(SPACE).
                    append(PASSWORDFILE_OPT).append(SPACE).append(credentials.getPasswordFile());
        }
        if (!StringUtils.isEmpty(cmd.getParameters())) {
            sw.append(SPACE).append(cmd.getParameters());
        }
        runNative(asHome, ASADMIN_NAME, sw.toString());
    }


    private static void runNative(final String executableDirectory,
                                    final String executableName,
                                    final String params)
            throws AsAdminException {
        try {
            final File executableDirFile = new File(executableDirectory);
            final Runtime runtime = Runtime.getRuntime();
            String[] command;
            if (SystemUtils.IS_OS_WINDOWS) {
                command = new String[]{
                    "cmd.exe",
                    "/C",
                    "cd " + executableDirFile.getAbsolutePath() + "\\bin & " +
                    executableName + ".bat " + SPACE + params
                };
            } else {
                command = new String[]{
                    "sh",
                    "-c",
                    "cd " + executableDirFile.getAbsolutePath() + "/bin; ./" +
                    executableName + SPACE + params
                };
            }
            // DEBUG START
            final StringWriter debugSw = new StringWriter();
            for (int i = 0; i < command.length; i++) {
                debugSw.append(command[i]).append(SPACE);
            }
            System.out.println(debugSw.toString());
            // DEBUG END
            final Process p = runtime.exec(command);
            final BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            final StringWriter sw = new StringWriter();
            String ln;
            while ((ln = br.readLine()) != null) {
                sw.append(ln);
                System.err.println(ln);
            }
            // final int exitCode = p.exitValue();
            final String executableOutput = sw.toString();
            if (executableOutput.contains(ASADMIN_FAILED)) {
                throw new AsAdminException(executableOutput);
            }
        } catch (final Exception e) {
            throw new AsAdminException("AsAdmin error occurred: " + e.getMessage(), e);
        }
    }


}
