package empresa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import serializadores.SerializarCliente;

public class Cliente implements Serializable{

	private String NIF;
	private String nombre;
	private int telefono;
	private String direccion;
	private double deuda;
	
	public String getNIF() {
		return NIF;
	}
	public void setNIF(String NIF) {
		this.NIF = NIF;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public double getDeuda() {
		return deuda;
	}
	public void setDeuda(double deuda) {
		this.deuda = deuda;
	}
	
	public Cliente(String NIF, String nombre, int telefono, String direccion, double deuda) {
		
		this.NIF = NIF;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		deuda = 0;
	}
	
	public Cliente() {
	}
	
	@Override
	public String toString() {
		return "El Cliente tiene el NIF: " + getNIF() + ", su nombre es:" + getNombre() +
				", su teléfono: " + getTelefono()+ ", su dirección: " + getDireccion() 
				+ ", y tiene una deuda de: " + getDeuda() + "€.";
	}
	

}
