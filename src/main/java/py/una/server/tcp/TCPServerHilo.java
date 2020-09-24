package py.una.server.tcp;

import java.net.*;
import java.util.Iterator;

import py.una.entidad.Persona;
import py.una.entidad.PersonaJSON;

import java.io.*;

public class TCPServerHilo extends Thread {

	private Socket socket = null;

	TCPMultiServer servidor;

	public TCPServerHilo(Socket socket, TCPMultiServer servidor) {
		super("TCPServerHilo");
		this.socket = socket;
		this.servidor = servidor;
	}

	public void run() {

		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// out.println("Bienvenido!");
			String inputLine, mensajeEnviado, mensajeRecibido, outputLine = "";
			Persona personaDestino = new Persona();

			while (true) {
				Persona cliente = PersonaJSON.stringObjeto(in.readLine());
				int operacion = cliente.getOperacion();
				// System.out.println("Operacion recibida: " + operacion);

				if (operacion == 0) {
					servidor.usuarios.add(cliente);
					cliente.setSocket(this.socket);
					servidor.clientes.put(cliente.getId(), cliente);
					outputLine = "Usuario/a " + cliente.getNombre() + " agregado";
				} else if (operacion == 2) {

					for (Long key : servidor.clientes.keySet()) {
						String name = servidor.clientes.get(key).getNombre();
						if (name.equals(cliente.getDestino())) {
							personaDestino = servidor.clientes.get(key);
							servidor.clientes.get(key).setDestino(cliente.getNombre());
							cliente.setDestino(personaDestino.getNombre());
							cliente.setSocket(this.socket);
							servidor.clientes.put(cliente.getId(), cliente);
							break;
						}
					}
					if (personaDestino.getDisponible()) {
						
						out.println("disponible");
						cliente.setSocket(this.socket);
						cliente.setDisponible(false);
						servidor.clientes.put(cliente.getId(), cliente);
						
						Socket socketDestino = personaDestino.getSocket();
						PrintWriter out2 = new PrintWriter(socketDestino.getOutputStream(), true);
						while (true) {
							mensajeEnviado = in.readLine();
							// System.out.println("Mensaje recibido de "+cliente.getNombre()+" con el
							// mensaje "+mensajeEnviado);
							if (mensajeEnviado.equals("chau")) {
								break;
							}
							out2.println(mensajeEnviado);
							// bw.flush();

						}

						cliente.setDisponible(true);
						cliente.setSocket(this.socket);
						servidor.clientes.put(cliente.getId(), cliente);
					}else {
						out.println("no disponible");
					}

				} else if (operacion == 3) {
					// String nombreOrigen="";
					cliente.setDisponible(false);
					cliente.setSocket(this.socket);
					servidor.clientes.put(cliente.getId(), cliente);

					Persona origen = new Persona();
					for (Long key : servidor.clientes.keySet()) {
						origen = servidor.clientes.get(key);
						if (origen.getDestino().equals(cliente.getNombre())) {
							personaDestino = servidor.clientes.get(key);
							// System.out.println("Se agrego el socket");
							break;
						}
					}

					out.println(origen.getNombre());

					Socket socketDestino = personaDestino.getSocket();
					PrintWriter out2 = new PrintWriter(socketDestino.getOutputStream(), true);
					while (true) {
						mensajeEnviado = in.readLine();
						// System.out.println("Mensaje recibido de "+cliente.getNombre()+" con el
						// mensaje "+mensajeEnviado);
						if (mensajeEnviado.equals("chau")) {
							break;
						}
						out2.println(mensajeEnviado);
					}

					cliente.setDisponible(true);
					cliente.setSocket(this.socket);
					servidor.clientes.put(cliente.getId(), cliente);

				} else if (operacion == 4) {
					servidor.listening = false;
					outputLine = "Usted apago todo";
					Iterator<Persona> iter = servidor.usuarios.iterator();
					while (iter.hasNext()) {
						if (iter.next().getNombre().equals(cliente.getNombre())) {
							iter.remove();
							servidor.clientes.remove(cliente.getId());
							break;
						}
					}
					break;

				} else if (operacion == 1) {

					outputLine = "Lista de usuarios disponibles: ";

					for (Long key : servidor.clientes.keySet()) {
						Persona p= servidor.clientes.get(key);
						if(p.getDisponible() && (p.getNombre().equals(cliente.getNombre())) == false) {
							System.out.println("persona" +p.getNombre()+" disponible: "+p.getDisponible());
							outputLine = outputLine + " - " + p.getNombre();
						}
					}
					out.println(outputLine);
				} else {
					System.out.println("No se reconocio la operacion");
				}

				// out.println(outputLine);
			}
			out.close();
			in.close();
			socket.close();
			System.out.println("Finalizando Hilo");

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
