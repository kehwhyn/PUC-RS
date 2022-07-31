package com.bcopstein.CtrlCorredor.aplicacao.servicos;

import com.bcopstein.CtrlCorredor.aplicacao.dtos.EstatisticasDTO;

public interface ICalculoEstatistica {
    EstatisticasDTO calculaEstatisticas(int distancia);
}
