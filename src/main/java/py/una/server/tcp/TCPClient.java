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

            //viene del servidor
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
        String fromUser,nombre;
        Long id;
        int opcion;
        
        //pasarle el objeto persona al servidor en formato Json
        System.out.println("Ingrese su id: ");
        id=Long.parseLong(stdIn.readLine());
        System.out.println("Ingrese su nombre: ");
        nombre=stdIn.readLine();
        Persona cliente= new Persona(id,nombre,0,true);
        fromUser= PersonaJSON.objetoString(cliente);
        out.println(fromUser);
        
        while ((fromServer = in.readLine()) != null) {
        	SubMenu();
        	System.out.println(fromServer);
            opcion = Integer.parseInt(stdIn.readLine());
            if (opcion==5) {
            	cliente.setOperacion(5);
            	fromUser= PersonaJSON.objetoString(cliente);
                out.println(fromUser);
            	break;
            }else if(opcion==1) {
            	cliente.setOperacion(1);
            	fromUser= PersonaJSON.objetoString(cliente);
                out.println(fromUser);
                
            }
        }

        out.close();
        in.close();
        stdIn.close();
        unSocket.close();
    }
    
    public static void SubMenu() {
    	System.out.println("1- Listar personas ");
    	System.out.println("5- Cerrar Sesion ");
    }
}
