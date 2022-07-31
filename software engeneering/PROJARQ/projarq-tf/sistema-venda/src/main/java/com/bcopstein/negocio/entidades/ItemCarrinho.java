package com.bcopstein.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ItemCarrinho {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer codigo;

  @Column(name = "cod_produto")
  private Integer codProduto;
  private Integer precoUnitario;
  private int quantidade;

  public ItemCarrinho(Integer codProduto, Integer precoUnitario, int quantidade) {
    this.codProduto = codProduto;
    this.precoUnitario = precoUnitario;
    this.quantidade = quantidade;
  }

  public ItemCarrinho() {

  }

  public int getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }

  public Integer getCodProduto() {
    return codProduto;
  }

  public void setCodProduto(Integer codProduto) {
    this.codProduto = codProduto;
  }

  public Integer getPrecoUnitario() {
    return precoUnitario;
  }

  public void setPrecoUnitario(Integer precoUnitario) {
    this.precoUnitario = precoUnitario;
  }

  @Override
  public String toString() {
    return "ItemCarrinho [codProduto=" + codProduto + ", codigo=" + codigo + ", precoUnitario=" + precoUnitario
        + ", quantidade=" + quantidade + "]";
  }
}
