package com.bcopstein.negocio.repositorios;

import java.util.List;

import com.bcopstein.negocio.entidades.ItemEstoque;

public interface ItemEstoqueRepository {
    List<ItemEstoque> todos();
    void cadastraItemEstoque(ItemEstoque itemEstoque);
}
