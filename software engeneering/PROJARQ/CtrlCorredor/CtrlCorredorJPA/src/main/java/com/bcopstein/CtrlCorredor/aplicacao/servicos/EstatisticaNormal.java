package com.bcopstein.CtrlCorredor.aplicacao.servicos;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcopstein.CtrlCorredor.aplicacao.dtos.EstatisticasDTO;
import com.bcopstein.CtrlCorredor.negocio.repositorios.IEventoRepository;

public class EstatisticaNormal implements ICalculoEstatistica {
    private IEventoRepository eventoRepository;

    @Autowired
    public EstatisticaNormal(IEventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public EstatisticasDTO calculaEstatisticas(int distancia) {
        var eventos = this.eventoRepository
                .listaEventos()
                .stream()
                .filter(e -> e.getDistancia() == distancia)
                .collect(Collectors.toList());

        var tempos = eventos
            .stream()
            .map(e -> e.getHora().getHoras() * 3600 + e.getHora().getMinutos() * 60 + e.getHora().getSegundos())
            .sorted()
            .collect(Collectors.toList());

        var media = tempos
            .stream()
            .mapToDouble(t -> t)
            .average()
            .orElse(Double.NaN);

        var size = tempos.size(); 
        var mediana = Double.NaN;
        if (size > 0)
            mediana = (size % 2  == 0) 
                ? (tempos.get(size / 2 - 1) + tempos.get(size / 2)) / 2.0
                : tempos.get(size / 2) / 2.0;

        var desvioPadrao = 0.0;
        if (mediana != Double.NaN) {
            var variancia = tempos
                .stream()
                .mapToDouble(t -> t - media)
                .map(nt -> nt * nt)
                .average()
                .getAsDouble();
            desvioPadrao = Math.sqrt(variancia);
        }

        return new EstatisticasDTO(media, mediana, desvioPadrao);
    }
}
