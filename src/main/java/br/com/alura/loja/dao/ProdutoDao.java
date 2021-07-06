package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Produto;

import javax.persistence.EntityManager;
import java.util.List;

public class ProdutoDao {

    private EntityManager em;

    public ProdutoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Produto produto){
        em.persist(produto);
    }

    public void alterar(Produto produto){
        em.merge(produto);
    }

    public void excliur(Produto produto){
        produto = em.merge(produto);
        em.remove(produto);
    }

    public Produto buscar(long id) {
        return em.find(Produto.class, id);
    }

    public List<Produto> todos() {
        String jpql = "SELECT p FROM Produto p";
        return em.createQuery(jpql, Produto.class).getResultList();
    }
}
