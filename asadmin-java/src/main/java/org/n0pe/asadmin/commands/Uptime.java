package org.n0pe.asadmin.commands;

import org.n0pe.asadmin.AbstractAsAdminCmd;

public class Uptime extends AbstractAsAdminCmd {
	
	boolean requiresCredentials = false;

	@Override
	public boolean needCredentials() {
		return true;
	}

	@Override
	public String getActionCommand() {
		return "uptime";
	}

	@Override
	public String[] getParameters() {
			return EMPTY_ARRAY;
	}

}
