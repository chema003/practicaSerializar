package empresa;

import java.util.ArrayList;
import java.util.Collection;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.OutputType;
import serializadores.SerializarCliente;


@SuppressWarnings("unused")
public class Menu {

	static Collection <Cliente> clientesEmpresa = new ArrayList<>();
	static String rutaJson = "datos/clientes.dat";
	
	public static void main(String[] args) {
		Json json = new Json();
//		Collection <Cliente> clientesEmpresa = new ArrayList<>();
		
		
		
		Cliente cliente1 = new Cliente("11111111A", "nombre1", 911111111, "direcc1", 1000);
		clientesEmpresa.add(cliente1);
		
	

	}
	
	public static void anadirCliente (Cliente c) {
//		Collection<Cliente> clientesEmpresa = new ArrayList<>();
		String rutaJson = "datos/clientes.dat";
		Json json = new Json();
		json.setSerializer(Cliente.class, new SerializarCliente());
		clientesEmpresa .add(c);
		guardarListaClientesEnJson(clientesEmpresa, rutaJson);//No sé por qué no me reconoce el método.
	
		
	}
	
	public static void listarClientes(String ruta) {
//		Collection<Cliente> clientesEmpresa = new ArrayList<>();
		String rutaJson = "datos/clientes.dat";
		clientesEmpresa.getCliente(rutaJson);//¿?
		System.out.println(clientesEmpresa);
		
	}
	
	public static Cliente buscarCliente (String NIF) {
		Cliente clienteBuscado = new Cliente();
		clienteBuscado.setNIF(NIF);
		clientesEmpresa.getCliente(rutaJson);//¿?
		
		for (Cliente cliente : clientesEmpresa) {
			if(cliente.getNIF()==clienteBuscado.getNIF()) {
				clienteBuscado = cliente;
			}else {
				clienteBuscado = null;
				System.out.println("Cliente no encontrado");
			}
		}
		System.out.println("Cliente encontrado en su lista de clientes");
		return clienteBuscado;
	}
	
	public static void borrarCliente (Cliente clienteABorrar) {
		clientesEmpresa.getCliente(rutaJson);//¿? Hay que leerlo cada vez?
		
		for (Cliente cliente : clientesEmpresa) {
			if(clientesEmpresa.contains(clienteABorrar))
				clienteABorrar=null;
		}
		guardarListaClientesEnJson(clientesEmpresa);//Se debe hacer esto para actualizar??
		
		}
		
	

}
