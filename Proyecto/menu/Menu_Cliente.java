package menu;

import cine.Cine;
import java.util.Scanner;

import cine.ValidarCine;
import compraBoletos.ComprasBoletos;
import compraBoletos.ComprasCompleja;
import pelicula.Pelicula;
import pelicula.utils.Categoria;
import sala.Sala;
import sala.utils.Asiento;
import usuarios.cliente.Cliente;

public class Menu_Cliente {

    Scanner read = new Scanner(System.in);
    Cine cine = new Cine();
    ValidarCine validador = new ValidarCine();

    final int  boletoPremium=200;
    final int boletoVIP=400;
    final int boletoRegular=100;


    public int mostrarMenuCliente() {
        System.out.println("\n---SISTEMA CINE---");
        System.out.println("1. Ver Cartelera");
        System.out.println("2. Comprar boletos");
        System.out.println("3. Ver mis boletos");
        System.out.println("4. Realizar compras adicional");
        System.out.println("5. Salir");

        System.out.print("Ingrese una opcion: ");
        int opcion = read.nextInt();

        return opcion;
    }

    public void procesarDatosMenu(int opcion, Cliente cliente, Cine cine) {
        switch (opcion) {
            case 2:
                int boletocont=0;
                System.out.println("\n----Compra de Boletos----");
                boolean promocion = validador.mesNacimiento(cliente);
                System.out.println("Boleto regular: $"+boletoRegular);
                System.out.println("Boleto Premium: $"+boletoPremium);
                System.out.println("Boleto VIP: $"+boletoVIP);

                String idCompra=cine.generarIDCompra();

                Pelicula nombrepeliculaval=null;
                String nombrePelicula="";
                while(nombrepeliculaval== null) {
                    System.out.print("Ingresar nombre de la película que desea ver: ");
                    read.nextLine();
                    nombrePelicula = read.nextLine();
                    nombrepeliculaval=cine.obtenerPeliculaPorNombre(nombrePelicula);
                    if(nombrepeliculaval == null){
                        System.out.println("Esa película no existe");
                    }
                }
                Sala nombresalaval=null;
                String nombreSala="";
                while(nombresalaval== null) {
                    System.out.print("Ingresar nombre de la sala donde desea ver su pelicula: ");
                    nombreSala = read.nextLine();
                    nombresalaval=cine.obtenerSalaporNombre(nombreSala);
                    if(nombrepeliculaval == null){
                        System.out.println("Esa sala no existe");
                    }
                }
                boolean flag=false;
                do {
                    System.out.println("1. Normal");
                    System.out.println("2. Premium");
                    System.out.println("3. VIP");
                    System.out.print("Elige un tipo de asiento: ");
                    int opc = read.nextInt();

                    Asiento asiento=Asiento.NORMAL;
                    boolean band =false;
                    while(!band){

                        switch (opc) {
                            case 1:
                                asiento = Asiento.NORMAL;
                                band = true;
                                break;
                            case 2:
                                 asiento = Asiento.PREMIUM;
                                band = true;
                                break;
                            case 3:
                                 asiento = Asiento.VIP;
                                band = true;
                                break;
                            default:
                                System.out.println("No existe esa opción");
                        }
                    }

                    switch (opc) {
                        case 1:
                            System.out.println("-----Asiento normal-----");
                            System.out.print("Ingrese la cantidad de boletos que desea adquirir: ");
                            int cantidadBoletos = read.nextInt();
                            cine.mostrarAsiento(nombresalaval.getMatrizAsiento(), Asiento.NORMAL);
                            double precio = cine.asientoRegular(cantidadBoletos, promocion);
                            int c=0;
                            do {
                                System.out.print("Ingrese la fila donde se encuetra el asiento que desea reservar: ");
                                int fila = read.nextInt();
                                System.out.print("Ingresa la columna donde se encuentra el asiento que desea reservar: ");
                                int columna = read.nextInt();
                                cine.seleccionAsientos(fila, columna, nombresalaval.getMatrizAsiento());
                                String asientoSeleccionado= fila + "" + columna;
                                c++;
                                ComprasBoletos boleto=new ComprasBoletos(idCompra, nombreSala, asientoSeleccionado,
                                        cliente.getNombre(), nombrePelicula, precio,  asiento, promocion);
                                cine.registrarBoleto(boleto);
                                boletocont++;
                            }while(c<cantidadBoletos);
                            if(boletocont>0){
                                System.out.println("¿Desea realizar compras adicionales?");
                                System.out.println("1. SI");
                                System.out.println("2. NO");
                                int res=read.nextInt();
                                if (res == 1) {
                                    System.out.println("\n----Compra compleja----");
                                    ComprasCompleja compra = new ComprasCompleja();

                                   double preciototal= compra.completarCompras(precio);
                                    System.out.println("El total es de: "+preciototal);
                                }

                                    System.out.println("El total es de: "+precio);
                                    System.out.println("1. Tarjeta de Crédito");
                                    System.out.println("2. Tarjeta de Débito");
                                    System.out.println("3. Efectivo");
                                    System.out.print("Ingrese el método pago: ");
                                    int pago = read.nextInt();
                                    switch(pago){
                                        case 1:
                                            System.out.print("Ingrese el número de su tarjeta de crédito: ");
                                            String tarjcred= read.next();
                                            System.out.println("Fecha de caducidad");
                                            String fechacred= read.next();
                                            System.out.print("Ingrese su CVV");
                                            String cvv= read.next();
                                            read.nextLine();
                                            break;
                                        case 2:
                                            System.out.print("Ingrese el número de su tarjeta de débito: ");
                                            String tarjdeb= read.next();
                                            System.out.println("Fecha de caducidad");
                                            String fechadeb= read.next();
                                            System.out.print("Ingrese su CVV");
                                            String cvvdeb= read.next();
                                            break;
                                        case 3:
                                            System.out.println("Generando tu orden compra...");
                                            System.out.println("\n...");
                                            System.out.println("\n...");
                                            System.out.println("\n...(En un momento)\n");
                                            System.out.println("Tu órden de compra es: "+ cine.generarOrdenCompra(cliente));
                                     }
                            }
                            flag=true;
                            break;
                        case 2:
                            System.out.println("-----Asiento Premium-----");
                            System.out.print("Ingrese la cantidad de boletos que desea adquirir: ");
                            cantidadBoletos = read.nextInt();
                            cine.mostrarAsiento(nombresalaval.getMatrizAsiento(), Asiento.PREMIUM);
                            precio = cine.asientoPremium(cantidadBoletos, promocion);
                             c=0;
                            do {
                                System.out.print("Ingrese la fila donde se encuetra el asiento que desea reservar: ");
                                int fila = read.nextInt();
                                System.out.print("Ingresa la columna donde se encuentra el asiento que desea reservar: ");
                                int columna = read.nextInt();
                                cine.seleccionAsientos(fila, columna, nombresalaval.getMatrizAsiento());
                                c++;
                                String asientoSeleccionado= fila + "" + columna;
                                ComprasBoletos boleto=new ComprasBoletos(idCompra, nombreSala, asientoSeleccionado,
                                        cliente.getNombre(), nombrePelicula, precio,  asiento, promocion);
                                cine.registrarBoleto(boleto);
                                boletocont++;

                            } while(c<cantidadBoletos);

                            if(boletocont>0){
                                System.out.println("¿Desea realizar compras adicionales?");
                                System.out.println("1. SI");
                                System.out.println("2. NO");
                                int res=read.nextInt();
                                if (res == 1) {
                                    System.out.println("\n----Compra compleja----");
                                    ComprasCompleja compra = new ComprasCompleja();

                                    double preciototal= compra.completarCompras(precio);
                                    System.out.println("El total es de: "+preciototal);
                                }

                                    System.out.println("El total es de: "+precio);
                                    System.out.println("1. Tarjeta de Crédito");
                                    System.out.println("2. Tarjeta de Débito");
                                    System.out.println("3. Efectivo");
                                    System.out.print("Ingrese el método pago: ");
                                    int pago = read.nextInt();
                                    switch(pago){
                                        case 1:
                                            System.out.print("Ingrese el número de su tarjeta de crédito: ");
                                            String tarjcred= read.next();
                                            System.out.println("Fecha de caducidad");
                                            String fechacred= read.next();
                                            System.out.print("Ingrese su CVV");
                                            String cvv= read.next();
                                            read.nextLine();
                                            break;
                                        case 2:
                                            System.out.print("Ingrese el número de su tarjeta de débito: ");                                            String tarjdeb= read.next();
                                            System.out.println("Fecha de caducidad");
                                            String fechadeb= read.next();
                                            System.out.print("Ingrese su CVV");
                                            String cvvdeb= read.next();
                                            read.nextLine();
                                            break;
                                        case 3:
                                            System.out.println("Generando tu orden compra...");
                                            System.out.println("\n...");
                                            System.out.println("\n...");
                                            System.out.println("\n...(En un momento)\n");
                                            System.out.println(cine.generarOrdenCompra(cliente));
                                    }
                            }
                            flag=true;
                            break;
                        case 3:
                            System.out.println("-----Asiento VIP-----");
                            System.out.print("Ingrese la cantidad de boletos que desea adquirir: ");
                            cantidadBoletos = read.nextInt();
                            cine.mostrarAsiento(nombresalaval.getMatrizAsiento(), Asiento.VIP);
                            precio = cine.asientoVIP(cantidadBoletos, promocion);
                             c=0;
                            do {
                                System.out.print("Ingrese la fila donde se encuetra el asiento que desea reservar: ");
                                int fila = read.nextInt();
                                System.out.print("Ingresa la columna donde se encuentra el asiento que desea reservar: ");
                                int columna = read.nextInt();
                                cine.seleccionAsientos(fila, columna, nombresalaval.getMatrizAsiento());
                                c++;
                                String asientoSeleccionado= fila + "" + columna;
                                ComprasBoletos boleto=new ComprasBoletos(idCompra, nombreSala, asientoSeleccionado,
                                        cliente.getNombre(), nombrePelicula, precio,  asiento, promocion);
                                cine.registrarBoleto(boleto);
                                boletocont++;
                            }while(c<cantidadBoletos);
                            if(boletocont>0) {
                                System.out.println("¿Desea realizar compras adicionales?");
                                System.out.println("1. SI");
                                System.out.println("2. NO");
                                int res = read.nextInt();
                                if (res == 1) {
                                    System.out.println("\n----Compra compleja----");
                                    ComprasCompleja compra = new ComprasCompleja();

                                    double preciototal = compra.completarCompras(precio);
                                    System.out.println("El total es de: " + preciototal);
                                }

                                    System.out.println("El total es de: "+precio);
                                    System.out.println("1. Tarjeta de Crédito");
                                    System.out.println("2. Tarjeta de Débito");
                                    System.out.println("3. Efectivo");
                                    System.out.print("Ingrese el método pago: ");
                                    int pago = read.nextInt();
                                    switch(pago){
                                        case 1:
                                            System.out.print("Ingrese el número de su tarjeta de crédito: ");                                            String tarjcred= read.next();
                                            System.out.println("Fecha de caducidad");
                                            String fechacred= read.next();
                                            System.out.print("Ingrese su CVV");
                                            String cvv= read.next();
                                            read.nextLine();
                                            break;
                                        case 2:
                                            System.out.print("Ingrese el número de su tarjeta de débito: ");
                                            String tarjdeb= read.next();
                                            System.out.println("Fecha de caducidad");
                                            String fechadeb= read.next();
                                            System.out.print("Ingrese su CVV");
                                            String cvvdeb= read.next();
                                            break;
                                        case 3:
                                            System.out.println("Generando tu orden compra...");
                                            System.out.println("\n...");
                                            System.out.println("\n...");
                                            System.out.println("\n...(En un momento)\n");
                                            System.out.println(cine.generarOrdenCompra(cliente));
                                    }
                            }
                            flag=true;
                            break;
                        default:
                            System.out.println("Esa opcion no existe");
                    }
                }while(!flag);



                break;
            case 1:
                System.out.println("\n-----Ver Cartelera----");
                cine.mostrarPeliculas();
                break;
            case 3:
                System.out.println("\n-----Ver mis boletos-----");
                cine.mostrarBoletos();
            case 4:
                System.out.println("\n-----Realizar compras-----");

                break;
            case 5:
                System.out.println("Gracias por usar el Sistema Integral de Gestión para Cinépolis");
                break;
        }
    }
}
