package br.com.alura.loja.teste;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {

    public static void main(String[] args) {

        cadastrarProduto();
        EntityManager em = JPAUtil.getFactory();
        ProdutoDao produtoDao = new ProdutoDao(em);
        Produto produto = produtoDao.buscar(1l);
        System.out.println(produto.getPreco());
        List<Produto> produtos = produtoDao.todos();
        produtos.forEach(p -> System.out.println(p.getNome()));
    }

    public static void cadastrarProduto(){
        Categoria celulares = new Categoria("Celulares");

        Produto celular = new Produto("Xiomi Redmi", "Muto legal", new BigDecimal("800"), celulares);

        EntityManager em = JPAUtil.getFactory();
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ProdutoDao produtoDao = new ProdutoDao(em);

        em.getTransaction().begin();
        categoriaDao.cadastrar(celulares);
        em.persist(celular);
        em.getTransaction().commit();
        em.close();

    }
}
