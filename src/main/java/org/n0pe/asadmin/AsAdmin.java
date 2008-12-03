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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;

import org.n0pe.asadmin.commands.Database;
import org.n0pe.asadmin.commands.Domain;


/**
 * asadmin command execution facility built as a multipleton which discriminator is a configuration provider.
 * 
 * TODO : allows AsAdminCommands to provide input lines for stdin, implements this command :
 *        echo y | asadmin generate-diagnostic-report --outputfile report-dosadi.jar domainName
 * 
 * TODO : handle asadmin invocation return codes with exceptions
 * 
 * @author Paul Merlin <eskatos@n0pe.org>
 * @author Christophe Souvignier <chris.so@free.fr>
 */
public class AsAdmin {


    private static final String ASADMIN_FAILED = "failed";


    private static final String OUTPUT_PREFIX = "[ASADMIN] ";


    private static Map instances;


    public static final String HOST_OPT = "--host";


    public static final String PORT_OPT = "--port";


    public static final String SECURE_OPT = "--secure";


    public static final String USER_OPT = "--user";


    public static final String PASSWORDFILE_OPT = "--passwordfile";


    public static String ASADMIN_COMMAND_NAME;


    


    static {
        ASADMIN_COMMAND_NAME = SystemUtils.IS_OS_WINDOWS
                ? "asadmin.bat"
                : "asadmin";
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
            int exitCode;
            final Process proc;
            if (SystemUtils.IS_OS_WINDOWS) {
                // Windows
                final String command = StringUtils.join(cmds, " ");
                final String[] windowsCommand;
                if (SystemUtils.IS_OS_WINDOWS_95 || SystemUtils.IS_OS_WINDOWS_98 || SystemUtils.IS_OS_WINDOWS_ME) {
                    windowsCommand = new String[]{"command.com", "/C", command};
                } else {
                    windowsCommand = new String[]{"cmd.com", "/C", command};
                }
                outPrintln("Will run the following command: " + StringUtils.join(windowsCommand, " "));
                proc = Runtime.getRuntime().exec(windowsCommand);
            } else {
                // Non Windows
                outPrintln("Will run the following command: " + StringUtils.join(cmds, " "));
                proc = Runtime.getRuntime().exec(cmds);
            }
            final ProcessStreamGobbler errorGobbler = new ProcessStreamGobbler(proc.getErrorStream(),
                                                                               ProcessStreamGobbler.ERROR);
            final ProcessStreamGobbler outputGobbler = new ProcessStreamGobbler(proc.getInputStream(),
                                                                                ProcessStreamGobbler.OUTPUT);
            errorGobbler.start();
            outputGobbler.start();
            exitCode = proc.waitFor();
            if (exitCode != 0) {
                throw new AsAdminException("asadmin invocation failed and returned : " + String.valueOf(exitCode));
            }
        } catch (final InterruptedException ex) {
            throw new AsAdminException("AsAdmin error occurred: " + ex.getMessage(), ex);
        } catch (final IOException ex) {
            throw new AsAdminException("AsAdmin error occurred: " + ex.getMessage(), ex);
        }
    }


    public static String[] buildProcessParams(final IAsAdminCmd cmd, final IAsAdminConfig config) {
        final List pbParams = new ArrayList();
        pbParams.add(ASADMIN_COMMAND_NAME);
        pbParams.add(cmd.getActionCommand());
        if (!StringUtils.isEmpty(config.getHost()) &&
                !Domain.START.equals(cmd.getActionCommand()) &&
                !Domain.STOP.equals(cmd.getActionCommand()) && 
                !Database.STOP.equals(cmd.getActionCommand()) &&
                !Database.START.equals(cmd.getActionCommand())) {
            pbParams.add(HOST_OPT);
            pbParams.add(config.getHost());
        }
        if (!StringUtils.isEmpty(config.getPort()) &&
                !Domain.START.equals(cmd.getActionCommand()) &&
                !Domain.STOP.equals(cmd.getActionCommand()) &&
                !Database.STOP.equals(cmd.getActionCommand()) &&
                !Database.START.equals(cmd.getActionCommand())) {
            pbParams.add(PORT_OPT);
            pbParams.add(config.getPort());
        }
        if (config.isSecure()) {
            pbParams.add(SECURE_OPT);
        }
        if (cmd.needCredentials()) {
            pbParams.add(USER_OPT);
            pbParams.add(config.getUser());
            pbParams.add(PASSWORDFILE_OPT);
            pbParams.add(config.getPasswordFile());
        }
        pbParams.addAll(Arrays.asList(cmd.getParameters()));
        return (String[]) pbParams.toArray(new String[pbParams.size()]);
    }


    private static void outPrintln(final String message) {
        System.out.print(OUTPUT_PREFIX);
        System.out.println(message);
    }


    private static void errPrintln(final String message) {
        System.out.print(OUTPUT_PREFIX);
        System.out.println(message);
    }


    /**
     * TODO : take a logger as constructor parameter and remove type
     */
    private static class ProcessStreamGobbler
            extends Thread {


        private static final int OUTPUT = 0;


        private static final int ERROR = 1;


        private InputStream is;


        private int type = OUTPUT;


        private ProcessStreamGobbler(InputStream is, int type) {
            this.is = is;
            this.type = type;
        }


        public void run() {
            try {
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String line = null;
                while ((line = br.readLine()) != null) {
                    switch (type) {
                        case OUTPUT:
                            outPrintln("[OUTPUT] " + line);
                            break;
                        case ERROR:
                            errPrintln("[ERROR]  " + line);
                    }
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }


    }


}
