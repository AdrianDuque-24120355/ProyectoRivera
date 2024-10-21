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
}

