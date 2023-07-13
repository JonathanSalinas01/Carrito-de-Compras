package Carrito;

public class Cliente {
   private String nombre;
	private int dni;
	private String cuil;
	
	public Cliente(String nomb, int docu, String cuil) {
		nombre = nomb;
		dni = docu;
		this.cuil = cuil;
	}
	public String dameNombre() {
		return nombre;
	}
	
	public int dameDocu() {
		return dni;
	}
	
	public String dameCuil(){
		return cuil;
	}
	

	public void mostrarCliente() {
		System.out.println("Nombre completo: " + nombre + " " + "DNI: " + dni);
		System.out.println("Cuil: " + cuil);
	} 
}
