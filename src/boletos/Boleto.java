package boletos;

import usuarios.cliente.Cliente;
import pelicula.Pelicula;
import sala.utils.Asiento;
import sala.Sala;

public class Boleto {
    public String id, asiento;
    public Sala sala;
    public Cliente cliente;
    public Pelicula pelicula;
    public int precio;
    public Asiento tipoAsiento;
    public boolean descuento;

    public Boleto(String id, String asiento, Sala sala, Cliente cliente, Asiento tipoAsiento, int precio, Pelicula pelicula, boolean descuento) {
        this.id = id;
        this.asiento = asiento;
        this.sala = sala;
        this.cliente = cliente;
        this.tipoAsiento = tipoAsiento;
        this.precio = precio;
        this.pelicula = pelicula;
        this.descuento = descuento;
    }


    public String getAsiento() {
        return asiento;
    }

    public String getId() {
        return id;
    }

    public Sala getSala() {
        return sala;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Asiento getTipoAsiento() {
        return tipoAsiento;
    }

    public boolean isDescuento() {
        return descuento;
    }

    public int getPrecio() {
        return precio;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }
}
