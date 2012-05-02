package org.n0pe.asadmin.commands;

import org.n0pe.asadmin.AbstractAsAdminCmd;

/**
 * @author JF
 */
public class Cluster
        extends AbstractAsAdminCmd
{

    public static final String START = "start-cluster";
    public static final String STOP = "stop-cluster";
    private Boolean start = null;
    private String cluster;

    public Cluster( String cluster )
    {
        this.cluster = cluster;
    }

    public Cluster start()
    {
        start = Boolean.TRUE;
        return this;
    }

    public Cluster stop()
    {
        start = Boolean.FALSE;
        return this;
    }

    public boolean needCredentials()
    {
        return true;
    }

    public String getActionCommand()
    {
        if ( start == null ) {
            throw new IllegalStateException();
        }
        return start.booleanValue()
               ? START
               : STOP;
    }

    public String[] getParameters()
    {
        if ( start == null ) {
            throw new IllegalStateException();
        }
        return new String[]{ cluster };
    }

}
