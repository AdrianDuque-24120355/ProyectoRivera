package cine;

import compraBoletos.ComprasBoletos;
import sala.Sala;
import sala.utils.Asiento;
import usuarios.Usuario;
import usuarios.cliente.Cliente;
import pelicula.Pelicula;

import java.util.Random;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import usuarios.admin.Admin;

public class Cine {

    public ArrayList<Usuario> listaUsuarios=new ArrayList<>();
    public ArrayList<Admin> listaAdmin = new ArrayList<>();
    public ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
    public ArrayList <Cliente> listaClientes = new ArrayList<>();
    public ArrayList <Sala> listaSalas=new ArrayList<>();
    public ArrayList <ComprasBoletos> listaBoletos=new ArrayList<>();
    Random rand = new Random();
    Scanner read = new Scanner(System.in);

    public Cine(){
        Admin admin = new Admin("A", "Admin", "Default", LocalDate.now(),
                15.00, "12");
        this.listaUsuarios.add(admin);
        this.listaAdmin.add(admin);
    }

    public Usuario validarInicioSesion(String idUsuario, String contraseña){
        for(Usuario usuario : this.listaUsuarios){

            if (idUsuario.equals(usuario.getId()) && contraseña.equals(usuario.getContraseña())){
                return usuario;
            }
        }
        return null;
    }

    public void agregarPelicula(Pelicula pelicula){
        listaPeliculas.add(pelicula);
        System.out.println("Se registró una nueva pelíula.");
    }
    public void registrarCliente(Cliente cliente){
        this.listaClientes.add(cliente);
        this.listaUsuarios.add(cliente);
        System.out.println("Se registró un nuevo cliente.");
    }
    public void registrarSala(Sala sala){
        this.listaSalas.add(sala);
        System.out.println("Se registró una nueva sala.");
    }
    public void registrarBoleto(ComprasBoletos boleto){

        this.listaBoletos.add(boleto);
        System.out.println("Se registró su boleto");
    }
    public void actualizarPelicula(String idPelicula){
        for (Pelicula p : listaPeliculas) {
            if (idPelicula == p.getID()) {
                System.out.println("Ingresa el nombre de la pelicula: ");
                String nombrePelicula = read.nextLine();
                p.setNombre(nombrePelicula);
                System.out.println("Ingresa la sinopsis de la pelicula: ");
                read.nextLine();
                String sinopsisPelicula = read.nextLine();
                p.setSinopsis(sinopsisPelicula);
                LocalDate FechaFuncion= LocalDate.of(2005,06, 01);

                while(!this.validadFechaFuncion(FechaFuncion)) {
                    System.out.print("Ingresar el día de la función: ");
                    int dia = read.nextInt();
                    System.out.print("Ingresar mes de la función: ");
                    int mes = read.nextInt();
                    System.out.print("Ingresar año de la función: ");
                    int año = read.nextInt();

                    FechaFuncion = LocalDate.of(año, mes, dia);

                    if(!this.validadFechaFuncion(FechaFuncion)){
                        System.out.println("La función no puede estar registrada en el pasado.");
                    }
                }
                p.setFechaFuncion(FechaFuncion);
                String idNuevoPelicula=this.generarIDpelicula(nombrePelicula);
                p.setID(idNuevoPelicula);
                System.out.println("Pelicula actualizada: " + p);
                return;
            }
        }
        System.out.println("Pelicula no encontrada.");
    }

    public void eliminarPelicula(String idPeliculaEliminar) {
        int longitudOriginal = this.listaPeliculas.size();

        this.listaPeliculas.removeIf((pelicula) -> pelicula.getID() == idPeliculaEliminar);

        if (longitudOriginal != this.listaPeliculas.size()) {
            System.out.println("Se eliminó la película con el ID: " + idPeliculaEliminar);
        } else {
            System.out.println("No existe un película con el ID: " + idPeliculaEliminar);
        }
    }

    public String generarIDpelicula(String nombre){

        char letra1 = nombre.charAt(0);
        char letra2 = nombre.charAt(1);
        int aleatorio = rand.nextInt(0,70000);
        int dia = LocalDate.now().getDayOfMonth();
        String ID=String.format("%c%c%d%d" , letra1, letra2, aleatorio, dia);
        return ID.toUpperCase();
    }
    public String generarIDCompra(){
        int aleatorio = rand.nextInt(1000,7000000);
        int dia = LocalDate.now().getDayOfMonth();
        String IDC=String.format("COM%d%d", aleatorio, dia);
        return IDC.toUpperCase();
    }

