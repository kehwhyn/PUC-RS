package pucrs.myflight.modelo;

import java.util.ArrayList;
import java.util.Collections;

public class GerenciadorRotas {
    private ArrayList<Rota> rotas = new ArrayList<>();

    public void adicionar(Rota rota) {
        rotas.add(rota);
    }

    public ArrayList<Rota> listarTodas() {
        return (ArrayList<Rota>) rotas.clone();
    }

    public Rota buscarPorOrigem(Aeroporto origem) {
        for (var rota : rotas)
            if (rota.getOrigem().equals(origem))
                return rota;
        return null;
    }

    public void ordenaCia() {
        Collections.sort(rotas);
    }
}
