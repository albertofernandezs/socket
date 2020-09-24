package py.una.server.tcp;

import java.io.*;
import java.net.*;

import py.una.entidad.Persona;
import py.una.entidad.PersonaJSON;

public class TCPClient {

	public static void main(String[] args) throws IOException {

		Socket unSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		try {
			unSocket = new Socket("localhost", 4444);
			// enviamos nosotros
			out = new PrintWriter(unSocket.getOutputStream(), true);

			// viene del servidor
			in = new BufferedReader(new InputStreamReader(unSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Host desconocido");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Error de I/O en la conexion al host");
			System.exit(1);
		}

		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String fromServer;
		String fromUser, nombre, destino, mensaje;
		Long id;
		int opcion;

		// pasarle el objeto persona al servidor en formato Json
		System.out.println("Ingrese su id: ");
		id = Long.parseLong(stdIn.readLine());
		System.out.println("Ingrese su nombre: ");
		nombre = stdIn.readLine();
		Persona cliente = new Persona(id, nombre, 0, true, "");
		fromUser = PersonaJSON.objetoString(cliente);
		out.println(fromUser);

		while (true) {
			// (fromServer = in.readLine()) != null
			SubMenu();
			// System.out.println(fromServer);
			opcion = Integer.parseInt(stdIn.readLine());
			if (opcion == 4) {
				cliente.setOperacion(4);
				fromUser = PersonaJSON.objetoString(cliente);
				out.println(fromUser);
				break;
			} else if (opcion == 1) {
				cliente.setOperacion(1);
				fromUser = PersonaJSON.objetoString(cliente);
				out.println(fromUser);
				fromServer = in.readLine();
				System.out.println(fromServer);
			} else if (opcion == 2) {
				System.out.println("A quien queres llamar? ");
				destino = stdIn.readLine();
				cliente.setOperacion(2);
				cliente.setDestino(destino); // falta validar si existe el destino
				fromUser = PersonaJSON.objetoString(cliente);
				out.println(fromUser);

				clearScreen();
				if (in.readLine().equals("disponible")) {
					System.out.println("Estas en una llamada con: " + destino + ", diga 'chau' para colgar la llamada");
					HiloCliente hilo = new HiloCliente(in);
					mensaje = stdIn.readLine();
					while (!mensaje.equals("chau")) {
						out.println(mensaje);
						mensaje = stdIn.readLine();
					}
					;
					out.println("chau");
					hilo.terminar();
				} else {
					System.out.println("La persona ya no esta disponible");
				}

			} else if (opcion == 3) {
				clearScreen();

				cliente.setOperacion(3);
				fromUser = PersonaJSON.objetoString(cliente);
				out.println(fromUser);
				System.out.println(
						"Se conecto en una llamada con: " + in.readLine() + ", diga 'chau' para colgar la llamada");
				HiloCliente hilo = new HiloCliente(in);
				mensaje = stdIn.readLine();
				while (!mensaje.equals("chau")) {
					out.println(mensaje);
					mensaje = stdIn.readLine();
				}
				;
				out.println("chau");
				hilo.terminar();

			}

		}

		out.close();
		in.close();
		stdIn.close();
		unSocket.close();
	}

	public static void SubMenu() {
		System.out.println("1- Listar personas ");
		System.out.println("2- Iniciar llamada ");
		System.out.println("3- Aceptar llamada ");
		System.out.println("4- Cerrar Sesion ");
	}

	public static void clearScreen() {
		for (int i = 0; i < 50; ++i)
			System.out.println();
		System.out.flush();
	}
}
