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


/**
 * @author Paul Merlin <eskatos@n0pe.org>
 */
public class AsAdmin {


    private static Map instances;


    public static AsAdmin getInstance(String glassfishHome, IAsAdminCredentials credentials) {
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


    private AsAdmin(String asHome, IAsAdminCredentials credentials) {
        this.asHome = asHome;
        this.credentials = credentials;
    }


    public void run(IAsCommand cmd)
            throws AsAdminException {
        final StringWriter sw = new StringWriter();
        sw.append(cmd.getActionCommand());
        if (cmd.needCredentials()) {
            sw.append(" --user ").append(credentials.getUser()).
                    append(" --passwordfile ").append(credentials.getPasswordFile());
        }
        if (!StringUtils.isEmpty(cmd.getParameters())) {
            sw.append(" ").append(cmd.getParameters());
        }
        runNative(asHome, "asadmin", sw.toString());
    }


    private static void runNative(String programDirectory, String programName, String params)
            throws AsAdminException {
        try {
            final File programDirFile = new File(programDirectory);
            final Runtime runtime = Runtime.getRuntime();
            String[] command;
            if (System.getProperty("os.name").startsWith("Windows")) {
                command = new String[]{
                    "cmd.exe",
                    "/C",
                    "cd " + programDirFile.getAbsolutePath() + "\\bin & " +
                    programName + ".bat " + " " + params
                };
            } else {
                command = new String[]{
                    "sh",
                    "-c",
                    "cd " + programDirFile.getAbsolutePath() + "/bin; ./" +
                    programName + " " + params
                };
            }
            // DEBUG START
            final StringWriter sw = new StringWriter();
            for (int i = 0; i < command.length; i++) {
                sw.append(command[i]).append(" ");
            }
            System.out.println(sw.toString());
            // DEBUG END
            final Process p = runtime.exec(command);
            final BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String line = "";
            String ln;
            while ((ln = br.readLine()) != null) {
                line += ln;
                System.err.println(ln);
            }
            if (line.contains("failed")) {
                throw new AsAdminException(line);
            }
        } catch (final Exception e) {
            throw new AsAdminException("AsAdmin error occurred: " + e.getMessage(), e);
        }
    }


}
