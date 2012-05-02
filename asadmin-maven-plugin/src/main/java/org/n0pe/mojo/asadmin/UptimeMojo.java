package org.n0pe.mojo.asadmin;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.n0pe.asadmin.AsAdminCmdList;
import org.n0pe.asadmin.IAsAdminConfig;
import org.n0pe.asadmin.commands.Uptime;
/**
 * @goal uptime
 * @description AsAdmin uptime mojo
 * @author JF
 */
public class UptimeMojo extends AbstractAsadminMojo implements IAsAdminConfig {
    
	@Override
	protected AsAdminCmdList getAsCommandList() throws MojoExecutionException,
			MojoFailureException {
		getLog().info( "Checking server uptime");
        final AsAdminCmdList list = new AsAdminCmdList();
        Uptime uptimeCmd = new Uptime();
        setPatterns(uptimeCmd);
        list.add(uptimeCmd);
        return list;
	}

}
