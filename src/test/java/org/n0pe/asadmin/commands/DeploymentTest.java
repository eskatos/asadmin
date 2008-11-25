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


import junit.framework.TestCase;

import org.n0pe.asadmin.AsAdmin;


/**
 * DeploymentTest.
 *
 * @author Paul Merlin <eskatos@n0pe.org>
 */
public class DeploymentTest
        extends TestCase {


    private static final String ARCHIVE_NAME = "my-archive-0.1.ear";


    private static final String COMPONENT_NAME = "my-archive-0.1";


    private static final String CONTEXT_ROOT = "my-ctx-root";


    public void testDeploy() {
        final Deployment deployCmd = new Deployment().archive(ARCHIVE_NAME).withContextRoot(CONTEXT_ROOT);
        try {
            deployCmd.getParameters();
            fail("This test should have thrown an IllegalStateException");
        } catch (IllegalStateException ex) {
        }
        deployCmd.deploy();
        assertTrue(deployCmd.needCredentials());
        assertEquals(Deployment.DEPLOY, deployCmd.getActionCommand());
        String[] goodParams = new String[]{Deployment.CONTEXTROOT_OPT, CONTEXT_ROOT, ARCHIVE_NAME};
        assertEquals(goodParams.length, deployCmd.getParameters().length);
        for (int i = 0; i < goodParams.length; i++) {
            assertEquals(goodParams[i], deployCmd.getParameters()[i]);
        }
        goodParams = new String[]{AsAdmin.ASADMIN_COMMAND_NAME, deployCmd.getActionCommand(),
                                  AsAdmin.HOST_OPT, TestAsConfigProvider.HOST,
                                  AsAdmin.PORT_OPT, TestAsConfigProvider.PORT,
                                  AsAdmin.USER_OPT, TestAsConfigProvider.USER_NAME,
                                  AsAdmin.PASSWORDFILE_OPT, TestAsConfigProvider.PASSWORD_FILE,
                                  Deployment.CONTEXTROOT_OPT, CONTEXT_ROOT,
                                  ARCHIVE_NAME
                };
        final String[] processParams = AsAdmin.buildProcessParams(deployCmd, TestAsConfigProvider.getInstance());
        assertEquals(goodParams.length, processParams.length);
        for (int i = 0; i < goodParams.length; i++) {
            assertEquals(goodParams[i], processParams[i]);
        }
    }


    public void testUndeploy() {
        final Deployment undeployCmd = new Deployment().component(COMPONENT_NAME);
        try {
            undeployCmd.getParameters();
            fail("This test should have thrown an IllegalStateException");
        } catch (IllegalStateException ex) {
        }
        undeployCmd.undeploy();
        assertTrue(undeployCmd.needCredentials());
        assertEquals(Deployment.UNDEPLOY, undeployCmd.getActionCommand());
        String[] goodParams = new String[]{COMPONENT_NAME};
        assertEquals(goodParams.length, undeployCmd.getParameters().length);
        for (int i = 0; i < goodParams.length; i++) {
            assertEquals(goodParams[i], undeployCmd.getParameters()[i]);
        }
        goodParams = new String[]{AsAdmin.ASADMIN_COMMAND_NAME, undeployCmd.getActionCommand(),
                                  AsAdmin.HOST_OPT, TestAsConfigProvider.HOST,
                                  AsAdmin.PORT_OPT, TestAsConfigProvider.PORT,
                                  AsAdmin.USER_OPT, TestAsConfigProvider.USER_NAME,
                                  AsAdmin.PASSWORDFILE_OPT, TestAsConfigProvider.PASSWORD_FILE,
                                  COMPONENT_NAME
                };
        final String[] processParams = AsAdmin.buildProcessParams(undeployCmd, TestAsConfigProvider.getInstance());
        assertEquals(goodParams.length, processParams.length);
        for (int i = 0; i < goodParams.length; i++) {
            assertEquals(goodParams[i], processParams[i]);
        }
    }


}
