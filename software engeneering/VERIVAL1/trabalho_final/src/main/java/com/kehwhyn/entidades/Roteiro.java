package com.kehwhyn.entidades;

import java.util.Collection;
import java.util.LinkedList;

import com.kehwhyn.entidades.geometria.Reta;
import com.kehwhyn.entidades.geometria.SituacaoReta;

public class Roteiro {
    private Reta rota;
    private Bairro bairroOrigem;
    private Bairro bairroDestino;
    private Collection<Bairro> bairrosPercorridos;

    public Roteiro(Bairro bairroOrigem, Bairro bairroDestino,Collection<Bairro> todosBairros) {
        this.bairroOrigem = bairroOrigem;
        this.bairroDestino = bairroDestino;
        bairrosPercorridos = new LinkedList<>(); 
        var pOrig = bairroOrigem.getPontoCentral();
        var pDest = bairroDestino.getPontoCentral();
        rota = new Reta(pOrig,pDest);
        determinaBairrosPercorridos(rota,todosBairros);
    }

    public Reta getRota() {
        return rota;
    }

    public Bairro getBairroOrigem() {
        return bairroOrigem;
    }

    public Bairro getBairroDestino() {
        return bairroDestino;
    }

    public Collection<Bairro> bairrosPercoridos() {
        return bairrosPercorridos;
    }

    private void determinaBairrosPercorridos(Reta rota, Collection<Bairro> todosBairros) {
        for (Bairro bairro : todosBairros) {
            SituacaoReta sr = bairro.getClassificacao(rota);
            if (sr != SituacaoReta.TODA_FORA)
                bairrosPercorridos.add(bairro);
        }
    }

    @Override
    public String toString() {
        return "Roteiro [bairroDestino=" + bairroDestino + ", bairroOrigem=" + bairroOrigem + "]";
    }

    @Override
    public boolean equals(Object outro) {
        if (outro instanceof Roteiro) {
            var outroRoteiro = (Roteiro) outro;
            return 
                this.getBairroOrigem().equals(outroRoteiro.getBairroOrigem())
                && this.getBairroDestino().equals(outroRoteiro.getBairroDestino());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}