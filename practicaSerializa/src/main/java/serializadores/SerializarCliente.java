package serializadores;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonSerializer;
import com.esotericsoftware.jsonbeans.JsonValue;

import empresa.Cliente;



public class SerializarCliente implements JsonSerializer<Cliente>  {

	
	@Override
	public Cliente read(Json json, JsonValue valor, Class type) {
		
		String NIF = valor.getString("NIF");
		String nombre = valor.getString("nombre");
		int telefono = valor.getInt("telefono");
		String direccion = valor.getString("direccion");
		double deuda = valor.getDouble("deuda");
		
		Cliente cliente = new Cliente(NIF, nombre, telefono, direccion, deuda);
		
		return cliente;
	}
	
	
	@Override
	public void write(Json json, Cliente cliente, Class type) {
		
		json.writeObjectStart();
		json.writeValue("NIF", cliente.getNIF());
		json.writeValue("nombre", cliente.getNombre());
		json.writeValue("telefono", cliente.getTelefono());
		json.writeValue("direccion", cliente.getDireccion());
		json.writeValue("deuda", cliente.getDeuda());
		json.writeObjectEnd();		
	}
}