    public boolean validadFechaFuncion(LocalDate fechaDeseada) {

        if (fechaDeseada.isBefore(LocalDate.now())) return false;
        return true;
    }

    public void mostrarPeliculas(){

        if (this.listaPeliculas.isEmpty()){
            System.out.println("Aún no hay películas registradas :(");
            return;
        }
        for (Pelicula pelicula : this.listaPeliculas) {
            System.out.println(pelicula.mostrarPelicula());
        }
    }
    public String genereadorIDsala(String nombre){

        int aleatorio = rand.nextInt(10000,70000);
        int dia = LocalDate.now().getDayOfMonth();
        char letra1 = nombre.charAt(0);
        String ID=String.format("S%d%d%c" , aleatorio, dia, letra1);
        return ID.toUpperCase();
    }
    public String generarIDcliente(String nombre, String apellido){
        Random random=new Random();
        int num_al= random.nextInt(0, 100000);
        char primeraLetra=nombre.charAt(0);
        char segundaLetra=nombre.charAt(1);
        char primeraLetraApellido=apellido.charAt(0);
        char segundaLetraApellido=apellido.charAt(1);
        LocalDate fecha=LocalDate.now();
        String idC=String.format("C%c%c%c%c%d", primeraLetra, segundaLetra, primeraLetraApellido, segundaLetraApellido, num_al);
        return idC.toUpperCase();
    }

    public Cliente obtenerClientePorCurp(String Curp){
        return listaClientes.stream().filter(p -> p.getCURP().equals(Curp)).findFirst().orElse(null);
    }
    public Pelicula obtenerPeliculaPorID(String idPelicula){
        return listaPeliculas.stream().filter(p -> p.getID().equals(idPelicula)).findFirst().orElse(null);

    }
    public Pelicula obtenerPeliculaPorNombre(String nombrePelicula){
        return listaPeliculas.stream().filter(p -> p.getNombre().equals(nombrePelicula)).findFirst().orElse(null);
    }

    public Asiento[][] asignarAsientoCalidad(int filasAsientos, int columnasAsientos, int cantidadAsientosVIP, int cantidadAsientosPremium){
        Asiento[][] asientoCalidad= new Asiento [filasAsientos][columnasAsientos];

            if(cantidadAsientosVIP==0) {

            }
            else {
                int cantidad = 0;
                System.out.println("Asigna VIP al asiento");
                do {
                    System.out.println("¿Cual fila de asiento quieres asignar el VIP?");
                    int filaVip = read.nextInt();
                    System.out.println("¿Cual columna de asiento quieres asignar el VIP?");
                    int columnaVip = read.nextInt();
                    asientoCalidad[filaVip][columnaVip] = Asiento.VIP;
                    cantidad++;
                } while (cantidad < cantidadAsientosVIP);
            }
            if(cantidadAsientosPremium==0) {

            }
            else {
                int cantidad = 0;
                System.out.println("Asigna Premium al asiento");
                do {
                    System.out.println("¿Cuál fila de asiento quieres asignar el Premium?");
                    int filaPremium = read.nextInt();
                    System.out.println("¿Cuál columna de asiento quieres asignar el Premium?");
                    int columnaPremium = read.nextInt();
                    asientoCalidad[filaPremium][columnaPremium] = Asiento.PREMIUM;
                    cantidad++;
                } while (cantidad < cantidadAsientosPremium);
            }

        for (int i=0 ; i<asientoCalidad.length; i++){
            for(int j=0 ; j<asientoCalidad[0].length ; j++){

                if(asientoCalidad[i][j]!=Asiento.PREMIUM && asientoCalidad[i][j]!=Asiento.VIP){
                    asientoCalidad[i][j]=Asiento.NORMAL;
                }
            }
        }
        return asientoCalidad;
    }


    public void mostrarSalas(){
         if (this.listaSalas.isEmpty()){
             System.out.println("No hay salas registradas xD");
             return;
         }
         for(Sala sala: this.listaSalas){
             System.out.println(sala.mostrarSala());
         }
    }

    public Sala obtenerSalaporNombre(String salaNombre){
        return listaSalas.stream().filter(s -> s.getNombreSala().equals(salaNombre)).findFirst().orElse(null);
    }

