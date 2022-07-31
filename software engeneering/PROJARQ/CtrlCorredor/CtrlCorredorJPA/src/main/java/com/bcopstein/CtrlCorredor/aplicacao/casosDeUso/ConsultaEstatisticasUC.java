package com.bcopstein.CtrlCorredor.aplicacao.casosDeUso;

import com.bcopstein.CtrlCorredor.aplicacao.dtos.EstatisticasDTO;
import com.bcopstein.CtrlCorredor.aplicacao.servicos.ServicoEstatistica;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class ConsultaEstatisticasUC {
    private ServicoEstatistica servicoEstatistica;

    @Autowired
    public ConsultaEstatisticasUC(ServicoEstatistica servicoEstatistica) {
        this.servicoEstatistica = servicoEstatistica;
    }

    public EstatisticasDTO run(int distancia) {
        return servicoEstatistica.calculaEstatisticas(distancia);
    }
}
