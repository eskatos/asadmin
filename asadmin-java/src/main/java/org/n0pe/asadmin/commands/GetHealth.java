package org.n0pe.asadmin.commands;

import org.n0pe.asadmin.AbstractAsAdminCmd;

public class GetHealth extends AbstractAsAdminCmd {

	private String clusterName = null;
	
	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	@Override
	public boolean needCredentials() {
		return true;
	}

	@Override
	public String getActionCommand() {
		return "get-health";
	}

	@Override
	public String[] getParameters() {
		if(clusterName == null)
			return EMPTY_ARRAY;
		else 
			return new String[] { clusterName };
	}

}
