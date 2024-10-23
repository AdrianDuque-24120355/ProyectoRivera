package usuarios.admin;

import usuarios.Usuario;
import usuarios.utils.Rol;

import java.time.LocalDate;

public class Admin extends Usuario {
    public double sueldo;

    public Admin(String id, String nombre, String apellidos, LocalDate fechaNacimiento,
                 double sueldo, String contraseña) {

        super(id, nombre, apellidos, fechaNacimiento, contraseña, Rol.ADMIN);
        this.sueldo = sueldo;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }
}
