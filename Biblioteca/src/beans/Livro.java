/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

/**
 *
 * @author laboratorio
 */
public class Livro {
    int id;
    String titulo;
    String genero;
    String anoPub;
    Autor id_autor;
    String numCop;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    

    public Autor getId_autor() {
        return id_autor;
    }

    public void setId_autor(Autor id_autor) {
        this.id_autor = id_autor;
    }

    public String getAnoPub() {
        return anoPub;
    }

    public void setAnoPub(String anoPub) {
        this.anoPub = anoPub;
    }

    public String getNumCop() {
        return numCop;
    }

    public void setNumCop(String numCop) {
        this.numCop = numCop;
    }

    

    
    
    
}
