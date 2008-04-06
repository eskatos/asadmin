/* 
 * Created on 6 avr. 2008
 * 
 * Licenced under the Netheos Licence, Version 1.0 (the "Licence"); you may not use this file except 
 * in compliance with the License. You may obtain a copy of the License at :
 * 
 * http://www.netheos.net/licences/LICENCE-1.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express 
 * or implied. See the License for the specific language governing permissions and limitations under 
 * the License.
 * 
 * Copyright @2008 Netheos
 * 
 * Developer:  Paul Merlin <p.merlin@netheos.net>
 * Maintainer: Paul Merlin <p.merlin@netheos.net>
 */
package org.n0pe.asadmin.commands;


import java.util.ArrayList;


/**
 * An ArrayList containing IAsCommands.
 * Generics in maven ?
 *
 * @author Paul Merlin <p.merlin@netheos.net>
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
