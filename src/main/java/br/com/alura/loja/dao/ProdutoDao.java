package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Produto;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public List<Produto> bucarFiltrandoPorSemCriteria(String nome, BigDecimal preco, LocalDateTime dataCadastro) {

        String jpql = "SELECT p FROM Produto p WHERE 1=1";
        if(nome != null && !nome.trim().isEmpty()) jpql += " AND nome = :nome";
        if(preco != null) jpql += " AND preco = :preco";
        if(dataCadastro != null) jpql += " AND dataCadastro = :dataCadastro";

        TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
        if(nome != null && !nome.trim().isEmpty()) query.setParameter("nome", nome);
        if(preco != null) query.setParameter("preco", preco);
        if(dataCadastro != null) query.setParameter("dataCadastro", dataCadastro);

        return query.getResultList();
    }

    public List<Produto> bucarFiltrandoPorComCriteria(String nome, BigDecimal preco, LocalDateTime dataCadastro) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
        Root<Produto> from = query.from(Produto.class);

        Predicate and = builder.and();
        if(nome != null && !nome.trim().isEmpty()) and = builder.and(and, builder.equal(from.get("nome"), nome));
        if(preco != null) and = builder.and(and, builder.equal(from.get("preco"), preco));
        if(dataCadastro != null) and = builder.and(and, builder.equal(from.get("dataCadastro"), dataCadastro));

        query.where(and);

        return em.createQuery(query).getResultList();
    }


}
