package Carrito;

public class Carrito {

    private int num;
    private Cliente cliente;
    private double montoTotal;
	
    public Carrito(Cliente cli, int num) {
        this.num = num;
        cliente = cli;
	montoTotal=0.0;
    }
    
    public int dameNum() {
	return num;
    }
    public double dameMontoTotal() {
	return montoTotal;
    }
    
    public void sumarMonto(ItemCarrito montoItem) {
	montoTotal = montoTotal + montoItem.dameMontoItem();
    }
    
    public String dameDatosCarro() {
	return "Carro Num: " + num + "Cliente: " + cliente.dameNombre();
    }

}
