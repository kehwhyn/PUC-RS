package com.bcopstein.adaptadores.repositorios;

import java.util.List;

import com.bcopstein.negocio.entidades.Venda;
import com.bcopstein.negocio.repositorios.VendaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VendasRepImpl implements VendaRepository {
    private VendasRepJPA vendasRepJPA;

    @Autowired
    public VendasRepImpl(VendasRepJPA vendasRepJPA) {
        this.vendasRepJPA = vendasRepJPA;
    }

    @Override
    public void salvar(Venda venda) {
        vendasRepJPA.save(venda);
    }

    @Override
    public Venda recupera(long identificador) {
        return vendasRepJPA.findById(identificador);
    }

    @Override
    public List<Venda> todos() {
        return vendasRepJPA.findAll();
    }
}
