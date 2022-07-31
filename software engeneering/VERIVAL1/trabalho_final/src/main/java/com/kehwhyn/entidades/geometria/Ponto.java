package com.kehwhyn.entidades.geometria;

public class Ponto {
    private int x;
    private int y;

    public Ponto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Ponto [x=" + x + ", y=" + y + "]";
    }

    // ERRO, pontos deviam ser comparados com igualdades
    // não através de subtrações
    @Override
    public boolean equals(Object outro) {
        if (outro instanceof Ponto) {
            Ponto outroP = (Ponto) outro;
            return x == outroP.x && y == outroP.y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}