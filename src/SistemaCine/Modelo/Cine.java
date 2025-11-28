package SistemaCine.Modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Cine implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String nombre;
    private ArrayList<Cliente> listaDeClientes;
    private ArrayList<Sala> listaDeSalas;
    private ArrayList<Entrada> listaDeEntradas;

   public Cine(String nombre, ArrayList<Cliente> listaDeClientes, ArrayList<Sala> listaDeSalas, ArrayList<Entrada> listaDeEntradas) {
        this.nombre = nombre;
        
        if (listaDeClientes == null) {
            this.listaDeClientes = new ArrayList<>();
        } else {
            this.listaDeClientes = listaDeClientes;
        }

        if (listaDeSalas == null) {
            this.listaDeSalas = new ArrayList<>();
        } else {
            this.listaDeSalas = listaDeSalas;
        }

        if (listaDeEntradas == null) {
            this.listaDeEntradas = new ArrayList<>();
        } else {
            this.listaDeEntradas = listaDeEntradas;
        }
    }

    public Cine() {
        this.nombre = "Cine UTN"; 
        this.listaDeClientes = new ArrayList<>();
        this.listaDeSalas = new ArrayList<>();
        this.listaDeEntradas = new ArrayList<>();
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Cliente> getListaDeClientes() {
        return listaDeClientes;
    }

    public void setListaDeClientes(ArrayList<Cliente> listaDeClientes) {
        this.listaDeClientes = listaDeClientes;
    }

    public ArrayList<Sala> getListaDeSalas() {
        return listaDeSalas;
    }

    public void setListaDeSalas(ArrayList<Sala> listaDeSalas) {
        this.listaDeSalas = listaDeSalas;
    }

    public ArrayList<Entrada> getListaDeEntradas() {
        return listaDeEntradas;
    }

    public void setListaDeEntradas(ArrayList<Entrada> listaDeEntradas) {
        this.listaDeEntradas = listaDeEntradas;
    }
    
    public void registrarCliente(Cliente nuevoCliente){
        listaDeClientes.add(nuevoCliente);
    }
    
}
