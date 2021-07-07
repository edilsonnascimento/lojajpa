package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.Pedido;

import javax.persistence.EntityManager;

public class ClienteDao {

    private EntityManager em;

    public ClienteDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Cliente cliente){
        em.persist(cliente);
    }

    public void alterar(Cliente cliente){
        em.merge(cliente);
    }

    public void excluir(Cliente cliente){
        cliente = em.merge(cliente);
        em.remove(cliente);
    }

    public Cliente buscar(long id) {
            return em.find(Cliente.class, id);
    }
}
