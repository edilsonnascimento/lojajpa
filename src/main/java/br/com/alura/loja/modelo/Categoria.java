package br.com.alura.loja.modelo;

import javax.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {


    @EmbeddedId
    private CategoriaId id;

    public Categoria(String nome) {
        id = new CategoriaId(nome, "Default");
    }

    public Categoria() {
    }

    public CategoriaId getId() {
        return id;
    }

    public String getNome() {
        return id.getNome();
    }

    public void setId(CategoriaId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                '}';
    }
}