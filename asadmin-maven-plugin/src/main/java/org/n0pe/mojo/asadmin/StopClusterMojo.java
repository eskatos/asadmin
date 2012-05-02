package org.n0pe.mojo.asadmin;

import org.n0pe.asadmin.AsAdminCmdList;
import org.n0pe.asadmin.commands.Cluster;

/**
 * @goal stop-cluster
 * @description AsAdmin stop-cluster mojo
 * @author JF
 */
public class StopClusterMojo
        extends AbstractAsadminMojo
{

    @Override
    protected AsAdminCmdList getAsCommandList()
    {
        getLog().info( "Stopping cluster: " + cluster );
        final AsAdminCmdList list = new AsAdminCmdList();
        Cluster clusterCmd = new Cluster( cluster ).stop();
        setPatterns(clusterCmd);
        list.add(clusterCmd);
        return list;
    }

}
