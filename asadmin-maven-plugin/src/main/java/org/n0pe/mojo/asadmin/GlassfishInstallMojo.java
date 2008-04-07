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
package org.n0pe.mojo.asadmin;


import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;


/**
 * GlassfishInstallMojo.
 *
 * @author Paul Merlin <eskatos@n0pe.org>
 */
public class GlassfishInstallMojo
        extends AbstractMojo {


    public void execute()
            throws MojoExecutionException, MojoFailureException {

        // install glassfish
        // setup domain
        // add lib
        // add lib/endorsed
        // add domain/xxxxxx/lib
        
        
        //                <echo file="${dependency.base.dir}/install.bat">
        //<![CDATA[
        //echo a > input
        //java -Xmx256m -jar ${dependency.base.dir}/${glassfish.installer.jar} -console < ./input
        //]]>
        //                </echo>
        //                <chmod file="${dependency.base.dir}/install.bat" perm="700"/>
        //                <exec executable="${dependency.base.dir}/install.bat" dir="${dependency.base.dir}" />
        //                <delete file="${dependency.base.dir}/install.bat"/>
        //                <delete file="${dependency.base.dir}/input"/>
        //                <ant antfile="${glassfish.home}/setup.xml" dir="${glassfish.home}" target="setup"/>
        //                <echo file="${dependency.base.dir}/glassfish/password.txt">AS_ADMIN_PASSWORD=adminadmin</echo>

        throw new UnsupportedOperationException("Not supported yet.");
    }


}
