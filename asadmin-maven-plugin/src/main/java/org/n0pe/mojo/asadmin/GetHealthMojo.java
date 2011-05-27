package org.n0pe.mojo.asadmin;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.n0pe.asadmin.AsAdminCmdList;
import org.n0pe.asadmin.IAsAdminConfig;
import org.n0pe.asadmin.commands.GetHealth;
/**
 * @goal get-health
 * @description AsAdmin get-health mojo
 * @author JF
 */
public class GetHealthMojo extends AbstractAsadminMojo implements IAsAdminConfig {
    
	@Override
	protected AsAdminCmdList getAsCommandList() throws MojoExecutionException,
			MojoFailureException {
		getLog().info( "Checking health of cluster " + cluster);
        final AsAdminCmdList list = new AsAdminCmdList();
        GetHealth healthCmd = new GetHealth();
        healthCmd.setClusterName(cluster);
        setPatterns(healthCmd);
        list.add(healthCmd);
        return list;
	}

}
