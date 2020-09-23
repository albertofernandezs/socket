package py.una.server.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class HiloCliente extends Thread{
	
	private boolean continuar=true;
	private BufferedReader in;
	
	public HiloCliente() {
		start();
	}
	
	public HiloCliente(BufferedReader in) {
		this.in=in;
		start();
	}
	
	@Override
	public void run() {
		String[] string;
		while(continuar) {
			try {
				String mensaje = this.in.readLine();
				if(mensaje.contains("solicitud.")) {
					string = mensaje.split(".");
					for(String s : string) {
						System.out.println(s);
					}
					//mensaje = string[1];
					System.out.println("Desea recibir un mensaje de "+ mensaje);
				}else {
					System.out.println(mensaje);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	public void terminar() {
		continuar=false;
	}
}
