
package Carrito;

public class Producto {
    private String nombre;
	private double precio;
	private int id;

	
	public Producto(String nom, double prec, int codigo) {
		nombre = nom;
		precio = prec;
		id = codigo;
	}
	
	public String unNombre(){
		return nombre; 
	}
	
	public double unPrecio() {
		return precio;
	}
	public int unId() {
		return id;
	}

	public void mostrarProducto() {
		System.out.println("Nombre: " + nombre +" -- " + "precio: " + precio +" -- " + "Código: " + id);
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println(" ");
	}
}
