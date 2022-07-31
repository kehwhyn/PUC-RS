package com.bcopstein.CtrlCorredor.negocio.servicos;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcopstein.CtrlCorredor.negocio.entidades.Corredor;
import com.bcopstein.CtrlCorredor.adaptadores.repositorios.CorredorRepository;

@Component
public class ServicoCorredor {

    private CorredorRepository corredorRepository;

    @Autowired
    public ServicoCorredor(CorredorRepository corredorRepository) {
        this.corredorRepository = corredorRepository;
    }

    public void deletaTodos() {
        this.corredorRepository.deletaTodos();
    }

    public List<Corredor> listaCorredores() {
        return this.corredorRepository.listaCorredores();
    }

    public boolean cadastraCorredor(Corredor corredor) {
        return this.corredorRepository.cadastraCorredor(corredor);
    }
}
