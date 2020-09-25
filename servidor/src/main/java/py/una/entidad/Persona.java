package py.una.entidad;
import java.util.ArrayList;
import java.util.List;
import java.net.Socket;
public class Persona {

	Long id;
	String nombre;
	Integer operacion;
	boolean disponible;
	Socket socket = null;
	String destino;
	

	
	public Persona(){	
	}
	public Persona(Long id, String nombre, Integer operacion, boolean disponible, String destino){
		this.id = id;
		this.nombre = nombre;
		this.operacion=operacion;
		this.disponible=disponible;
		this.destino=destino;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getOperacion() {
		return operacion;
	}

	public void setOperacion(Integer operacion) {
		this.operacion = operacion;
	}

	public boolean getDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	public java.net.Socket getSocket() {
		return socket;
	}
	public void setSocket(java.net.Socket socket) {
		this.socket = socket;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	
	
	
	
	
}
