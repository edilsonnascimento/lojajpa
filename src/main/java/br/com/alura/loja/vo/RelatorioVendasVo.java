package br.com.alura.loja.vo;

import java.time.LocalDate;

public class RelatorioVendasVo {

    private String nomeProduto;
    private Long quantidadeProdutos;
    private LocalDate ultimaVendaProduto;

    public RelatorioVendasVo(String nomeProduto, Long quantidadeProdutos, LocalDate ultimaVendaProduto) {
        this.nomeProduto = nomeProduto;
        this.quantidadeProdutos = quantidadeProdutos;
        this.ultimaVendaProduto = ultimaVendaProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public Long getQuantidadeProdutos() {
        return quantidadeProdutos;
    }

    public LocalDate getUltimaVendaProduto() {
        return ultimaVendaProduto;
    }

    @Override
    public String toString() {
        return "RelatorioVendasVo{" +
                "nomeProduto='" + nomeProduto + '\'' +
                ", quantidadeProdutos=" + quantidadeProdutos +
                ", ultimaVendaProduto=" + ultimaVendaProduto +
                '}';
    }
}


