package com.bcopstein.CtrlCorredor.dominio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcopstein.CtrlCorredor.acessoDados.Evento;
import com.bcopstein.CtrlCorredor.acessoDados.EventoRepository;

@Service
public class ServicoEstatistica {
    private EventoRepository eventoRepository;
    
    @Autowired
    public ServicoEstatistica(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public List<Evento> listaEventos() {
        return eventoRepository.listaEventos();
    }
    
    public void deletaEventos() {
        this.eventoRepository.deletaEventos();
    }
    
    public boolean cadastraEvento(Evento evento) {
        return this.eventoRepository.cadastraEvento(evento);
    }

    public EstatisticasDTO estatisticas(final int distancia) {
        var eventos = this.eventoRepository
                .listaEventos()
                .stream()
                .filter(e -> e.getDistancia() == distancia)
                .collect(Collectors.toList());

        var tempos = eventos
            .stream()
            .map(e -> e.getHoras() * 3600 + e.getMinutos() * 60 + e.getSegundos())
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

    public PerformanceDTO aumentoPerformance(final int distancia, final int ano) {
        var eventos = this.eventoRepository
                .listaEventos()
                .stream()
                .filter(e -> e.getDistancia() == distancia && e.getAno() == ano)
                .collect(Collectors.toList());

        var index = 0;
        var diferenca = 0.0;
        for (int i = 0; i < eventos.size() - 1; i++) {
            var e1 = eventos.get(i);
            var e2 = eventos.get(i + 1);

            var t1 = e1.getHoras() * 3600 + e1.getMinutos() * 60 + e1.getSegundos();
            var t2 = e2.getHoras() * 3600 + e2.getMinutos() * 60 + e2.getSegundos();

            if (t1 - t2 > diferenca) {
                index = i;
                diferenca = t1 - t2;
            }
        }
        
        return new PerformanceDTO(eventos.get(index).getNome(), eventos.get(index + 1).getNome(), diferenca);
    }
}
