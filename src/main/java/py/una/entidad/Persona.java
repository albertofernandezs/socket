package py.una.entidad;
import java.util.ArrayList;
import java.util.List;
import java.net.Socket;
public class Persona {

	Long id;
	String nombre;
	Integer operacion;
	boolean conectado;
	Socket socket = null;
	

	
	public Persona(){	
	}
	public Persona(Long id, String nombre, Integer operacion, boolean conectado){
		this.id = id;
		this.nombre = nombre;
		this.operacion=operacion;
		this.conectado=conectado;
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

	public boolean getConectado() {
		return conectado;
	}

	public void setConectado(boolean conectado) {
		this.conectado = conectado;
	}
	public java.net.Socket getSocket() {
		return socket;
	}
	public void setSocket(java.net.Socket socket) {
		this.socket = socket;
	}
	
	
}
