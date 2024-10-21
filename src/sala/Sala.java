package sala;

import pelicula.Pelicula;

import java.time.LocalDate;

public class Sala {

    public int  filasDeAsientos, columnasDeAsientos;
    public int CantidadDeAsientosPremium;
    public int CantidadDeAsientosVIP;
    public Pelicula pelicula;
    public LocalDate HorariosSala;
    public String ID, nombreSala;


    public Sala(String ID, String nombreSala, int filasDeAsientos, int columnasDeAsientos, int cantidadDeAsientosPremium, int cantidadDeAsientosVIP,
                Pelicula pelicula) {
        this.ID = ID;
        this.filasDeAsientos = filasDeAsientos;
        this.columnasDeAsientos = columnasDeAsientos;
        this.CantidadDeAsientosPremium = cantidadDeAsientosPremium;
        this.CantidadDeAsientosVIP = cantidadDeAsientosVIP;
        this.pelicula = pelicula;
        this.nombreSala = nombreSala;
    }

    public int getCapacidadDeAsientos() {
        return filasDeAsientos;
    }

    public int getCantidadDeAsientosPremium() {
        return CantidadDeAsientosPremium;
    }

    public int getCantidadDeAsientosVIP() {
        return CantidadDeAsientosVIP;
    }

    public LocalDate getHorariosSala() {
        return HorariosSala;
    }

    public String getID() {
        return ID;
    }

    public int getFilasDeAsientos() {
        return filasDeAsientos;
    }

    public int getColumnasDeAsientos() {
        return columnasDeAsientos;
    }
    public Pelicula getPelicula() {
        return pelicula;
    }
    public String getNombreSala() {
        return nombreSala;
    }

    public String mostrarSala(){

        String info=String.format("ID: %s, Nombre de la sala: %s, Filas de asientos: %d, Columnas de asientos: %d, Asientos premium: %d, Asientos VIP: %d, Pelicula: %s",
                ID, nombreSala, filasDeAsientos, columnasDeAsientos, getCantidadDeAsientosPremium(), getCantidadDeAsientosVIP(), pelicula.getNombre());
        return info;
    }
}
