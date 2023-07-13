package Carrito;

public class ItemCarrito {
    	private Carrito carro;
	private Producto prod;
	private int cantidad;
	private double montoItem;
	private int numeroC;
	
	public ItemCarrito(Carrito num, Producto p, int cantidad) {
		carro= num;
		prod = p;
		this.cantidad=cantidad;
		montoItem= prod.unPrecio() * cantidad;	
	}
	public int dameCantidad() {
		return cantidad;
	}
	
	public double dameMontoItem() {
		return montoItem;
	}
	public String dameProducto() {
		return prod.unNombre();
	}
	public void mostrarItem() {
		
		System.out.println(cantidad+"\t"+montoItem+"\t"+String.format("%.2f",montoItem ));
	}
	public void mostrarItemTitulo() {
		System.out.println("Carro num: "+ carro.dameNum());
		System.out.println("Cant:\tPrecio:\tSub Total:\tProd:");
	}
}
