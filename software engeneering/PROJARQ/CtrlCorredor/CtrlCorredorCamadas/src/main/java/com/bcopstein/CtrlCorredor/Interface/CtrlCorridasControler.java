package com.bcopstein.CtrlCorredor.Interface;

import java.util.List;

import com.bcopstein.CtrlCorredor.acessoDados.Corredor;
import com.bcopstein.CtrlCorredor.acessoDados.Evento;
import com.bcopstein.CtrlCorredor.dominio.EstatisticasDTO;
import com.bcopstein.CtrlCorredor.dominio.PerformanceDTO;
import com.bcopstein.CtrlCorredor.dominio.ServicoCorredor;
import com.bcopstein.CtrlCorredor.dominio.ServicoEstatistica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ctrlCorridas")
public class CtrlCorridasControler {

    private ServicoCorredor servicoCorredor;
    private ServicoEstatistica servicoEstatistica;
    
    @Autowired
    public CtrlCorridasControler(ServicoCorredor servicoCorredor, ServicoEstatistica servicoEstatistica) {
        this.servicoCorredor = servicoCorredor;
        this.servicoEstatistica = servicoEstatistica;
    }

    @GetMapping("/corredor")
    @CrossOrigin(origins = "*")
    public List<Corredor> consultaCorredor() {
        return this.servicoCorredor.listaCorredores();
    }

    @PostMapping("/corredor")
    @CrossOrigin(origins = "*")
    public boolean cadastraCorredor(@RequestBody final Corredor corredor) {
        this.servicoCorredor.deletaTodos();
        return this.servicoCorredor.cadastraCorredor(corredor);
    }

    @GetMapping("/eventos")
    @CrossOrigin(origins = "*")
    public List<Evento> consultaEventos() {
        return this.servicoEstatistica.listaEventos();
    }

    @PostMapping("/eventos") // adiciona evento no único corredor
    @CrossOrigin(origins = "*")
    public boolean informaEvento(@RequestBody final Evento evento) {
        return this.servicoEstatistica.cadastraEvento(evento);
    }

    @GetMapping("/estatisticas")
    @CrossOrigin(origins = "*")
    public EstatisticasDTO estatisticas(@RequestParam final int distancia) {
        return this.servicoEstatistica.estatisticas(distancia);
    }

    @GetMapping("/aumentoPerformance")
    @CrossOrigin(origins = "*")
    public PerformanceDTO aumentoPerformance(@RequestParam final int distancia, @RequestParam final int ano) {
        return this.servicoEstatistica.aumentoPerformance(distancia, ano);
    }
}
