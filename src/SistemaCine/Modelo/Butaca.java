package SistemaCine.Modelo;

import java.io.Serializable;

public class Butaca implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private boolean libre;
    private double numero;
    private double fila;
    
    public Butaca(boolean libre, double numero, double fila) {
        this.libre = libre;
        this.numero = numero;
        this.fila = fila;
    }

    public boolean isLibre() {
        return libre;
    }

    public void setLibre(boolean libre) {
        this.libre = libre;
    }

    public double getNumero() {
        return numero;
    }

    public void setNumero(double numero) {
        this.numero = numero;
    }

    public double getFila() {
        return fila;
    }

    public void setFila(double fila) {
        this.fila = fila;
    }

    @Override
    public String toString() {
        return "Butaca{" + "libre=" + libre + ", numero=" + numero + ", fila=" + fila + '}';
    }
}

