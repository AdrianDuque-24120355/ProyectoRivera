package menu;

import cine.Cine;
import pelicula.Pelicula;
import pelicula.utils.Categoria;
import usuarios.Usuario;
import usuarios.admin.Admin;
import usuarios.cliente.Cliente;
import usuarios.utils.Rol;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Menu {
    Scanner read = new Scanner(System.in);
    private Cine cine=new Cine();

    public void login() {

        int intentosMaximos=5, intentosUsuario=0;

        System.out.println("\nBienvenido.\n");

        while(intentosUsuario<intentosMaximos){

            System.out.print("Ingresa tu ID: ");
            String usuario=read.next();
            System.out.print("Ingresa tu contraseña: ");
            String contraseña=read.next();


            Usuario usuarioEnSesion = cine.validarInicioSesion(usuario, contraseña);

            if (usuarioEnSesion instanceof Usuario){

                if (usuarioEnSesion.getRol() == Rol.ADMIN){

                    Admin adminEnSesion = (Admin) usuarioEnSesion;
                    Menu_Admin menuAdmin = new Menu_Admin();

                    int opc=0;
                    while(opc!=8) {
                        opc = menuAdmin.mostrarMenuAdmin();
                        menuAdmin.procesarDatos(opc, adminEnSesion, cine);
                        intentosUsuario = 0;
                    }
                }
                else{
                    Cliente clienteEnSesion = (Cliente)  usuarioEnSesion;
                    Menu_Cliente menuCliente = new Menu_Cliente();
                    int opc=0;
                    while(opc!=5) {
                        opc = menuCliente.mostrarMenuCliente();
                        menuCliente.procesarDatosMenu(opc, clienteEnSesion, cine);
                        intentosUsuario = 0;
                    }
                }
            }
            else{

                intentosUsuario = mostrarErrorLogin(intentosUsuario);
            }
        }

        System.out.println("\nDemasiados intentos fallidos:(\n");
    }
    private int mostrarErrorLogin(int intentosUsuario){

        System.out.println("\nUsuario o contraseña incorrectos. Intenta de nuevo.");
        return intentosUsuario+1;}
}

