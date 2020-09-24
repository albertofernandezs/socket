package py.una.server.tcp;

import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;

import py.una.entidad.Persona;

import java.io.*;



public class TCPMultiServer {

	//variables compartidas
	boolean listening = true;
	List<TCPServerHilo> hilosClientes; //almacenar los hilos (no se utiliza en el ejemplo, se deja para que el alumno lo utilice)
	List<Persona> usuarios; //almacenar una lista de usuarios (no se utiliza, se deja para que el alumno lo utilice)
	//crear un hash map de id persona y su socket
	Map<Long, Persona> clientes = new HashMap<Long, Persona> ();
    public void ejecutar() throws IOException {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            System.err.println("No se puede abrir el puerto: 4444.");
            System.exit(1);
        }
        System.out.println("Puerto abierto: 4444.");

        while (listening) {
        	
        	TCPServerHilo hilo = new TCPServerHilo(serverSocket.accept(), this);
            hilosClientes.add(hilo);
            //
            hilo.start();
        }

        serverSocket.close();
    }

    public void registro(String origen, String destino) throws FileNotFoundException {
        PrintWriter r = new PrintWriter("registro.txt");
        r.println(origen+" "+destino+" "+java.time.LocalDateTime.now()+" "+"4444");
        r.close();
    }
    
    public static void main(String[] args) throws IOException {
    	
    	TCPMultiServer tms = new TCPMultiServer();
    	
    	tms.hilosClientes = new ArrayList<TCPServerHilo>();
    	tms.usuarios = new ArrayList<Persona>();
    	
    	tms.ejecutar();
    	
    }
}
