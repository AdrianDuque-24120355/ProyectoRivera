package menu;

import cine.Cine;
import java.util.Scanner;

import compraBoletos.ComprasBoletos;
import pelicula.Pelicula;
import sala.Sala;
import usuarios.cliente.Cliente;

public class Menu_Cliente {

    Scanner read = new Scanner(System.in);
    Cine cine = new Cine();

    final int  boletoPremium=200;
    final int boletoVIP=400;
    final int boletoRegular=100;


    public int mostrarMenuCliente() {
        System.out.println("\n---SISTEMA CINE---");
        System.out.println("1. Comprar boletos");
        System.out.println("2. Ver Cartelera");
        System.out.println("4. Salir");

        System.out.println("Ingrese una opcion: ");
        int opcion = read.nextInt();

        return opcion;
    }

    public void procesarDatosMenu(int opcion, Cliente cliente, Cine cine) {
        switch (opcion) {
            case 1:
                System.out.println("\n----Compra de Boletos----");
                System.out.println("Boleto regular: $"+boletoRegular);
                System.out.println("Boleto Premium: $"+boletoPremium);
                System.out.println("Boleto VIP: $"+boletoVIP);

                String idCompra=cine.generarIDCompra();

                Pelicula nombrepeliculaval=null;
                while(nombrepeliculaval== null) {
                    System.out.print("Ingresar nombre de la película que desea ver: ");
                    read.nextLine();
                    String nombrePelicula = read.nextLine();
                    nombrepeliculaval=cine.obtenerPeliculaPorNombre(nombrePelicula);
                    if(nombrepeliculaval == null){
                        System.out.println("Esa película no existe");
                    }
                }
                double precio=cine.descuento();
                Sala nombresalaval=null;
                while(nombresalaval== null) {
                    System.out.print("Ingresar nombre de la sala donde desea ver su pelicula: ");
                    read.nextLine();
                    String nombreSala = read.nextLine();
                    nombresalaval=cine.obtenerSalaporNombre(nombreSala);
                    if(nombrepeliculaval == null){
                        System.out.println("Esa sala no existe");
                    }
                }
                System.out.print("Seleccione la fila del asiento: ");
                int filaasiento = read.nextInt();
                System.out.print("Seleccione la columna del asiento: ");
                int columnaasiento = read.nextInt();
                String asientoCompra= String.valueOf(filaasiento+columnaasiento);
                cine.seleccionAsientos(filaasiento, columnaasiento, nombresalaval.getMatrizAsiento());
                ComprasBoletos boleto=new ComprasBoletos(idCompra, nombresalaval, asientoCompra, cliente, nombrepeliculaval, precio, );



                break;
            case 2:
                System.out.println("\n-----Ver Cartelera----");
                cine.mostrarPeliculas();
                break;
            case 3:
                System.out.println("Gracias por usar el Sistema Integral de Gestión para Cinépolis");
                break;
        }
    }
}
