package br.com.alura.loja.modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "produtos")
@NamedQuery(name = "Produto.buscarNomeCategoria",
            query = "SELECT p FROM Produto p WHERE p.categoria.id.nome  = :nome AND p.categoria.id.tipo = :tipo")
@Inheritance(strategy = InheritanceType.JOINED)
public class Produto{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private LocalDateTime dataCadastro = LocalDateTime.now();
    @ManyToOne(fetch = FetchType.LAZY)
    private Categoria categoria;

    public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }

    public Produto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
