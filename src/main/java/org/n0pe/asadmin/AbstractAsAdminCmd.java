/*
 * Created on 22 avr. 2010
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
 * Copyright (c) Netheos
 * @author Jean-Michel Tonneau <jm.tonneau@netheos.net>
 *
 */
package org.n0pe.asadmin;

import java.io.Reader;
import org.apache.commons.io.input.CharSequenceReader;

public abstract class AbstractAsAdminCmd implements IAsAdminCmd {

    private final StringBuilder stdoutBuilder = new StringBuilder();
    private final StringBuilder stderrBuilder = new StringBuilder();

    public final Reader getStandardOutput() {
        return new CharSequenceReader(stdoutBuilder);
    }

    public final Reader getErrorOutput() {
        return new CharSequenceReader(stderrBuilder);
    }

    final void appendStandardOutputLine(String line) {
        stdoutBuilder.append(line).append("\n");
    }

    final void appendErrorOutputLine(String line) {
        stderrBuilder.append(line).append("\n");
    }
}
