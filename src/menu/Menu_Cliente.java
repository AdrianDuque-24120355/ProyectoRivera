
package menu;

import cine.Cine;
import java.util.Scanner;
import usuarios.cliente.Cliente;


public class Menu_Cliente {

    Scanner read = new Scanner(System.in);
    Cine cine = new Cine();



    public int mostrarMenuCliente() {
        System.out.println("\nSISTEMA HOSPITAL");
        System.out.println("1. Ver mis reservaciones");
        System.out.println("2. Comprar boletos");
        System.out.println("3. Ver Cartelera");
        System.out.println("4. Reservar asientos");
        System.out.println("5. Registrar fecha de nacimiento");
        System.out.println("5. Salir");

        System.out.println("Ingrese una opcion: ");
        int opcion = read.nextInt();

        return opcion;
    }

    public void procesarDatosMenu(int opcion, Cliente cliente, Cine cine) {
        switch (opcion) {
            case 1:
                System.out.println("\n Tus Reservacione: ");

                break;
            case 2:


                break;

            case 3:
                System.out.println("\n Cartelera");
                cine.mostrarPeliculas();
                break;
            case 4:
                System.out.println("\n Reservar asientos");
                break;
            case 5:
                System.out.println("Ingresa tu CURP");
                String curp=read.next();

            case 6:

        }
    }
}
