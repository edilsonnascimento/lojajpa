package br.com.alura.loja.teste;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.*;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class PerformaceConsultas {

    public static void main(String[] args) {

        popularBancoDados();
        EntityManager em = JPAUtil.getFactory();

        Pedido pedido = em.find(Pedido.class, 1l);
        System.out.println("Quantidade de itens no pedidos: " + pedido.getItens().size());
        PedidoDao pedidoDao = new PedidoDao(em);
        Pedido pedido2 = pedidoDao.buscarCliente(1l);
        em.close();

        System.out.println(pedido2.getCliente().getNome());

    }

    public static void popularBancoDados(){
        Categoria celulares = new Categoria("Celulares");
        Categoria computares = new Categoria("Computadores");
        Categoria cozihas = new Categoria("Cozinha");

        Produto celular = new Produto("Xiomi Redmi", "Muto legal", new BigDecimal("800"), celulares);
        Produto pc = new Produto("Server Dell XPMZ", "Muto legal", new BigDecimal("3800"), computares);
        Produto notebook = new Produto("Notebook Dell XPMZ", "Muto legal", new BigDecimal("5000"), computares);
        Produto xicara = new Produto("Caneca magica", "Deves cançados", new BigDecimal("55"),cozihas);

        Cliente cliente = new Cliente("Edilson","111111");
        Cliente cliente0 = new Cliente("Tomazio","111111");

        EntityManager em = JPAUtil.getFactory();
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ProdutoDao produtoDao = new ProdutoDao(em);
        ClienteDao clienteDao = new ClienteDao(em);
        PedidoDao pedidoDao = new PedidoDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        categoriaDao.cadastrar(computares);
        categoriaDao.cadastrar(cozihas);

        produtoDao.cadastrar(celular);
        produtoDao.cadastrar(pc);
        produtoDao.cadastrar(notebook);
        produtoDao.cadastrar(xicara);
        clienteDao.cadastrar(cliente);
        clienteDao.cadastrar(cliente0);

        Produto produto1 = produtoDao.buscar(1l);
        Produto produto2 = produtoDao.buscar(2l);
        Produto produto3 = produtoDao.buscar(3l);
        Produto produto4 = produtoDao.buscar(4l);
        Cliente cliente1 = clienteDao.buscar(1l);
        Cliente cliente2 = clienteDao.buscar(2l);

        Pedido pedido1 = new Pedido(cliente1);
        ItemPedido itemPedido1 = new ItemPedido(10, produto1, pedido1);
        pedido1.adiciona(itemPedido1);
        ItemPedido itemPedido2 = new ItemPedido(4, produto2, pedido1);
        pedido1.adiciona(itemPedido2);
        pedidoDao.cadastrar(pedido1);

        Pedido pedido2 = new Pedido(cliente2);
        ItemPedido itemPedido3 = new ItemPedido(6, produto2, pedido2);
        pedido2.adiciona(itemPedido3);
        ItemPedido itemPedido4 = new ItemPedido(5, produto3, pedido2);
        pedido2.adiciona(itemPedido4);
        pedidoDao.cadastrar(pedido2);

        Pedido pedido3 = new Pedido(cliente1);
        ItemPedido itemPedido5 = new ItemPedido(6, produto1, pedido3);
        pedido3.adiciona(itemPedido5);
        ItemPedido itemPedido6 = new ItemPedido(5, produto2, pedido3);
        pedido3.adiciona(itemPedido6);
        ItemPedido itemPedido7 = new ItemPedido(5, produto3, pedido3);
        pedido3.adiciona(itemPedido7);
        ItemPedido itemPedido8 = new ItemPedido(5, produto4, pedido3);
        pedido3.adiciona(itemPedido8);
        pedidoDao.cadastrar(pedido3);

        em.getTransaction().commit();

        em.close();

    }
}
