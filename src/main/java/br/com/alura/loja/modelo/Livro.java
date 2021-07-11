package br.com.alura.loja.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "livros")
public class Livro extends Produto{

    private String autor;
    @Column(name = "numero_paginas")
    private Integer numeroPaginas;

    public Livro() {
    }

    public Livro(String autor, Integer numeroPaginas) {
        this.autor = autor;
        this.numeroPaginas = numeroPaginas;
    }

    public String getAutor() {
        return autor;
    }

    public Integer getNumeroPaginas() {
        return numeroPaginas;
    }
}
