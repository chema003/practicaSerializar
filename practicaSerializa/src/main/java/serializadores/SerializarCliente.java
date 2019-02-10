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
		
		Cliente cliente = new Cliente("NIF", "nombre", telefono, "direccion", deuda);
		
		return cliente;
	}
	
	public Collection<Cliente> getCliente (String rutaJson){
		
		Collection <Cliente> clientesEmpresa = new ArrayList<>();
		
		try(BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(rutaJson)))){
			String linea = reader.readLine();
			while((linea = reader.readLine()) != null) {
				clientesEmpresa.add(deserializarCliente(linea));
			}
			
		}catch (Exception e){
			e.printStackTrace();	
		}
		return clientesEmpresa;
	}

	public Cliente deserializarCliente(String linea) {
		
		String[] campos = linea.split(",");
		String NIF = campos[0];
		String nombre = campos[1];
		String telefono1 = campos[2];
		String direccion = campos[3];
		String deuda1 = campos[4];
		
		int telefono = Integer.parseInt(telefono1);
		double deuda = Double.parseDouble(deuda1);

		Cliente cliente = new Cliente(NIF, nombre, telefono, direccion, deuda);
		return cliente;
	}
	
	
	
	public void guardarListaClientesEnJson (Collection<Cliente> clientes, String ruta) {
		Json json = new Json();
		
		try(BufferedWriter writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(ruta), "UTF-8"))){
			for (Cliente cliente : clientes) {
				writer.write(json.toJson(cliente));
				writer.newLine();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
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
