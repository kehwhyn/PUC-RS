package com.bcopstein.CtrlCorredor.adaptadores.repositorios;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcopstein.CtrlCorredor.negocio.entidades.Corredor;
import com.bcopstein.CtrlCorredor.negocio.repositorios.ICorredorRepository;

@Repository
public class CorredorRepository {

    private ICorredorRepository corredorRep;

    @Autowired
    public CorredorRepository(ICorredorRepository corredorRep) {
        this.corredorRep = corredorRep;
    }

    public List<Corredor> listaCorredores() {
        var resp = this.corredorRep.todos();
        return resp;
    }

    public void deletaTodos() {
        // Limpa a base de dados
        this.corredorRep.removeTodos();
    }

    public boolean cadastraCorredor(Corredor corredor) {
        // Então cadastra o novo "corredor único"
        this.corredorRep.cadastra(corredor);
        return true;
    }
}
