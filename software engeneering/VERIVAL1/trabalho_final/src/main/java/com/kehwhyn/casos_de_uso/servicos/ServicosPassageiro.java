package com.kehwhyn.casos_de_uso.servicos;

import java.util.List;
import java.util.Collection;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import com.kehwhyn.entidades.Bairro;
import com.kehwhyn.entidades.Viagem;
import com.kehwhyn.entidades.Roteiro;
import com.kehwhyn.entidades.Passageiro;
import com.kehwhyn.casos_de_uso.politicas.CustoViagem;
import com.kehwhyn.casos_de_uso.politicas.CalculoCustoViagem;
import com.kehwhyn.casos_de_uso.repositorios.RepositorioBairros;
import com.kehwhyn.casos_de_uso.repositorios.RepositorioPassageiros;

public class ServicosPassageiro {
    private CustoViagem custoViagem;
    private RepositorioBairros repBairros;
    private RepositorioPassageiros repPassageiros;

    public ServicosPassageiro(RepositorioBairros repBairros, RepositorioPassageiros repPassageiros, CalculoCustoViagem ccv) {
        this.repBairros = repBairros;
        this.repPassageiros = repPassageiros;
        this.custoViagem = new CustoViagem(ccv);
    }

    public List<String> getListaBairros() {
        return repBairros.recuperaListaBairros()
                .stream()
                .map(Bairro::getNome)
                .collect(Collectors.toList());
    }

    public List<String> getPassageirosCadastrados() {
        return repPassageiros.listaPassageiros()
                .stream()
                .map(Passageiro::getNome)
                .collect(Collectors.toList());
    }

    public Roteiro criaRoteiro(String bairroOrigem, String bairroDestino) {
        Collection<Bairro> todosBairros = repBairros.recuperaListaBairros();
        var bOrigem = repBairros.recuperaPorNome(bairroOrigem);
        var bDestino = repBairros.recuperaPorNome(bairroDestino);
        return new Roteiro(bOrigem, bDestino, todosBairros);
    }

    public Viagem criaViagem(int id, Roteiro roteiro, String cpfPassageiro) {
        var data = LocalDateTime.now();
        var passageiro = repPassageiros.recuperaPorCPF(cpfPassageiro);
        double valorCobrado = custoViagem.custoViagem(roteiro, passageiro);
        return new Viagem(id, data, roteiro, passageiro, valorCobrado);
    }
}