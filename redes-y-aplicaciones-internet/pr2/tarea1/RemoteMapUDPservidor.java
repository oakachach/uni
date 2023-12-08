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

package udp.servidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.PortUnreachableException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import edu.uoc.dpcs.lsim.logger.LoggerManager.Level;
import lsim.library.api.LSimLogger;

/**
 * @author Joan-Manuel Marques
 *
 */

public class RemoteMapUDPservidor {
	
	public RemoteMapUDPservidor(int server_port, Map<String, String> map){
		LSimLogger.log(Level.INFO, "Inici RemoteMapUDPservidor ");
		LSimLogger.log(Level.INFO, "server_port: " + server_port);
		LSimLogger.log(Level.INFO, "map: " + map);

		// Server waits for requests a maximum time (timeout_time)
		Timer timer = new Timer();
		timer.schedule(
				new TimerTask() {
					@Override
					public void run() {
						System.exit(0);
					}
				},
				90000 // 90 seconds
				); 
		
		DatagramSocket socket = null;
		DatagramPacket paquete;
		byte[] mensaje_bytes;
		String mensaje;
		InetAddress adr;
		int port;
		
		LSimLogger.log(Level.INFO, "Se crea un servidor por el puerto " + server_port + "que espera conexiones de potenciales clientes");
		
		try {
			socket = new DatagramSocket(server_port);
			
			for (int i = 0; i < map.size(); i++) {
				LSimLogger.log(Level.INFO, "Se declaran e inicializan las variables.");
				
				mensaje_bytes = new byte[256];
				paquete = new DatagramPacket(mensaje_bytes, mensaje_bytes.length);
				
				LSimLogger.log(Level.INFO, "Se inicia el intercambio de mensajes");
				socket.receive(paquete);
				mensaje = new String(paquete.getData()).trim();
				LSimLogger.log(Level.INFO, "Hemos recibido el mensaje " + mensaje);
				LSimLogger.log(Level.INFO, "La clave es: " + map.get(mensaje));

				port = paquete.getPort();
				adr = paquete.getAddress();
				mensaje = map.get(mensaje);
				LSimLogger.log(Level.INFO, "Enviamos el paquete");
				LSimLogger.log(Level.INFO, "mensaje: " + mensaje);
				LSimLogger.log(Level.INFO, "dirección " + adr);
				LSimLogger.log(Level.INFO, "puerto " + port);
				paquete = new DatagramPacket(mensaje.getBytes(), mensaje.length(), adr, port);
				socket.send(paquete);
			}
			
			socket.close();
		} catch(IOException e) {
			e.printStackTrace();
			System.err.println(e);
		}	  
	}
}
