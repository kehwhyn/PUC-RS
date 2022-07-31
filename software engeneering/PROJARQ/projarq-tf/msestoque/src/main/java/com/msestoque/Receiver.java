package com.msestoque;

import com.google.gson.Gson;
import com.msestoque.negocio.entidades.ItemEstoque;
import com.msestoque.negocio.servicos.ServicoEstoque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
  @Autowired
  private ServicoEstoque servicoEstoque;

  public void receiveMessage(String jsonObect) {
    ItemEstoque itemEstoque = new Gson().fromJson(jsonObect, ItemEstoque.class);
    servicoEstoque.atualizaProduto(itemEstoque);
  }
}
