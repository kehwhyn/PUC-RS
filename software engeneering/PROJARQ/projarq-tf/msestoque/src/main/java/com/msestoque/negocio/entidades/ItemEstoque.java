package com.msestoque.negocio.entidades;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "estoque")
public class ItemEstoque {

  @Id
  @Column(name = "cod_estoque")
  private Integer codEstoque;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "cod_produto")
  private Produto produto;
  private int qtdade;

  public ItemEstoque(Integer codEstoque, Produto produto, int qtdade) {
    this.codEstoque = codEstoque;
    this.produto = produto;
    this.qtdade = qtdade;
  }

  public ItemEstoque() {
  }

  public Integer getCodEstoque() {
    return codEstoque;
  }

  public void setCodEstoque(Integer codEstoque) {
    this.codEstoque = codEstoque;
  }

  public Produto getProduto() {
    return produto;
  }

  public void setProduto(Produto produto) {
    this.produto = produto;
  }

  public int getQtdade() {
    return qtdade;
  }

  public void setQtdade(int qtdade) {
    this.qtdade = qtdade;
  }

}
