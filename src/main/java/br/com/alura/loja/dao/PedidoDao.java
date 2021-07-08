package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.vo.RelatorioVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

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

    public BigDecimal valorTotalVendido(){
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
        return em.createQuery(jpql, BigDecimal.class).getSingleResult();
    }

     public List<RelatorioVendasVo> relatorioVendas(){
        String jpql = "SELECT new br.com.alura.loja.vo.RelatorioVendasVo(" +
                      "produto.nome, " +
                      "       SUM(item.quantidade), " +
                      "       MAX(pedido.data)) " +
                      "FROM Pedido pedido " +
                      "JOIN pedido.itens item " +
                      "JOIN item.produto produto " +
                      "GROUP BY produto.nome " +
                      "ORDER BY SUM(item.quantidade) DESC";
        return em.createQuery(jpql, RelatorioVendasVo.class).getResultList();
     }

     public Pedido buscarCliente(Long idPedido){
        String jpql = "SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :idPedido";
        return em.createQuery(jpql, Pedido.class).setParameter("idPedido", idPedido).getSingleResult();
     }
}
