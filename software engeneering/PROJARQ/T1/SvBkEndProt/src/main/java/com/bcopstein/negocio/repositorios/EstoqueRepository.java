package com.bcopstein.negocio.repositorios;

import java.util.List;

import com.bcopstein.negocio.entidades.Estoque;

public interface EstoqueRepository {
    List<Estoque> todos();
    void cadastraEstoque(Estoque estoque);
}
