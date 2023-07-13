package Carrito;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Compras {
    public static void main(String[] args) {
        
        try {
            //Primero se crea el objeto de tipo Connection que nos permitira conectarnos a la base de datos
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/carritocompras", "root", "");
            
            //Luego creamos el objeto de tipo Statement para poder realizar consultas o  updates a la base de datos
            Statement sT = conexion.createStatement();
            
            //Creamos una variable String con codigo SQL que nos permitira hacer las consultas mas facilmente
            String consulta = "select * from clientes";
            
            //Creamos una variable ResultSet para guardar los datos de la consulta a la base de datos
            ResultSet datosDB = sT.executeQuery(consulta);
            
            //Realizamos un ciclo while para iterar todos los valores de la tabla ubicada en la base de datos
            while(datosDB.next()){
                System.out.println(datosDB.getInt(1) + "\t" + datosDB.getString(2) + "\t" + datosDB.getInt(3) + "\t" + datosDB.getString(4));
            }
            //Creamos un objeto Scanner para leer datos desde la consola 
            Scanner in = new Scanner(System.in);
            
            //Obtenemos el ID del cliente
            System.out.println("Ingrese el ID del cliente");
            int idCli =  in.nextInt();
            
            consulta = String.format("select * from clientes where IDcliente = %s", idCli);
            datosDB = sT.executeQuery(consulta);
            
            //Definimos las variables para luego poder instanciar al cliente que realizará la compra
            String nombre = "", cuil = "";
            int dni = 0;
            
            System.out.println("***************************************************************************************");
            System.out.println(" ");
            System.out.println("El cliente seleccionado es: ");
            while(datosDB.next()){
                System.out.println(datosDB.getInt(1) + "\tNombre: " + datosDB.getString(2) + "\tDNI: " + datosDB.getInt(3) + "\tCUIL: " + datosDB.getString(4));
                
                //Asignamos los valores a las variables correspondientes para crear el cliente que realizará la compra
                nombre = datosDB.getString(2);
                dni = datosDB.getInt(3);
                cuil = datosDB.getString(4);
            }
            //Creamos el objeto de tipo Cliente
            Cliente cliente1 = new Cliente(nombre, dni, cuil);
            //cliente1.mostrarCliente();
            
            //Seleccionamos el número de carrito y le asignamos el cliente
            System.out.println("Ingrese el número del carrito");
            int numCarrito = in.nextInt();
            
            Carrito carrito = new Carrito(cliente1, numCarrito);
            
            
            
            //Mostramos los productos
            consulta = "select * from productos";
            datosDB = sT.executeQuery(consulta);
            
            System.out.println("Productos: ");
            boolean bandera = false; // Variable boolean para saber si se quiere agregar mas productos o no
            int codProduct = 0, cantidad = 0; //Variable auxiliares para crear los objetos productos;
            String nombProduct = ""; //Variable auxiliares para crear los objetos productos;
            double precioProduct = 0; //Variable auxiliares para crear los objetos productos;
            char desicion = 's';
            ArrayList<ItemCarrito> acumuladorProd = new ArrayList<ItemCarrito>(); //Se crea un ArrayList que almacenará los productos
            
            
            while(bandera == false){ //Ciclo que se utilizara para cargar los productos al carrito
                while(datosDB.next()){ //Ciclo para mostrar los productos de la base de datos 
                  //contador = contador + 1;
                    System.out.println(datosDB.getInt(1) + " " + datosDB.getString(2) + " " + datosDB.getNString(3) + " " + datosDB.getDouble(4));
                }
                System.out.println("Indique el ID del producto que quiere agregar al carrito");
                codProduct = in.nextInt();
                consulta = String.format("select * from productos where IDproducto = %s", codProduct);
                datosDB = sT.executeQuery(consulta);
                while(datosDB.next()){ //Ciclo para obtener los valores del producto seleccionado
                nombProduct = datosDB.getString(2);
                precioProduct = datosDB.getDouble(4);
                }
                Producto producto = new Producto(nombProduct, precioProduct, codProduct); //Creacion del objeto producto que eligio el cliente
                
                System.out.println("Indique la cantidad de productos que quiere agregar");
                cantidad = in.nextInt();
                
                ItemCarrito item1 = new ItemCarrito(carrito, producto, cantidad);
                acumuladorProd.add(item1);
                
                System.out.println("Si desea agregar mas productos al carrito presione (s), de lo contratio presione (n)");
                desicion = in.next().charAt(0);
                
                if(desicion == 'n'){
                bandera = true;

                }
                                  //Mostramos los productos
                consulta = "select * from productos";
                datosDB = sT.executeQuery(consulta);
                
            }
            System.out.println("***********************************************************************");
            System.out.println(" ");
            System.out.println("Productos Comprados:");
            System.out.println(" ");
            double total = 0;
            
            //Se crea la consulta para el update a la base de datos
            String consulta1 = "insert into carritoitems (IDcarrito, cliente, numCarro, nombreProd, cantidad, montoItem, fecha) values (IDcarrito, ?, ?, ?, ?, ?, ?)";
            
            //Se crea otro objeto de tipo Statement para realizar los updates.
            PreparedStatement sqlIn = conexion.prepareStatement(consulta1);
            LocalDate fecha = LocalDate.now();
            for(int i = 0;  i < acumuladorProd.size(); i++){ //Ciclo para mostrar el producto comprado, la cantidad y el subtotal de los productos seleccionados por consola.
                System.out.println("Producto: " + acumuladorProd.get(i).dameProducto() + " Cantidad: " + acumuladorProd.get(i).dameCantidad() + " Precio: $" + acumuladorProd.get(i).dameMontoItem());
                total = total + acumuladorProd.get(i).dameMontoItem(); //Aqui se acumulará el total de la compra.
                
                //Se carga los valores correspondientes a los signos de pregunta declarados en la consulta.
                sqlIn.setString(1, nombre);
                sqlIn.setInt(2, numCarrito);
                sqlIn.setString(3, acumuladorProd.get(i).dameProducto());
                sqlIn.setInt(4, acumuladorProd.get(i).dameCantidad());
                sqlIn.setDouble(5, acumuladorProd.get(i).dameMontoItem());
                sqlIn.setString(6, fecha.toString());
                
                sqlIn.executeUpdate(); //Se ejecuta la carga de los datos en la base de datos.
            }
            System.out.println("Total de la compra : $" + total);
            
            
            
           
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la conexion -- " +  e.toString());
        }
        
    }
}
