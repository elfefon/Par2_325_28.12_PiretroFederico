package SistemaCine.Modelo;

public class Pelicula {
    private String titulo;
    private String urlImagen;

    public Pelicula(String titulo, String urlImagen) {
        this.titulo = titulo;
        this.urlImagen = urlImagen;
    }

    public String getTitulo() { return titulo; }
    public String getUrlImagen() { return urlImagen; }
}