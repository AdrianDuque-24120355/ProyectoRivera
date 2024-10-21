/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import cine.Cine;
import pelicula.Pelicula;
import pelicula.utils.Categoria;
import sala.Sala;
import usuarios.admin.Admin;
import usuarios.cliente.Cliente;

public class Menu_Admin {
    Scanner read = new Scanner(System.in);
    Cine cine = new Cine();

    public int mostrarMenuAdmin() {
        System.out.println("\nSISTEMA CINE");
        System.out.println("1. Registrar cliente");
        System.out.println("2. Registrar sala");
        System.out.println("3. Agregar película.");
        System.out.println("4. Modificar detalles de la película.");
        System.out.println("5. Mostrar sala.");
        System.out.println("6. Eliminar pelicula.");
        System.out.println("7. Mostrar películas.");
        System.out.println("8. Salir");

        System.out.println("Ingrese una opcion: ");

        int opcion = read.nextInt();
        return opcion;
    }

    public void procesarDatos(int opcion, Admin admin, Cine cine) {
        boolean salir = false;
        switch (opcion) {
            case 1:
                System.out.println("---------Registrar Cliente-------");
                System.out.println("Ingresa nombre de cliente: ");
                read.nextLine();
                String nombreCliente = read.nextLine();
                System.out.println("Ingresa apellido del cliente: ");
                String apellidoCliente = read.nextLine();
                System.out.println("Ingresa CURP del cliente: ");
                String curpCliente = read.nextLine();
                System.out.println("Ingresa dirección del cliente: ");
                String direccionCliente = read.nextLine();
                System.out.println("Ingresa año de nacimiento del cliente: ");
                int añoNaciCliente = read.nextInt();
                System.out.println("Ingresa mes de nacimiento del cliente:");
                int mesNaciCliente = read.nextInt();
                System.out.println("Ingresa dia de nacimiento del cliente:");
                int diaNaciCliente = read.nextInt();
                LocalDate fechaNacimiento = LocalDate.of(añoNaciCliente, mesNaciCliente, diaNaciCliente);
                String id = cine.generarIDcliente(nombreCliente, apellidoCliente);
                System.out.println("Ingrese la contraseña del cliente: ");
                String contraseñaCliente = read.nextLine();
                Cliente cliente = new Cliente(id, nombreCliente, apellidoCliente, curpCliente, direccionCliente, fechaNacimiento, contraseñaCliente);
                cine.registrarCliente(cliente);
                break;
            case 2:
                System.out.println("---------Registrar sala-------");
                System.out.print("Ingresa nombre de la sala: ");
                read.nextLine();

                String nombresala= read.nextLine();
                String ID=cine.genereadorIDsala(nombresala);
                System.out.print("Ingresa cantidad de asientos VIP: ");
                int cantidadVIP = read.nextInt();
                System.out.print("Ingresa cantidad de asientos premium: ");
                int cantidadPremium = read.nextInt();
                int filasAsientos=0;
                do {
                    System.out.println("Ingresa filas de asientos");
                    filasAsientos = read.nextInt();
                    if (filasAsientos>10){
                        System.out.println("Las filas no pueden ser mayor que 10");
                    }
                }while(filasAsientos>10);
                System.out.println("Ingresa columnas de asientos");
                int columnasAsientos=0;
                do {
                    columnasAsientos = read.nextInt();
                    if (columnasAsientos>12){
                        System.out.println("Las filas no pueden ser mayor que 12");
                    }
                }while(columnasAsientos>12);

               cine.asignarAsientoCalidad(filasAsientos,columnasAsientos, cantidadVIP, cantidadPremium);

               Pelicula peliculavalid=null;
               while(peliculavalid == null) {

                   System.out.print("Ingresar ID de la película que se va a proyectar: ");
                   read.nextLine();
                   String idPelicula = read.nextLine();
                   peliculavalid=cine.obtenerPeliculaPorID(idPelicula);
                   if(peliculavalid == null){
                       System.out.println("Esa película no existe");
                        }
                   }

                Sala sala = new Sala(ID, nombresala, filasAsientos, columnasAsientos, cantidadPremium, cantidadVIP, peliculavalid);
                cine.registrarSala(sala);
               break;

            case 3:
                System.out.println("\n----- AGREGAR PELÍCULA -----\n");
                System.out.print("Ingresa el nombre de la película: ");
                read.nextLine();
                String nombre = read.nextLine();
                System.out.println("Ingresa la categoría de la película: ");
                Categoria categoria=Categoria.A;
                boolean band=false;
                System.out.println("1. A");
                System.out.println("2. B");
                System.out.println("3. C");
                System.out.println("4. D");
                int num = read.nextInt();

                while(!band){

                    switch (num) {
                        case 1:
                            categoria = Categoria.A;
                            band = true;
                            break;
                        case 2:
                            categoria = Categoria.B;
                            band = true;
                            break;
                        case 3:
                            categoria = Categoria.C;
                            band = true;
                            break;
                        case 4:
                            categoria = Categoria.D;
                            band = true;
                            break;
                        default:
                            System.out.println("No existe esa opción");
                    }
                }
                System.out.print("Agregar sinópsis: ");
                String Sinopsis = read.nextLine();
                read.nextLine();
                System.out.print("Ingresa la duración de horas de la película: ");
                int horas = read.nextInt();
                System.out.print("Ingresa la duración de minutos de la película: ");
                int minutos = read.nextInt();
                LocalTime Duracion = LocalTime.of(horas, minutos);
                String idPelicula= cine.generarIDpelicula(nombre);

                LocalDate FechaFuncion= LocalDate.of(2005,06, 01);

                while(!cine.validadFechaFuncion(FechaFuncion)) {
                    System.out.print("Ingresar el día de la función: ");
                    int dia = read.nextInt();
                    System.out.print("Ingresar mes de la función: ");
                    int mes = read.nextInt();
                    System.out.print("Ingresar año de la función: ");
                    int año = read.nextInt();

                    FechaFuncion = LocalDate.of(año, mes, dia);

                    if(!cine.validadFechaFuncion(FechaFuncion)){
                        System.out.println("La función no puede estar registrada en el pasado.");
                    }
                }

                Pelicula pelicula= new Pelicula(idPelicula, nombre, Duracion, FechaFuncion, Sinopsis, categoria);
                cine.agregarPelicula(pelicula);
                break;

            case 4:
                System.out.println("\n-------Modificar pelicula------");
                System.out.print("Ingresa el ID de la pelicula que deseas modificar: ");
                String idModificar= read.nextLine();
                cine.actualizarPelicula(idModificar);
                break;
            case 5:
                System.out.println("'\n--------Mostrar Salas-------");
                cine.mostrarSalas();
                break;

            case 6:
                System.out.print("Ingresa el ID de la pelicula que desea eliminar: ");
                String idEliminar= read.nextLine();
                cine.eliminarPelicula(idEliminar);
                break;
            case 7:
                cine.mostrarPeliculas();
                break;
            case 8:
                salir = true;
                System.out.println("Gracias por usar el Sistema Integral de Gestión para Cinépolis");
                break;
            default:
                System.out.println("Opción no válida. Por favor, intentelo de nuevo.");
        }


    }
}

