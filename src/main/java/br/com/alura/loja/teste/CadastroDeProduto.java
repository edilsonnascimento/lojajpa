package br.com.alura.loja.teste;

import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class CadastroDeProduto {

    public static void main(String[] args) {
        Produto celular = new Produto();
        celular.setNome("Xiomi Redmi");
        celular.setDescricao("Muto legal");
        celular.setPreco(new BigDecimal("800"));

        EntityManager em = JPAUtil.getFactory();
        ProdutoDao produtoDao = new ProdutoDao(em);

        em.getTransaction().begin();
        em.persist(celular);
        em.getTransaction().commit();

        em.close();

    }
}
