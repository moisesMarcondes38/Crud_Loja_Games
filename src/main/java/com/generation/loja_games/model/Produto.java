package com.generation.loja_games.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name="tb_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="O título do produto é obrigatório!")
    @Size(min = 5, max = 100, message ="O título deve conter entre 5 e 100 caracteres.")
    private String titulo;

    @NotBlank(message = "A descrição do produto é obrigatória!")
    @Size(min = 20, max= 1000, message = "A descrição do produto deve conter entre 20 e 1000 caracteres")
    private String descricao;

    @NotNull(message = "A quantidade do produto é obrigatória")
    private int quantidade;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @NotNull (message = "O valor do produto é obrigatório")
    @Positive(message = "O valor  do produto precisa ser maior que zero")
    private BigDecimal valor;

    @NotBlank (message = "É obrigatório informar o fornecedor do produto")
    private String fornecedor;

    @ManyToOne
    @JsonIgnoreProperties("produto")
    private Categoria categoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
