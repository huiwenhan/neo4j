/*
 * Copyright (c) 2002-2016 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.server.security.enterprise.auth;

import org.apache.shiro.authc.AuthenticationToken;

import java.util.Map;

import org.neo4j.kernel.api.security.AuthToken;
import org.neo4j.kernel.api.security.exception.InvalidAuthTokenException;

public class ShiroAuthToken implements AuthenticationToken
{
    private final Map<String,Object> authToken;

    public ShiroAuthToken( Map<String,Object> authToken )
    {
        this.authToken = authToken;
    }

    @Override
    public Object getPrincipal()
    {
        return authToken.get( AuthToken.PRINCIPAL );
    }

    @Override
    public Object getCredentials()
    {
        return authToken.get( AuthToken.CREDENTIALS );
    }

    public String getScheme() throws InvalidAuthTokenException
    {
        return AuthToken.safeCast( AuthToken.SCHEME_KEY, authToken );
    }

    public Map<String,Object> getAuthTokenMap()
    {
        return authToken;
    }

    /** returns true if token map does not specify a realm, or if it specifies the requested realm */
    public boolean supportsRealm( String realm )
    {
        return !authToken.containsKey( AuthToken.REALM_KEY ) ||
               authToken.get( AuthToken.REALM_KEY ).toString().length() == 0 ||
               authToken.get( AuthToken.REALM_KEY ).equals( "*" ) ||
               authToken.get( AuthToken.REALM_KEY ).equals( realm );
    }
}
