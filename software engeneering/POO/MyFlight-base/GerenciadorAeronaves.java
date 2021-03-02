package pucrs.myflight.modelo;

import java.util.ArrayList;
import java.util.Collections;

public class GerenciadorAeronaves {
    private ArrayList<Aeronave> aeronaves = new ArrayList<>();

    public void adicionar(Aeronave aeronave) {
        aeronaves.add(aeronave);
    }

    public ArrayList<Aeronave> listarTodas() {
        return (ArrayList<Aeronave>) aeronaves.clone();
    }

    public Aeronave buscarPorCodigo(String codigo) {
        for (var aeronave : aeronaves)
            if (aeronave.getCodigo().equals(codigo))
                return aeronave;
        return null;
    }

    public void ordenaDescricao() {
        Collections.sort(aeronaves);
    }

}
