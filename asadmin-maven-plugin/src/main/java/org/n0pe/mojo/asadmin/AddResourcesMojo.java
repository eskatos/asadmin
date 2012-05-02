package org.n0pe.mojo.asadmin;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.n0pe.asadmin.AsAdminCmdList;
import org.n0pe.asadmin.commands.AddResources;

/**
 * @goal add-resources
 * @description AsAdmin add-resources mojo
 * @author JF
 */
public class AddResourcesMojo extends AbstractAsadminMojo {

    /**
     * @parameter
     * @required
     */
    private String resourcesFileLocation;
    
    /**
     * @parameter
     */
    private String target = null;
    

    /**
     * @parameter
     */
    private Boolean upload = null;
    
	@Override
	protected AsAdminCmdList getAsCommandList() throws MojoExecutionException,
			MojoFailureException {
		getLog().info( "adding resources: " + resourcesFileLocation );
        final AsAdminCmdList list = new AsAdminCmdList();
        AddResources addResCmd = new AddResources()
        	.setFileLocation(resourcesFileLocation)
        	.setTarget(target)
        	.setUpload(upload);
        setPatterns(addResCmd);
        list.add(addResCmd);
        return list;
	}

}
