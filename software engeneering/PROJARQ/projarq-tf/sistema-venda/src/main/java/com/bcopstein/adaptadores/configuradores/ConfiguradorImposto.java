package com.bcopstein.adaptadores.configuradores;

import com.bcopstein.aplicacao.servicos.ICalculoImposto;
import com.bcopstein.aplicacao.servicos.ImpostoBrasil;
import com.bcopstein.aplicacao.servicos.ImpostoChile;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguradorImposto {

  @Bean
  @ConditionalOnProperty(name = "venda.imposto", havingValue = "brasil", matchIfMissing = true)
  public ICalculoImposto opcaoPaisBrasil() {
    return new ImpostoBrasil();
  }

  @Bean
  @ConditionalOnProperty(name = "venda.imposto", havingValue = "chile")
  public ICalculoImposto opcaoPaisChile() {
    return new ImpostoChile();
  }
}