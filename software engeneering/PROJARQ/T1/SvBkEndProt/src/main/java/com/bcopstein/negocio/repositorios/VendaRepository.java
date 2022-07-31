package com.bcopstein.negocio.repositorios;

import java.util.List;

import com.bcopstein.negocio.entidades.Venda;

public interface VendaRepository {
    List<Venda> todos();
    void salvar(Venda venda);
    Venda recupera(long numero);
}
