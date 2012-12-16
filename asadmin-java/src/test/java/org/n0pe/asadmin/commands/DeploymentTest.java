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
package org.n0pe.asadmin.commands;

import junit.framework.TestCase;

import org.n0pe.asadmin.AsAdmin;
import org.n0pe.asadmin.AsAdminException;

public class DeploymentTest
        extends TestCase
{

    private static final String ARCHIVE_NAME = "my-archive-0.1.ear";
    private static final String COMPONENT_NAME = "my-archive-0.1";
    private static final String CONTEXT_ROOT = "my-ctx-root";

    public void testDeploy()
            throws AsAdminException
    {
        final Deployment deployCmd = new Deployment();
        try {
            deployCmd.getParameters();
            fail( "This test should have thrown an IllegalStateException" );
        } catch ( IllegalStateException ex ) {
        }
        deployCmd.archive( ARCHIVE_NAME ).withContextRoot( CONTEXT_ROOT ).deploy();
        assertTrue( deployCmd.needCredentials() );
        assertEquals( Deployment.DEPLOY, deployCmd.getActionCommand() );
        String[] goodParams = new String[]{ Deployment.CONTEXTROOT_OPT, CONTEXT_ROOT, ARCHIVE_NAME };
        assertEquals( goodParams.length, deployCmd.getParameters().length );
        for ( int i = 0; i < goodParams.length; i++ ) {
            assertEquals( goodParams[i], deployCmd.getParameters()[i] );
        }
        goodParams = new String[]{ AsAdmin.ASADMIN_COMMAND_NAME, 
                                   AsAdmin.HOST_OPT, TestAsConfigProvider.HOST,
                                   AsAdmin.PORT_OPT, TestAsConfigProvider.PORT,
                                   AsAdmin.USER_OPT, TestAsConfigProvider.USER_NAME,
                                   AsAdmin.PASSWORDFILE_OPT, TestAsConfigProvider.PASSWORD_FILE,
                                   deployCmd.getActionCommand(),
                                   Deployment.CONTEXTROOT_OPT, CONTEXT_ROOT,
                                   ARCHIVE_NAME
        };
        final String[] processParams = AsAdmin.buildProcessParams( deployCmd, TestAsConfigProvider.getInstance() );
        assertEquals( goodParams.length, processParams.length );
        for ( int i = 0; i < goodParams.length; i++ ) {
            assertEquals( goodParams[i], processParams[i] );
        }
    }

    public void testUndeploy()
            throws AsAdminException
    {
        final Deployment undeployCmd = new Deployment().component( COMPONENT_NAME );
        try {
            undeployCmd.getParameters();
            fail( "This test should have thrown an IllegalStateException" );
        } catch ( IllegalStateException ex ) {
        }
        undeployCmd.undeploy();
        assertTrue( undeployCmd.needCredentials() );
        assertEquals( Deployment.UNDEPLOY, undeployCmd.getActionCommand() );
        String[] goodParams = new String[]{ COMPONENT_NAME };
        assertEquals( goodParams.length, undeployCmd.getParameters().length );
        for ( int i = 0; i < goodParams.length; i++ ) {
            assertEquals( goodParams[i], undeployCmd.getParameters()[i] );
        }
        goodParams = new String[]{ AsAdmin.ASADMIN_COMMAND_NAME,
                                   AsAdmin.HOST_OPT, TestAsConfigProvider.HOST,
                                   AsAdmin.PORT_OPT, TestAsConfigProvider.PORT,
                                   AsAdmin.USER_OPT, TestAsConfigProvider.USER_NAME,
                                   AsAdmin.PASSWORDFILE_OPT, TestAsConfigProvider.PASSWORD_FILE,
                                   undeployCmd.getActionCommand(),
                                   COMPONENT_NAME
        };
        final String[] processParams = AsAdmin.buildProcessParams( undeployCmd, TestAsConfigProvider.getInstance() );
        assertEquals( goodParams.length, processParams.length );
        for ( int i = 0; i < goodParams.length; i++ ) {
            assertEquals( goodParams[i], processParams[i] );
        }
    }
    public void testRedeploy()
            throws AsAdminException
    {
        final Deployment redeployCmd = new Deployment();
        try {
            redeployCmd.getParameters();
            fail( "This test should have thrown an IllegalStateException" );
        } catch ( IllegalStateException ex ) {
        }
        redeployCmd.archive( ARCHIVE_NAME ).appName( COMPONENT_NAME ).redeploy();
        assertTrue( redeployCmd.needCredentials() );
        assertEquals( Deployment.REDEPLOY, redeployCmd.getActionCommand() );
        String[] goodParams = new String[]{ Deployment.NAME_OPT, COMPONENT_NAME, ARCHIVE_NAME };
        assertEquals( goodParams.length, redeployCmd.getParameters().length );
        for ( int i = 0; i < goodParams.length; i++ ) {
            assertEquals( goodParams[i], redeployCmd.getParameters()[i] );
        }
        goodParams = new String[]{ AsAdmin.ASADMIN_COMMAND_NAME, 
                                   AsAdmin.HOST_OPT, TestAsConfigProvider.HOST,
                                   AsAdmin.PORT_OPT, TestAsConfigProvider.PORT,
                                   AsAdmin.USER_OPT, TestAsConfigProvider.USER_NAME,
                                   AsAdmin.PASSWORDFILE_OPT, TestAsConfigProvider.PASSWORD_FILE,
                                   redeployCmd.getActionCommand(),
                                   Deployment.NAME_OPT, COMPONENT_NAME,
                                   ARCHIVE_NAME
        };
        final String[] processParams = AsAdmin.buildProcessParams( redeployCmd, TestAsConfigProvider.getInstance() );
        assertEquals( goodParams.length, processParams.length );
        for ( int i = 0; i < goodParams.length; i++ ) {
            assertEquals( goodParams[i], processParams[i] );
        }
    }


}
