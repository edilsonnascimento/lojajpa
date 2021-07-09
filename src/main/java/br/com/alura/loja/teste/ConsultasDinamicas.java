package br.com.alura.loja.teste;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ConsultasDinamicas {

    public static void main(String[] args) {

        cadastrarProduto();
        EntityManager em = JPAUtil.getFactory();
        ProdutoDao produtoDao = new ProdutoDao(em);

        //List<Produto> produtosSemCriteria = produtoDao.bucarFiltrandoPorSemCriteria("Zenfone", BigDecimal.valueOf(500.00), LocalDateTime.now());

        List<Produto> produtosComCriteria = produtoDao.bucarFiltrandoPorComCriteria("Zenfone", BigDecimal.valueOf(500.00), null);

    }

    public static void cadastrarProduto(){
        Categoria celulares = new Categoria("Celulares");
        Categoria cozinhas = new Categoria("Cozinha");

        Produto celular1 = new Produto("Xiomi Redmi", "Muto legal", new BigDecimal("800"), celulares);
        Produto celular2 = new Produto("Zenfone 4", "Muto legal", new BigDecimal("900"), celulares);
        Produto xicara = new Produto("Xicara magina", "Senhor do Aneis", new BigDecimal("45"), cozinhas);
        Produto panela = new Produto("Tramoltina", "Cacharola", new BigDecimal("120"), cozinhas);

        EntityManager em = JPAUtil.getFactory();
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ProdutoDao produtoDao = new ProdutoDao(em);

        em.getTransaction().begin();
        categoriaDao.cadastrar(celulares);
        categoriaDao.cadastrar(cozinhas);
        produtoDao.cadastrar(xicara);
        produtoDao.cadastrar(panela);
        produtoDao.cadastrar(celular1);
        produtoDao.cadastrar(celular2);

        em.getTransaction().commit();
        em.close();

    }
}
