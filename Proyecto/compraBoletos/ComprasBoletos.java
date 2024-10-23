package compraBoletos;
import pelicula.Pelicula;
import usuarios.cliente.Cliente;
import sala.Sala;
import sala.utils.Asiento;


public class ComprasBoletos {

    String ID;
    String nombrePelicula;
    String nombreCliente;
    String asientoSeleccionado;
    String nombreSala;
    double precio;
    Asiento tipoAsiento;
    boolean descuento;

    public ComprasBoletos(String ID, String nombreSala, String asientoSeleccionado, String nombreCliente,
                          String nombrePelicula, double precio, Asiento tipoAsiento, boolean descuento) {
        this.ID = ID;
        this.descuento = descuento;
        this.tipoAsiento = tipoAsiento;
        this.precio = precio;
        this.asientoSeleccionado = asientoSeleccionado;
        this.nombreSala = nombreSala;
        this.nombreCliente = nombreCliente;
        this.nombrePelicula = nombrePelicula;
    }

    public String getID() {
        return ID;
    }

    public boolean isDescuento() {
        return descuento;
    }

    public Asiento getTipoAsiento() {
        return tipoAsiento;
    }

    public double getPrecio() {
        return precio;
    }

    public String getAsientoSeleccionado() {
        return asientoSeleccionado;
    }

    public String getNombrePelicula() {
        return nombrePelicula;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getNombreSala() {
        return nombreSala;
    }

    public String mostrarBoleto(){
        String info = String.format("ID: %s, Sala: %s, Asiento: %s, Cliente: %s, Película: %s, Precio: .2%f, Tipo de Asiento: %s, Descuento %s",
            ID, getNombreSala(), asientoSeleccionado, getNombreCliente(), getNombrePelicula(), precio, tipoAsiento, descuento );
        return info;
    }
}
