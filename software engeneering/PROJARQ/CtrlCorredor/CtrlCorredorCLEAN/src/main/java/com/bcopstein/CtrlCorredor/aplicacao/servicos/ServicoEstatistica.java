package com.bcopstein.CtrlCorredor.aplicacao.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcopstein.CtrlCorredor.negocio.entidades.Evento;
import com.bcopstein.CtrlCorredor.aplicacao.dtos.PerformanceDTO;
import com.bcopstein.CtrlCorredor.aplicacao.dtos.EstatisticasDTO;
import com.bcopstein.CtrlCorredor.negocio.repositorios.IEventoRepository;

@Component
public class ServicoEstatistica {
    private IEventoRepository eventoRepository;
    private ICalculoEstatistica calculoEstatistica;

    @Autowired
    public ServicoEstatistica(IEventoRepository eventoRepository, ICalculoEstatistica calculoEstatistica) {
        this.eventoRepository = eventoRepository;
        this.calculoEstatistica = calculoEstatistica;
    }

    public void deletaEventos() {
        this.eventoRepository.deletaEventos();
    }

    public List<Evento> listaEventos() {
        return eventoRepository.listaEventos();
    }
    
    public boolean cadastraEvento(Evento evento) {
        return this.eventoRepository.cadastraEvento(evento);
    }

    public EstatisticasDTO calculaEstatisticas(int distancia) {
        return calculoEstatistica.calculaEstatisticas(distancia);
    }

    public PerformanceDTO calculaAumentoPerformance(int distancia, int ano) {
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
