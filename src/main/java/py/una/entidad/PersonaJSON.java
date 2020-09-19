package py.una.entidad;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PersonaJSON {


    public static void main(String[] args) throws Exception {
    	PersonaJSON representacion = new PersonaJSON();
    	
    	System.out.println("Ejemplo de uso 1: pasar de objeto a string");
    	Persona p = new Persona();
    	p.setNombre("Maria");
    
    
    }
    public static String objetoString(Persona p) {	
    	
		JSONObject obj = new JSONObject();
        obj.put("id", p.getId());
        obj.put("nombre", p.getNombre());
        obj.put("operacion", (Integer)p.getOperacion());
        obj.put("conectado", p.getConectado());


        return obj.toJSONString();
    }
    
    
    public static Persona stringObjeto(String str) throws Exception {
    	Persona p = new Persona();
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(str.trim());
        JSONObject jsonObject = (JSONObject) obj;

        Long id = (Long) jsonObject.get("id");
        p.setId(id);
        
        p.setNombre((String)jsonObject.get("nombre"));
        
        Long op = (Long) jsonObject.get("operacion");
        p.setOperacion(op.intValue());
        p.setConectado((boolean)jsonObject.get("conectado"));
        return p;
	}

}
