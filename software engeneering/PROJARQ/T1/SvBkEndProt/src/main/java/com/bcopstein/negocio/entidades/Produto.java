package com.bcopstein.negocio.entidades;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "produto")
public class Produto {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private long id;
  private String nome;
  private double preco;

  protected Produto() {
  }

  public Produto(String nome, double preco) {
    this.nome = nome;
    this.preco = preco;
  }

  public long getId() {
      return id;
  }

  public String getnome() {
    return nome;
  }

  public double getPreco() {
    return preco;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setPreco(double preco) {
    this.preco = preco;
  }
}
