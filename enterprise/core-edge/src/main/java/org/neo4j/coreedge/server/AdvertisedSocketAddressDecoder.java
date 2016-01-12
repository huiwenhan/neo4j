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
package org.neo4j.coreedge.server;

import java.nio.ByteBuffer;

import io.netty.buffer.ByteBuf;

import org.neo4j.coreedge.raft.replication.StringMarshal;

public class AdvertisedSocketAddressDecoder
{
    public AdvertisedSocketAddress decode( ByteBuf buffer )
    {
        String host = StringMarshal.deserialize( buffer );
        int port = buffer.readInt();

        return new AdvertisedSocketAddress( host + ":" + port );
    }

    public AdvertisedSocketAddress decode( ByteBuffer buffer )
    {
        String host = StringMarshal.deserialize( buffer );
        int port = buffer.getInt();

        return new AdvertisedSocketAddress( host + ":" + port );
    }
}