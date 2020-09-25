package py.una.server.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class HiloCliente extends Thread{
	
	private boolean continuar=true;
	boolean desconectar=true;
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
				String mensaje=this.in.readLine();
				if(mensaje.equals("chau")) {
					System.out.println("Se ha cortado la llamada, presione enter para continuar");
					this.desconectar=false;
					break;
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

	public boolean isDesconectar() {
		return desconectar;
	}

	public void setDesconectar(boolean desconectar) {
		this.desconectar = desconectar;
	}
	
	
}
