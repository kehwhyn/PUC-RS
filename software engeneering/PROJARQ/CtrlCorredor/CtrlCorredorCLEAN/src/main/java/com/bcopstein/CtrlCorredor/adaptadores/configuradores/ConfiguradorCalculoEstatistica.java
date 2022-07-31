package com.bcopstein.CtrlCorredor.adaptadores.configuradores;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import com.bcopstein.CtrlCorredor.aplicacao.servicos.EstatisticaNormal;
import com.bcopstein.CtrlCorredor.aplicacao.servicos.ICalculoEstatistica;
import com.bcopstein.CtrlCorredor.negocio.repositorios.IEventoRepository;
import com.bcopstein.CtrlCorredor.aplicacao.servicos.EstatisticaDesconsidera;

@Configuration
public class ConfiguradorCalculoEstatistica {
    private IEventoRepository eventoRep;

    @Autowired
    public ConfiguradorCalculoEstatistica(IEventoRepository eventoRep) {
        this.eventoRep = eventoRep;
    }

    @Bean
    @ConditionalOnProperty(name = "calculo.estatistica", havingValue = "original", matchIfMissing = true)
    public ICalculoEstatistica opcaoRegraClassica() {
        return new EstatisticaNormal(eventoRep);
    }

    @Bean
    @ConditionalOnProperty(name = "calculo.estatistica", havingValue = "desconsidera")
    public ICalculoEstatistica opcaoDesconsidera() {
        return new EstatisticaDesconsidera(eventoRep);
    }
}
