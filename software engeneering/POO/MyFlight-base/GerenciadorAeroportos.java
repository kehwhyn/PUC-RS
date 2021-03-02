package pucrs.myflight.modelo;

import java.util.ArrayList;
import java.util.Collections;

public class GerenciadorAeroportos {
    private ArrayList<Aeroporto> aeroportos = new ArrayList<>();

    public void adicionar(Aeroporto aeroporto) {
        aeroportos.add(aeroporto);
    }

    public ArrayList<Aeroporto> listarTodas() {
        return (ArrayList<Aeroporto>) aeroportos.clone();
    }

    public Aeroporto buscarPorCodigo(String codigo) {
        for (var aeroporto : aeroportos)
            if (aeroporto.getCodigo().equals(codigo))
                return aeroporto;
        return null;
    }

    public void ordenaNome() {
        Collections.sort(aeroportos);
    }
}
