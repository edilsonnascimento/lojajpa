package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
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

    public List<Produto> buscar(String nome){
        String jpql = "SELECT p FROM Produto p WHERE p.nome  = :nome";
        return em.createQuery(jpql, Produto.class).setParameter("nome", nome).getResultList();
    }

    public List<Produto> buscarNomeCategoria(String nome){
        return em.createNamedQuery("Produto.buscarNomeCategoria", Produto.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public BigDecimal buscarPrecoPorProduto(String nome){
        String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
        return em.createQuery(jpql, BigDecimal.class)
                .setParameter("nome", nome)
                .getSingleResult();
    }

}
