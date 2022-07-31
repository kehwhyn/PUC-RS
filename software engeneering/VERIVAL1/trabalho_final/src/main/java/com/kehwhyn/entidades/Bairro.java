package com.kehwhyn.entidades;

import com.kehwhyn.entidades.geometria.Area;
import com.kehwhyn.entidades.geometria.Reta;
import com.kehwhyn.entidades.geometria.Ponto;
import com.kehwhyn.entidades.geometria.SituacaoReta;

public class Bairro {
    private Area area;
    private String nome;
    private double custoTransporte;

    public static Bairro novoBairroQuadrado(String nome, Ponto pSupEsq, int lado, double custoTransporte) {
        var pInfDir = new Ponto(pSupEsq.getX() + lado, pSupEsq.getY() - lado);
        return new Bairro(nome, new Area(pSupEsq, pInfDir), custoTransporte);
    }

    public static Bairro novoBairroRetangular(String nome, Ponto pSupEsq, int ladoH, int ladoV, double custoTransporte) {
        var pInfDir = new Ponto(pSupEsq.getX() + ladoH, pSupEsq.getY() - ladoV);
        return new Bairro(nome, new Area(pSupEsq, pInfDir), custoTransporte);
    }

    private Bairro(String nome, Area area, double custoTransporte) {
        this.nome = nome;
        this.area = area;
        this.custoTransporte = custoTransporte;
    }

    public Area getArea() {
        return area;
    }

    public String getNome() {
        return nome;
    }

    public Ponto getPontoCentral() {
        return area.pontoCentral();
    }

    public double getCustoTransporte() {
        return custoTransporte;
    }

    public SituacaoReta getClassificacao(Reta reta) {
        return getArea().classifica(reta);
    }

    public void alteraCustoTransporte(double novoValor) {
        if (novoValor < 0.0)
            throw new IllegalArgumentException("Valor invalido");

        this.custoTransporte = novoValor;
    }

    @Override
    public String toString() {
        return "Bairro [area=" + area + ", nome=" + nome + "]";
    }

    @Override
    public boolean equals(Object outro) {
        if (outro instanceof Bairro) {
            var outroBairro = (Bairro) outro;
            return  
                this.getNome().equals(outroBairro.getNome())
                && this.getCustoTransporte() == outroBairro.getCustoTransporte()
                && this.getArea().equals(outroBairro.getArea());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}