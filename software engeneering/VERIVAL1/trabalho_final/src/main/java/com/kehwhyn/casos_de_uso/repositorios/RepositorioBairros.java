package com.kehwhyn.casos_de_uso.repositorios;

import java.util.List;

import com.kehwhyn.entidades.Bairro;

public interface RepositorioBairros {
    List<Bairro> recuperaListaBairros();
    Bairro recuperaPorNome(String nomeBairro);
}