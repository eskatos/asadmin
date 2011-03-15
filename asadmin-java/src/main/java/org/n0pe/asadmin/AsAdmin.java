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
public class AsAdmin
{

    private static final String ASADMIN_FAILED = "failed";
    private static final String OUTPUT_PREFIX = "[ASADMIN] ";
    private static Map<IAsAdminConfig, AsAdmin> instances;
    public static final String HOST_OPT = "--host";
    public static final String PORT_OPT = "--port";
    public static final String SECURE_OPT = "--secure";
    public static final String USER_OPT = "--user";
    public static final String PASSWORDFILE_OPT = "--passwordfile";
    public static String ASADMIN_COMMAND_NAME;

    static {
        ASADMIN_COMMAND_NAME = SystemUtils.IS_OS_WINDOWS ? "asadmin.bat" : "asadmin";
    }

    private IAsAdminConfig config;

    /**
     * Get a asadmin instance configured with the given configuration provider.
     * 
     * @param config
     * @return
     */
    public static AsAdmin getInstance( final IAsAdminConfig config )
    {
        if ( instances == null ) {
            instances = new HashMap<IAsAdminConfig, AsAdmin>( 1 );
        }
        AsAdmin instance = instances.get( config );
        if ( instance == null ) {
            instance = new AsAdmin( config );
            instances.put( config, instance );
        }
        return instance;
    }

    private AsAdmin( final IAsAdminConfig config )
    {
        this.config = config;
    }

    /**
     * Run the given list of AsAdmin command.
     * 
     * @param cmdList AsAdmin commands to be run
     * @throws org.n0pe.asadmin.AsAdminException AsAdminException
     */
    public void run( final AsAdminCmdList cmdList )
            throws AsAdminException
    {
        final Iterator<IAsAdminCmd> it = cmdList.iterator();
        while ( it.hasNext() ) {
            run( it.next() );
        }
    }

    /**
     * Run the given AsAdmin command.
     * 
     * @param cmd AsAdmin command to be run
     * @throws org.n0pe.asadmin.AsAdminException AsAdminException
     */
    public void run( final IAsAdminCmd cmd )
            throws AsAdminException
    {
        try {
            final File gfBinPath = new File( config.getGlassfishHome() + File.separator + "bin" );
            final String[] cmds = buildProcessParams( cmd, config );
            cmds[0] = gfBinPath + File.separator + cmds[0];
            int exitCode;
            final Process proc;
            String[] env = buildEnvironmentStrings( config.getEnvironmentVariables() );
            if ( SystemUtils.IS_OS_WINDOWS ) {
                // Windows
                final String command = "\"\"" + StringUtils.join( cmds, "\" \"" ) + "\"\"";
                final String[] windowsCommand;
                if ( SystemUtils.IS_OS_WINDOWS_95 || SystemUtils.IS_OS_WINDOWS_98 || SystemUtils.IS_OS_WINDOWS_ME ) {
                    windowsCommand = new String[]{ "command.com", "/C", command };
                } else {
                    windowsCommand = new String[]{ "cmd.exe", "/C", command };
                }
                outPrintln( "Will run the following command: " + StringUtils.join( windowsCommand, " " ) );
                proc = Runtime.getRuntime().exec( windowsCommand, env );
            } else {
                // Non Windows
                outPrintln( "Will run the following command: " + StringUtils.join( cmds, " " ) );
                proc = Runtime.getRuntime().exec( cmds, env );
            }
            final ProcessStreamGobbler errorGobbler = new ProcessStreamGobbler( cmd,
                                                                                proc.getErrorStream(),
                                                                                ProcessStreamGobbler.ERROR );
            final ProcessStreamGobbler outputGobbler = new ProcessStreamGobbler( cmd,
                                                                                 proc.getInputStream(),
                                                                                 ProcessStreamGobbler.OUTPUT );
            errorGobbler.start();
            outputGobbler.start();
            exitCode = proc.waitFor();
            if ( exitCode != 0 ) {
                throw new AsAdminException( "asadmin invocation failed and returned : " + String.valueOf( exitCode ) );
            }
        } catch ( final InterruptedException ex ) {
            throw new AsAdminException( "AsAdmin error occurred: " + ex.getMessage(), ex );
        } catch ( final IOException ex ) {
            throw new AsAdminException( "AsAdmin error occurred: " + ex.getMessage(), ex );
        }
    }

