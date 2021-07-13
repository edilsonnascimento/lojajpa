package br.com.alura.loja.teste;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.CategoriaId;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;

public class ChaveCompostaTeste {
    public static void main(String[] args) {

        EntityManager em = JPAUtil.getFactory();

        CategoriaDao categoriaDao = new CategoriaDao(em);
        Categoria cozihas = new Categoria("Cozinha");

        em.getTransaction().begin();

        categoriaDao.cadastrar(cozihas);

        em.getTransaction().commit();

        System.out.println(em.find(Categoria.class, new CategoriaId("Cozinha", "Default")));

    }
}
