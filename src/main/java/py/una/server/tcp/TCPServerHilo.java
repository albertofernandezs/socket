package py.una.server.tcp;

import java.net.*;
import java.util.Iterator;

import py.una.entidad.Persona;
import py.una.entidad.PersonaJSON;

import java.io.*;

public class TCPServerHilo extends Thread {

    private Socket socket = null;

    TCPMultiServer servidor;
    
    public TCPServerHilo(Socket socket, TCPMultiServer servidor ) {
        super("TCPServerHilo");
        this.socket = socket;
        this.servidor = servidor;
    }

    public void run() {

        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                    socket.getInputStream()));
            //out.println("Bienvenido!");
            String inputLine, outputLine="";
            
            while (true) {
            	Persona cliente= PersonaJSON.stringObjeto(in.readLine());
                int operacion= cliente.getOperacion();
                System.out.println("Operacion recibida: " + operacion);
                
                if (operacion==0) {
                	servidor.usuarios.add(cliente);
                    cliente.setSocket(socket);
                    servidor.clientes.put(cliente.getId(), socket);
                    outputLine = "Usuario/a "+cliente.getNombre()+" agregado";
                }else if (operacion==5) {
                    servidor.listening = false;
                    outputLine = "Usted apago todo";
                    Iterator<Persona> iter = servidor.usuarios.iterator();
                    while (iter.hasNext()) { 
                    	if(iter.next().getNombre().equals(cliente.getNombre())) {
                    		iter.remove();
                    		servidor.clientes.remove(cliente.getId());
                    		break;
                    	}
                    } 
                    break;
                    
                }else if (operacion==1){
                
                	outputLine = "Lista de usuarios: " ;
                               	
                	Iterator<Persona> iter = servidor.usuarios.iterator();
                	
                    while (iter.hasNext()) { 
                    	outputLine = outputLine + " - " + iter.next().getNombre(); 
                    } 
                }else {
                	System.out.println("No se te reconocio la peracion");
                }
                
                out.println(outputLine);
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
