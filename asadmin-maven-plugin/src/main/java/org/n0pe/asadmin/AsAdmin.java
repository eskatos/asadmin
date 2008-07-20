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
package org.n0pe.asadmin;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.SystemUtils;


/**
 * asadmin command execution facility built as a multipleton which discriminator is a configuration provider.
 * 
 * TODO : handle asadmin invocation return codes with exceptions
 * TODO : investigate multimode asadmin command invocation
 * 
 * @author Paul Merlin <eskatos@n0pe.org>
 */
public class AsAdmin {


    private static final String ASADMIN_FAILED = "failed";


    private static Map instances;


    public static final String USER_OPT = "--user";


    public static final String PASSWORDFILE_OPT = "--passwordfile";


    public static String ASADMIN_COMMAND_NAME;


    


    static {
        ASADMIN_COMMAND_NAME = SystemUtils.IS_OS_WINDOWS
                ? "asadmin.bat"
                : "./asadmin";
    }


    private IAsAdminConfig config;


    /**
     * Get a asadmin instance configured with the given configuration provider.
     * 
     * @param config
     * @return
     */
    public static AsAdmin getInstance(final IAsAdminConfig config) {
        if (instances == null) {
            instances = new HashMap(1);
        }
        AsAdmin instance = (AsAdmin) instances.get(config);
        if (instance == null) {
            instance = new AsAdmin(config);
            instances.put(config, instance);
        }
        return instance;
    }


    private AsAdmin(final IAsAdminConfig config) {
        this.config = config;
    }


    /**
     * Run the given list of AsAdmin command.
     * 
     * @param cmdList AsAdmin commands to be run
     * @throws org.n0pe.asadmin.AsAdminException AsAdminException
     */
    public void run(final AsAdminCmdList cmdList)
            throws AsAdminException {
        final Iterator it = cmdList.iterator();
        IAsAdminCmd cmd;
        while (it.hasNext()) {
            cmd = (IAsAdminCmd) it.next();
            run(cmd);
        }
    }


    /**
     * Run the given AsAdmin command.
     * 
     * @param cmd AsAdmin command to be run
     * @throws org.n0pe.asadmin.AsAdminException AsAdminException
     */
    public void run(final IAsAdminCmd cmd)
            throws AsAdminException {
        try {
            final File gfBinPath = new File(config.getGlassfishHome() + File.separator + "bin");
            final String[] cmds = buildProcessParams(cmd, config);
            cmds[0] = gfBinPath + File.separator + cmds[0];
            final ProcessBuilder pb = new ProcessBuilder(cmds);
            pb.directory(gfBinPath);
            System.out.println("AsAdmin will run the following command : " + pb.command());
            final Process p = pb.start();
            final BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            final StringWriter sw = new StringWriter();
            String ln;
            while ((ln = br.readLine()) != null) {
                sw.append(ln);
            }
            p.waitFor();
            final int exitCode = p.exitValue();
            final String errorOutput = sw.toString();
            if (errorOutput.contains(ASADMIN_FAILED)) {
                throw new AsAdminException("asadmin returned : " + String.valueOf(exitCode) +
                                           " error output is : \n" + errorOutput);
            }
        } catch (final InterruptedException ex) {
            throw new AsAdminException("AsAdmin error occurred: " + ex.getMessage(), ex);
        } catch (final IOException ex) {
            throw new AsAdminException("AsAdmin error occurred: " + ex.getMessage(), ex);
        }
    }


    public static String[] buildProcessParams(final IAsAdminCmd cmd, final IAsAdminConfig config) {
        final String[] pbParams;
        if (cmd.needCredentials()) {
            pbParams = new String[cmd.getParameters().length + 6];
            pbParams[0] = ASADMIN_COMMAND_NAME;
            pbParams[1] = cmd.getActionCommand();
            pbParams[2] = USER_OPT;
            pbParams[3] = config.getUser();
            pbParams[4] = PASSWORDFILE_OPT;
            pbParams[5] = config.getPasswordFile();
            for (int i = 0; i < cmd.getParameters().length; i++) {
                pbParams[i + 6] = cmd.getParameters()[i];
            }
        } else {
            pbParams = new String[cmd.getParameters().length + 2];
            pbParams[0] = ASADMIN_COMMAND_NAME;
            pbParams[1] = cmd.getActionCommand();
            for (int i = 0; i < cmd.getParameters().length; i++) {
                pbParams[i + 2] = cmd.getParameters()[i];
            }
        }
        return pbParams;
    }


}
