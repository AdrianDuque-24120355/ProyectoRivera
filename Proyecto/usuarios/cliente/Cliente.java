package usuarios.cliente;

import usuarios.Usuario;
import usuarios.utils.Rol;

import java.time.LocalDate;

public class Cliente extends Usuario {
    public String CURP;
    private String direccion;



    public Cliente(String id, String apellidos, String nombre,
                   String CURP, String direccion, LocalDate fechaNacimiento, String contraseña) {

        super (id, apellidos, nombre, fechaNacimiento, contraseña, Rol.CLIENTE);
        this.CURP = CURP;
        this.direccion = direccion;
    }

    public String getCURP() {
        return CURP;
    }

    public String getDireccion() {
        return direccion;
    }

    public String mostrarCliente(){
        String info = String.format("ID: %s, Nombre: %s, Apellidos: %s, Fecha de Nacimiento: %s, CURP: %s, Dirección: %s",
        getId(), getNombre(), getApellidos(), getFechaNacimiento(), CURP, direccion);
        return info;
    }
}

