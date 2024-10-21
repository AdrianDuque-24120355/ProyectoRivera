package compraBoletos;
import pelicula.Pelicula;
import usuarios.cliente.Cliente;
import sala.Sala;
import sala.utils.Asiento;


public class ComprasBoletos {

    String ID;
    Pelicula pelicula;
    Cliente cliente;
    Sala sala, asiento;
    int precio;
    Asiento tipoAsiento;
    boolean descuento;

    public ComprasBoletos(String ID, boolean descuento, Asiento tipoAsiento, int precio, Sala asiento, Sala sala, Cliente cliente, Pelicula pelicula) {
        this.ID = ID;
        this.descuento = descuento;
        this.tipoAsiento = tipoAsiento;
        this.precio = precio;
        this.asiento = asiento;
        this.sala = sala;
        this.cliente = cliente;
        this.pelicula = pelicula;
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

    public int getPrecio() {
        return precio;
    }

    public Sala getAsiento() {
        return asiento;
    }

    public Sala getSala() {
        return sala;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }
}
