package SistemaCine.Modelo;

import java.io.Serializable;

public class Entrada implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Cliente cliente;
    private Sala sala;
    private Butaca butaca;

    public Entrada(Cliente cliente, Sala sala, Butaca butaca) {
        this.cliente = cliente;
        this.sala = sala;
        this.butaca = butaca;
    }
    
    
}
