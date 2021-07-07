package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;

import javax.persistence.EntityManager;

public class PedidoDao {

    private EntityManager em;

    public PedidoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Pedido pedido){
        em.persist(pedido);
    }

    public void alterar(Pedido pedido){
        em.merge(pedido);
    }

    public void excluir(Pedido pedido){
        pedido = em.merge(pedido);
        em.remove(pedido);
    }

    public Pedido buscar(long id) {
        return em.find(Pedido.class, id);
    }
}
