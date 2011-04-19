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
package org.n0pe.asadmin;

import java.io.Reader;
import java.util.regex.Pattern;

import org.apache.commons.io.input.CharSequenceReader;

public abstract class AbstractAsAdminCmd
        implements IAsAdminCmd
{

    private final StringBuilder stdoutBuilder = new StringBuilder();
    private final StringBuilder stderrBuilder = new StringBuilder();
    private Pattern okayErrorPattern = null;
    private Pattern okayStdOutPattern = null;

    @Override
    public final Reader getStandardOutput()
    {
        return new CharSequenceReader( stdoutBuilder );
    }

    @Override
    public final Reader getErrorOutput()
    {
        return new CharSequenceReader( stderrBuilder );
    }
    
    @Override
    public boolean failOnNonZeroExit()
    {
    	if(okayErrorPattern == null)
    	{
    		if(okayStdOutPattern == null)
    			return true;
    		else
    			return  !okayStdOutPattern.matcher(stdoutBuilder.toString()).matches();
    	}
    	else
    	{
    		boolean stderrorNotOK = !okayErrorPattern.matcher(stderrBuilder.toString()).matches();
    		if(okayStdOutPattern == null)
    			return stderrorNotOK;
    		else
    			return  !okayStdOutPattern.matcher(stdoutBuilder.toString()).matches();
    		
    	}
    }

    @Override
	public void setOkayErrorPattern(Pattern pattern) {
		this.okayErrorPattern = pattern;
	}

    @Override
	public void setOkayStdOutPattern(Pattern pattern) {
		this.okayStdOutPattern = pattern;
	}

	public String handlePasswordFile( String configuredPasswordFile )
            throws AsAdminException
    {
        return configuredPasswordFile;
    }

    final void appendStandardOutputLine( String line )
    {
        stdoutBuilder.append( line ).append( "\n" );
    }

    final void appendErrorOutputLine( String line )
    {
        stderrBuilder.append( line ).append( "\n" );
    }
}
