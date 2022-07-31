package com.kehwhyn.casos_de_uso.repositorios;

import java.util.List;

import com.kehwhyn.entidades.Passageiro;

public interface RepositorioPassageiros {
    List<Passageiro> listaPassageiros();
    Passageiro recuperaPorCPF(String cpf);
    void atualizaPassageiro(Passageiro passageiro);
}