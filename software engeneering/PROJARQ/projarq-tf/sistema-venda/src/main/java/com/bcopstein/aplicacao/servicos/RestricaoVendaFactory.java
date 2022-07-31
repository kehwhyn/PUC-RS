package com.bcopstein.aplicacao.servicos;

import java.time.LocalTime;

public class RestricaoVendaFactory {
  private static final LocalTime HORARIO_ABERTURA = LocalTime.of(7, 0, 0);
  private static final LocalTime HORARIO_FECHAMENTO = LocalTime.of(18, 0, 0);

  public static IRestricaoHorarioVenda getInstance(LocalTime horaRestricao) {
    if (horaRestricao.isAfter(HORARIO_ABERTURA) && horaRestricao.isBefore(HORARIO_FECHAMENTO)) {
      return new HorarioNormal();
    } else {
      return new HorarioPertoDoLimite();
    }
  }
}
