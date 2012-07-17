package org.n0pe.mojo.asadmin;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.n0pe.asadmin.AsAdminCmdList;
import org.n0pe.asadmin.commands.Domain;

/**
 * @goal restart-domain
 * @description AsAdmin restart-domain mojo
 * @author  Charles Brown
 * Date: 7/17/12
 */
public class RestartDomainMojo
        extends AbstractAsadminMojo {

    @Override
    protected AsAdminCmdList getAsCommandList()
            throws MojoExecutionException, MojoFailureException
    {
        getLog().info("Restarting AS Domain");
        final AsAdminCmdList list = new AsAdminCmdList();
        Domain domainCmd = new Domain( ).restart();
        setPatterns(domainCmd);
        list.add(domainCmd);
        return list;
    }
}
