package org.n0pe.mojo.asadmin;

import org.n0pe.asadmin.AsAdminCmdList;
import org.n0pe.asadmin.commands.Cluster;

/**
 * @goal start-cluster
 * @description AsAdmin start-cluster mojo
 * @author JF
 */
public class StartClusterMojo
        extends AbstractAsadminMojo
{

    @Override
    protected AsAdminCmdList getAsCommandList()
    {
        getLog().info( "Starting cluster: " + cluster );
        final AsAdminCmdList list = new AsAdminCmdList();
        Cluster clusterCmd = new Cluster( cluster ).start();
        setPatterns(clusterCmd);
        list.add(clusterCmd);
        return list;
    }

}
