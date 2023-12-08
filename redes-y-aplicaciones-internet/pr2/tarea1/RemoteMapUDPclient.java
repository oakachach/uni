/*

* Copyright (c) Joan-Manuel Marques 2013. All rights reserved.
* DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
*
* This file is part of the practical assignment of Distributed Systems course.
*
* This code is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This code is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this code.  If not, see <http://www.gnu.org/licenses/>.
*/

package udp.client;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.PortUnreachableException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.uoc.dpcs.lsim.logger.LoggerManager.Level;
import lsim.library.api.LSimLogger;


/**
 * @author Joan-Manuel Marques
 *
 */

public class RemoteMapUDPclient {

	public RemoteMapUDPclient() {
	}
	
	public Map<String, String> getMap (List<Key> keys) {
		Map<String, String> map = new HashMap<String, String>();
		int i = 1;
		for (Key key : keys) {
			LSimLogger.log(
					Level.TRACE,
					"["+i+"] Query for key "+key.getKey()+" at "+ key.getServerAddress() +":"+key.getServerPort()
					);

			String value = get(key.getKey(), key.getServerAddress(), key.getServerPort());

			LSimLogger.log(Level.TRACE, "["+i+"] RemoteMap("+key.getKey()+"): "+ value);
			i++;
			map.put(key.getKey(), value);
		}

		return map;
	}
	
	private String get(String key, String server_address, int server_port) {
		LSimLogger.log(Level.INFO, "inici RemoteMapUDPclient.get ");
		LSimLogger.log(Level.TRACE, "key: " + key);
		LSimLogger.log(Level.TRACE, "server_address: " + server_address);
		LSimLogger.log(Level.TRACE, "server_port: " + server_port);
		
		DatagramSocket socket = null;
		DatagramPacket paquete;
		InetAddress adr;
		String resposta = null;
		
		LSimLogger.log(Level.TRACE, "Se crea un socket cliente llamado socket que se conectará al servidor UDP.");
		
		try {
			socket = new DatagramSocket();
			adr = InetAddress.getByName(server_address);
			
			LSimLogger.log(Level.TRACE, "Se inicia el intercambio de mensajes.");
			
			paquete = new DatagramPacket(key.getBytes(), key.length(), adr, server_port);
			socket.send(paquete);
			
			byte[] mensaje_bytes = new byte[256];
			paquete = new DatagramPacket(mensaje_bytes, mensaje_bytes.length);
			
			LSimLogger.log(Level.TRACE, "Esperamos a recibir de vuelta el paquete.");
			
			socket.receive(paquete);
			resposta = new String(paquete.getData()).trim();
			
			LSimLogger.log(Level.TRACE, "respuesta: " + resposta);
			
			socket.close();
		} catch(IOException e) {
			e.printStackTrace();
			System.err.println(e);
		}
		
		return resposta;
	}
}