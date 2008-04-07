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


import java.util.ArrayList;


/**
 * An ArrayList containing IAsCommands.
 * Generics in maven ?
 *
 * @author Paul Merlin <eskatos@n0pe.org>
 */
public class AsCommandList
        extends ArrayList {


    private static final long serialVersionUID = 1L;


    /**
     * AsCommandList CTOR.
     */
    public AsCommandList() {
        super();
    }


    public boolean add(IAsCommand e) {
        return super.add(e);
    }


    public void add(int index, IAsCommand element) {
        super.add(index, element);
    }


    public boolean contains(IAsCommand o) {
        return super.contains(o);
    }


    public int indexOf(IAsCommand o) {
        return super.indexOf(o);
    }


    public int lastIndexOf(IAsCommand o) {
        return super.lastIndexOf(o);
    }


    public boolean remove(IAsCommand o) {
        return super.remove(o);
    }


    public IAsCommand set(int index, IAsCommand element) {
        return (IAsCommand) super.set(index, element);
    }


    public IAsCommand[] toArray(IAsCommand[] a) {
        return (IAsCommand[]) super.toArray(a);
    }


}
