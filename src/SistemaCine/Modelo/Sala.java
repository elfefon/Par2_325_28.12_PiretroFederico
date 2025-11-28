package SistemaCine.Modelo;

import java.io.Serializable;

public class Sala implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private double numero;
    private String pelicula;
    private Butaca[][] butacas;

    public Sala(double numero, String pelicula, Butaca[][] butacas) {
        this.numero = numero;
        this.pelicula = pelicula;
        this.butacas = butacas;
    }

    public double getNumero() {
        return numero;
    }

    public void setNumero(double numero) {
        this.numero = numero;
    }

    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    public Butaca[][] getButacas() {
        return butacas;
    }

    public void setButacas(Butaca[][] butacas) {
        this.butacas = butacas;
    }

    @Override
    public String toString() {
        return "Sala{" + "numero=" + numero + ", pelicula=" + pelicula + ", butacas=" + butacas + '}';
    }
    
}
