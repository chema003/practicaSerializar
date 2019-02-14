package empresa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.OutputType;

import serializadores.GestorSerializador;
import serializadores.SerializarCliente;


@SuppressWarnings("unused")
public class Menu {

	static String rutaJson = "datos/clientes.json";
	static Collection <Cliente> clientesEmpresa = new ArrayList<>();
	
	public static void main(String[] args) {
//		Json json = new Json();
//		Collection <Cliente> clientesEmpresa = new ArrayList<>();
		GestorSerializador gestor = new GestorSerializador();
	
		Cliente cliente1 = new Cliente("11111111A", "nombre1", 911111111, "direcc1", 1000);
		Cliente cliente2 = new Cliente("22222222B", "nombre2", 911111112, "direcc2", 2000);
//		anadirCliente(cliente1, gestor);
//		anadirCliente(cliente2, gestor);
//		anadirCliente(cliente1, gestor);
		
		Cliente buscado = buscarCliente("11111111A", gestor);
		if(buscado!=null) {
			System.out.println(buscado);
		}else {
			System.out.println("Cliente no encontrado.");
		}
		
		borrarCliente("11111111A", gestor);
		
		try {
			listarClientes(rutaJson, gestor);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void anadirCliente (Cliente c, GestorSerializador gestor) {
		
		clientesEmpresa = gestor.getClientes(rutaJson);
	
		if(clientesEmpresa.isEmpty()) {
			clientesEmpresa.add(c);
			System.out.println("Archivo vacio. Primer cliente.");
			gestor.guardarClienteEnJson(c, rutaJson);
			
		}else{
			boolean contenido = false;
			for (Cliente cliente : clientesEmpresa) {
				if(cliente.getNIF().equals(c.getNIF())){
					System.out.println("El cliente ya se encuentra entre los clientes de la empresa.");
					contenido = true;
				}
			}
			if(!contenido) {
				gestor.guardarClienteEnJson(c, rutaJson);
				System.out.println("Cliente nuevo añadido.");
			}
		}		
	}
	
	
	public static void listarClientes(String ruta, GestorSerializador gestor) throws IOException {
		
		List<String> listaJson = new ArrayList<>();
		try (BufferedReader br = Files.newBufferedReader(Paths.get(ruta))) {
			listaJson = br.lines().collect(Collectors.toList());
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		listaJson.forEach(System.out::println);
//			System.out.println(gestor.getClientes(ruta));
		
	}
	
	public static Cliente buscarCliente (String NIF, GestorSerializador gestor) {
		
		clientesEmpresa = gestor.getClientes(rutaJson);
	
		Cliente clienteBuscado = null;
		
		for (Cliente cliente : clientesEmpresa) {
			if(cliente.getNIF().equals(NIF)){
				clienteBuscado = cliente;
				System.out.println("Cliente encontrado en su lista de clientes");
			}
		}
		return clienteBuscado;
	}
	
	public static void borrarCliente (String  NIFABorrar, GestorSerializador gestor) {
	
		clientesEmpresa = gestor.getClientes(rutaJson);
		Collection<Cliente> listaAux = new ArrayList<>();
		
		for (Cliente cliente : clientesEmpresa) {
			if(!cliente.getNIF().equals(NIFABorrar)) {
				listaAux.add(cliente);
			}
		}
			gestor.guardarListaClientesEnJson(listaAux, rutaJson);

		}
//leer, deserializar, contine, incluyo todos menos el que quiero borrar en un Array aux. Y este Array lo guardo.	
	
	
}
