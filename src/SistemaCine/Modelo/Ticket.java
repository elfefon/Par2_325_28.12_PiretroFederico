package SistemaCine.Modelo;

public class Ticket {
    private String pelicula;
    private String ubicacion; 

    public Ticket(String pelicula, int fila, int numero) {
        this.pelicula = pelicula;
        this.ubicacion = "Fila " + fila + " - Asiento " + numero;
    }

    public String getPelicula() { return pelicula; }
    public String getUbicacion() { return ubicacion; }
}