package cine;
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
    }
    public void registrarSala(Sala sala){
        this.listaSalas.add(sala);
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
        return idC;
    }

    public Cliente obtenerClientePorCurp(String Curp){
        return listaClientes.stream().filter(p -> p.getCURP().equals(Curp)).findFirst().orElse(null);
    }
    public Pelicula obtenerPeliculaPorID(String idPelicula){
        return listaPeliculas.stream().filter(p -> p.getID().equals(idPelicula)).findFirst().orElse(null);

    }

    public int mesNacimiento(String CURP){
        char primermes=CURP.charAt(6);
        char segundmes=CURP.charAt(7);
        String mes=""+primermes+segundmes;
        int mesnacimiento=Integer.parseInt(mes);
        return mesnacimiento;
    }
    public void asignarAsientoCalidad(int filasAsientos, int columnasAsientos, int cantidadAsientosVIP, int cantidadAsientosPremium){
        Asiento salafuncion[][]= new Asiento [filasAsientos][columnasAsientos];


            if(cantidadAsientosVIP==0) {
                return;
            }
            else {
                int cantidad = 0;
                System.out.println("Asigna VIP al asiento");
                do {
                    System.out.printf("Cual fila de asiento quieres asignar el VIP?");
                    int filaVip = read.nextInt();
                    System.out.println("Cual columna de asiento quieres asignar el VIP?");
                    int columnaVip = read.nextInt();
                    salafuncion[filaVip][columnaVip] = Asiento.VIP;
                    cantidad++;
                } while (cantidad < cantidadAsientosVIP);
            }
            if(cantidadAsientosPremium==0) {
                    return;
            }
            else {
                int cantidad = 0;
                System.out.println("Asigna Premium al asiento");
                do {
                    System.out.printf("¿Cuál fila de asiento quieres asignar el Premium?");
                    int filaPremium = read.nextInt();
                    System.out.println("¿Cuál columna de asiento quieres asignar el Premium?");
                    int columnaPremium = read.nextInt();
                    salafuncion[filaPremium][columnaPremium] = Asiento.PREMIUM;
                    cantidad++;
                } while (cantidad < cantidadAsientosPremium);
            }

        for (int i=0 ; i<salafuncion.length; i++){
            for(int j=0 ; j<salafuncion[0].length ; j++){

                if(salafuncion[i][j]!=Asiento.PREMIUM && salafuncion[i][j]!=Asiento.VIP){
                    salafuncion[i][j]=Asiento.NORMAL;
                }
            }
        }
    }

    public void descuento(){
        System.out.println("Ingresa tu CURP: ");
        String CurpCliente = read.nextLine();
        this.obtenerClientePorCurp(CurpCliente);
        System.out.println("Elige un asiento: ");
        System.out.println("1. Normal");
        System.out.println("2. Premium");
        System.out.println("3. VIP");
        System.out.println("4. Salir");
        int opcion2= read.nextInt();
        switch (opcion2){
            case 1:
                System.out.println("Elegiste un asiento normal");
                System.out.println("El precio del asiento es de $100");
                if (LocalDate.now().getMonthValue()==this.mesNacimiento(CurpCliente)){
                    double precio=100*0.30;
                    System.out.printf("Por ser tu mes de cumpleaños, el precio del boleto será "+precio);
                }
                break;
            case 2:
                System.out.println("Elegiste un asiento premium");
                System.out.println("El precio del asiento es de $200");
                if (LocalDate.now().getMonthValue()==this.mesNacimiento(CurpCliente)){
                    double precio=200*0.40;
                    System.out.printf("Por ser tu mes de cumpleaños, el precio del boleto será "+precio);
                }
                break;
            case 3:
                System.out.println("Elegiste un asiento VIP");
                System.out.println("El precio del asiento es de $400");
                if (LocalDate.now().getMonthValue()==this.mesNacimiento(CurpCliente)){
                    double precio=400*0.65;
                    System.out.printf("Por ser tu mes de cumpleaños, el precio del boleto será "+precio);
                }
                break;
        }
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
}
