/*
 * Copyright (c) 2010, Paul Merlin. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.n0pe.mojo.asadmin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * @author Paul Merlin
 */
public class GlassfishInstallMojo
        extends AbstractMojo
{

    @Override
    public void execute()
            throws MojoExecutionException, MojoFailureException
    {

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

        throw new UnsupportedOperationException( "Not supported yet." );
    }

}