    public void seleccionAsientos(int filaAsiento, int columnaAsiento, Asiento asiento[][]){

        asiento[filaAsiento][columnaAsiento] = Asiento.RESERVADO;

    }
    public void mostrarAsiento(Asiento[][] sala, Asiento asiento){

        if(asiento == Asiento.NORMAL) {

            for (int i = 0; i < sala.length; i++) {
                for (int j = 0; j < sala[0].length; j++) {

                    if (sala[i][j] != Asiento.PREMIUM && sala[i][j] != Asiento.VIP) {
                        System.out.println(sala[i][j]+ " "+i +" "+ j);
                    }
                }
            }
        }
        if(asiento == Asiento.PREMIUM) {

            for (int i = 0; i < sala.length; i++) {
                for (int j = 0; j < sala[0].length; j++) {

                    if (sala[i][j] != Asiento.NORMAL && sala[i][j] != Asiento.VIP) {
                        System.out.println(sala[i][j]);
                    }
                }
            }
        }
        if(asiento == Asiento.VIP) {

            for (int i = 0; i < sala.length; i++) {
                for (int j = 0; j < sala[0].length; j++) {

                    if (sala[i][j] != Asiento.PREMIUM && sala[i][j] != Asiento.NORMAL) {
                        System.out.println(sala[i][j]);
                    }
                }
            }
        }
    }

    public double asientoRegular (int CantidadBoletos, boolean promocion) {
        double precio = 100;
        System.out.println("Elegiste un asiento normal");
        System.out.println("El precio del asiento es de: $" + precio);
        if (promocion == true) {
            double precioBoletoDescuento = precio * 0.30;
            CantidadBoletos--;
            precio = precio * CantidadBoletos;
            precio = precio + precioBoletoDescuento;
            System.out.println("Por ser tu mes de cumpleaños, el precio del boleto será " + precio);
            return precio;
        } else {
            System.out.println("El precio del asiento es de: $" + precio);
            return precio;
        }
    }
        public double asientoPremium (int CantidadBoletos, boolean promocion){
            double precio = 200;
            System.out.println("Elegiste un asiento premium");
            System.out.println("El precio del asiento es de $200");
            if ( promocion == true) {
                double precioBoletoDescuento = precio * 0.40;
                CantidadBoletos--;
                precio = precio * CantidadBoletos;
                precio = precio + precioBoletoDescuento;
                System.out.println("Por ser tu mes de cumpleaños, el precio del boleto será " + precio);
                return precio;
            } else {
                System.out.println("El precio del boleto es: $" + precio);
                return precio;
            }
        }
        public double asientoVIP(int CantidadBoletos, boolean promocion){
            double precio = 400;
            System.out.println("Elegiste un asiento VIP");
            System.out.println("El precio del asiento es de $400");
            if (promocion == true) {
                double precioBoletoDescuento = precio * 0.65;
                CantidadBoletos--;
                precio = precio * CantidadBoletos;
                precio = precio + precioBoletoDescuento;
                System.out.println("Por ser tu mes de cumpleaños, el precio del boleto será " + precio);
                return precio;
            } else {
                System.out.println("El precio del boleto es: $" + precio);
                return precio;
            }
        }
        public LocalDate obtenerFechaNacimiento(String CURP){

            int año =0;
            int caracter=Integer.parseInt(String.valueOf(CURP.charAt(4)));

            if (caracter  <= 9 && caracter >0 ){

                String fecha = "19"+CURP.charAt(4)+CURP.charAt(5);
                año = Integer.parseInt(fecha);
            }
            else if (caracter == 0 || caracter == 1 || caracter == 2 ){
                String fecha = "20"+CURP.charAt(4)+CURP.charAt(5);
                año = Integer.parseInt(fecha);
            }
            String mes1 = CURP.charAt(6)+""+CURP.charAt(7);
            int mes = Integer.parseInt(mes1);
            String dia1 = CURP.charAt(8)+""+CURP.charAt(9);
            int dia = Integer.parseInt(dia1);

            LocalDate FechaNacimiento= LocalDate.of(año, mes, dia);
            return FechaNacimiento;
        }
        public void mostrarclinente(){
            if (this.listaClientes.isEmpty()){
                System.out.println("Aún no hay clientes registrados :(");
                return;
            }
            for (Cliente cliente : this.listaClientes) {
                System.out.println(cliente.mostrarCliente());
            }
        }

        public void mostrarBoletos(){

            if (this.listaBoletos.isEmpty()){
                System.out.println("Aún no hay clientes registrados :(");
                return;
            }
            for (ComprasBoletos boletos : this.listaBoletos) {
                System.out.println(boletos.mostrarBoleto());
            }

        }

        public String generarOrdenCompra(Cliente cliente){

            String codigo="OC"+rand.nextInt(30000, 700000)+cliente.getNombre();
            return codigo.toUpperCase();
        }
}




