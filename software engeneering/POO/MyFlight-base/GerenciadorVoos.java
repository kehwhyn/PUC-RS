package pucrs.myflight.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class GerenciadorVoos {
    private ArrayList<Voo> voos = new ArrayList<>();

    public void adicionar(Voo voo) {
        voos.add(voo);
    }

    public ArrayList<Voo> listarTodos() {
        return (ArrayList<Voo>) voos.clone();
    }

    public Voo buscarData(LocalDateTime date) {
        for (var voo : voos)
            if (voo.getDataHora().equals(date))
                return voo;
        return null;
    }
}
