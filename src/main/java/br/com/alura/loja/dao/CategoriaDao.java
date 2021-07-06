package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;

import javax.persistence.EntityManager;

public class CategoriaDao {

    private EntityManager em;

    public CategoriaDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Categoria categoria){
        em.persist(categoria);
    }

    public void alterar(Categoria categoria){
        em.merge(categoria);
    }

    public void excluir(Categoria categoria){
        categoria = em.merge(categoria);
        em.remove(categoria);
    }
}
