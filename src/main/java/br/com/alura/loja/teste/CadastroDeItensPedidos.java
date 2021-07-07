package br.com.alura.loja.teste;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.*;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CadastroDeItensPedidos {

    public static void main(String[] args) {

        popularBancoDados();
        EntityManager em = JPAUtil.getFactory();
        ProdutoDao produtoDao = new ProdutoDao(em);
        ClienteDao clienteDao = new ClienteDao(em);
        PedidoDao pedidoDao = new PedidoDao(em);

        em.getTransaction().begin();

        Produto produto = produtoDao.buscar(1l);
        Cliente cliente = clienteDao.buscar(1l);
        Pedido pedido = new Pedido(cliente);
        ItemPedido itemPedido = new ItemPedido(10, produto, pedido);
        pedido.adiciona(itemPedido);
        pedidoDao.cadastrar(pedido);

        em.getTransaction().commit();

        em.close();

    }

    public static void popularBancoDados(){
        Categoria celulares = new Categoria("Celulares");
        Produto celular = new Produto("Xiomi Redmi", "Muto legal", new BigDecimal("800"), celulares);
        Cliente cliente = new Cliente("Edilson","111111");

        EntityManager em = JPAUtil.getFactory();
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ProdutoDao produtoDao = new ProdutoDao(em);
        ClienteDao clienteDao = new ClienteDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);
        clienteDao.cadastrar(cliente);

        em.getTransaction().commit();

        em.close();

    }
}
