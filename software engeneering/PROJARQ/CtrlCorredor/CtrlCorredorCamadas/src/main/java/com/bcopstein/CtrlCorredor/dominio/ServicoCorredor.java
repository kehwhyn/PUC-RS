package com.bcopstein.CtrlCorredor.dominio;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcopstein.CtrlCorredor.acessoDados.Corredor;
import com.bcopstein.CtrlCorredor.acessoDados.CorredorRepository;

@Service
public class ServicoCorredor {
    
    private CorredorRepository corredorRepository;

    @Autowired
    public ServicoCorredor(CorredorRepository corredorRepository) {
        this.corredorRepository = corredorRepository;
    }

    public List<Corredor> listaCorredores() {
        return this.corredorRepository.listaCorredores();
    }

    public void deletaTodos() {
        this.corredorRepository.deletaTodos();
    }

    public boolean cadastraCorredor(Corredor corredor) {
        return this.corredorRepository.cadastraCorredor(corredor);
    }
}
