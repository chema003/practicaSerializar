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

import empresa.Cliente;

public class GestorSerializador {

	public Collection<Cliente> getClientes (String rutaJson){
		
		Collection <Cliente> clientesEmpresa = new ArrayList<>();
		Json json = new Json();
		json.setSerializer(Cliente.class, new SerializarCliente());
		try(BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(rutaJson)))){
			
			String linea = reader.readLine();
			while((linea) != null) {
				
			Cliente cliente = json.fromJson(Cliente.class, linea);
		
				clientesEmpresa.add(cliente);				
				linea = reader.readLine();
			}
			
		}catch (Exception e){
			e.printStackTrace();	
		}
		return clientesEmpresa;
	}
	
	public void guardarListaClientesEnJson (Collection<Cliente> clientes, String ruta) {
		Json json = new Json();
		json.setSerializer(Cliente.class, new SerializarCliente());
		try(BufferedWriter writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(ruta), "UTF-8"))){
			for (Cliente cliente : clientes) {
				writer.write(json.toJson(cliente));
				writer.newLine();
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void guardarClienteEnJson (Cliente cliente, String ruta) {
		Json json = new Json();
		json.setSerializer(Cliente.class, new SerializarCliente());
		try(BufferedWriter writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(ruta, true), "UTF-8"))){
				writer.append(json.toJson(cliente));
				writer.newLine();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
