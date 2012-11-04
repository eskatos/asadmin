/*
 * Copyright (c) 2010, Paul Merlin.
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

import org.apache.commons.lang.exception.NestableException;

public class AsAdminException
    extends NestableException
{

    private static final long serialVersionUID = 1L;

    public AsAdminException()
    {
    }

    public AsAdminException( final String message )
    {
        super( message );
    }

    public AsAdminException( final String message, Throwable cause )
    {
        super( message, cause );
    }

}
