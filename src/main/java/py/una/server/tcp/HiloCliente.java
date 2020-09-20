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
		
		while(continuar) {
			try {
				System.out.println(this.in.readLine());
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