    public static String[] buildProcessParams( final IAsAdminCmd cmd, final IAsAdminConfig config )
            throws AsAdminException
    {
        final List<String> pbParams = new ArrayList<String>();
        pbParams.add( ASADMIN_COMMAND_NAME );
        pbParams.add( cmd.getActionCommand() );
        if ( !StringUtils.isEmpty( config.getHost() )
             && !Domain.START.equals( cmd.getActionCommand() )
             && !Domain.STOP.equals( cmd.getActionCommand() )
             && !Database.STOP.equals( cmd.getActionCommand() )
             && !Database.START.equals( cmd.getActionCommand() ) ) {
            pbParams.add( HOST_OPT );
            pbParams.add( config.getHost() );
        }
        if ( !StringUtils.isEmpty( config.getPort() )
             && !Domain.START.equals( cmd.getActionCommand() )
             && !Domain.STOP.equals( cmd.getActionCommand() )
             && !Database.STOP.equals( cmd.getActionCommand() )
             && !Database.START.equals( cmd.getActionCommand() ) ) {
            pbParams.add( PORT_OPT );
            pbParams.add( config.getPort() );
        }
        if ( config.isSecure() ) {
            pbParams.add( SECURE_OPT );
        }
        if ( cmd.needCredentials() ) {
            pbParams.add( USER_OPT );
            pbParams.add( config.getUser() );
            pbParams.add( PASSWORDFILE_OPT );
            pbParams.add( cmd.handlePasswordFile( config.getPasswordFile() ) );

        }
        pbParams.addAll( Arrays.asList( cmd.getParameters() ) );
        return pbParams.toArray( new String[ pbParams.size() ] );
    }

    private static void outPrintln( final String message )
    {
        System.out.print( OUTPUT_PREFIX );
        System.out.println( message );
    }

    private static void errPrintln( final String message )
    {
        System.out.print( OUTPUT_PREFIX );
        System.out.println( message );
    }

    /**
     * Ensure variable names do not contains spaces and quote their values if needed.
     */
    /* package */ static String[] buildEnvironmentStrings( Map<String, String> envVariables )
    {
        if ( envVariables == null || envVariables.isEmpty() ) {
            return new String[]{};
        }
        String[] array = new String[ envVariables.size() ];
        int idx = 0;
        for ( Map.Entry<String, String> eachEntry : envVariables.entrySet() ) {
            String key = eachEntry.getKey().trim();
            if ( !key.matches( "^\\S+$" ) ) {
                throw new IllegalArgumentException( "Environment variable names cannot contain spaces: " + key );
            }
            String value = eachEntry.getValue();
            array[idx] = key + "=" + quoteArgument( value );
            idx++;
        }
        return array;
    }

    private static final String SINGLE_QUOTE = "\'";
    private static final String DOUBLE_QUOTE = "\"";

    private static String quoteArgument( final String argument )
    {
        String cleanedArgument = argument.trim();
        // strip the quotes from both ends
        while ( cleanedArgument.startsWith( SINGLE_QUOTE ) || cleanedArgument.startsWith( DOUBLE_QUOTE ) ) {
            cleanedArgument = cleanedArgument.substring( 1 );
        }
        while ( cleanedArgument.endsWith( SINGLE_QUOTE ) || cleanedArgument.endsWith( DOUBLE_QUOTE ) ) {
            cleanedArgument = cleanedArgument.substring( 0, cleanedArgument.length() - 1 );
        }
        final StringBuffer sb = new StringBuffer();
        if ( cleanedArgument.indexOf( DOUBLE_QUOTE ) > -1 ) {
            if ( cleanedArgument.indexOf( SINGLE_QUOTE ) > -1 ) {
                throw new IllegalArgumentException( "Can't handle single and double quotes in same argument" );
            } else {
                return sb.append( SINGLE_QUOTE ).append( cleanedArgument ).append( SINGLE_QUOTE ).toString();
            }
        } else if ( cleanedArgument.indexOf( SINGLE_QUOTE ) > -1 || cleanedArgument.indexOf( " " ) > -1 ) {
            return sb.append( DOUBLE_QUOTE ).append( cleanedArgument ).append( DOUBLE_QUOTE ).toString();
        } else {
            return cleanedArgument;
        }
    }

    /**
     * TODO : take a logger as constructor parameter and remove type
     */
    private static class ProcessStreamGobbler
            extends Thread
    {

        private static final int OUTPUT = 0;
        private static final int ERROR = 1;
        private AbstractAsAdminCmd cmd;
        private InputStream is;
        private int type = OUTPUT;

        private ProcessStreamGobbler( final IAsAdminCmd cmd, InputStream is, int type )
        {
            this.is = is;
            this.type = type;
            if ( AbstractAsAdminCmd.class.isAssignableFrom( cmd.getClass() ) ) {
                this.cmd = ( AbstractAsAdminCmd ) cmd;
            }
        }

        @Override
        @SuppressWarnings( "CallToThreadDumpStack" )
        public void run()
        {
            try {
                InputStreamReader isr = new InputStreamReader( is );
                BufferedReader br = new BufferedReader( isr );
                String line = null;
                while ( ( line = br.readLine() ) != null ) {

                    switch ( type ) {
                        case OUTPUT:
                            if ( cmd != null ) {
                                cmd.appendStandardOutputLine( line );
                            }
                            outPrintln( "[OUTPUT] " + line );
                            break;
                        case ERROR:
                            if ( cmd != null ) {
                                cmd.appendErrorOutputLine( line );
                            }
                            errPrintln( "[ERROR]  " + line );
                    }
                }
            } catch ( IOException ioe ) {
                ioe.printStackTrace();
            }
        }

    }

}
