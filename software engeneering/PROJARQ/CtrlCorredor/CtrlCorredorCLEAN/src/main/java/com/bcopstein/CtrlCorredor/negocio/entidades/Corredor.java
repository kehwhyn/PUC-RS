package com.bcopstein.CtrlCorredor.negocio.entidades;

public class Corredor {
    private String cpf;
    private String nome;
    private String genero;
    private int diaDn, mesDn, anoDn;

    public Corredor(String cpf, String nome, int diaDn, int mesDn, int anoDn, String genero) {
        this.cpf = cpf;
        this.nome = nome;
        this.diaDn = diaDn;
        this.mesDn = mesDn;
        this.anoDn = anoDn;
        this.genero = genero;
    }

    public int getDiaDn() {
        return diaDn;
    }

    public int getMesDn() {
        return mesDn;
    }

    public int getAnoDn() {
        return anoDn;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getGenero() {
        return genero;
    }
}