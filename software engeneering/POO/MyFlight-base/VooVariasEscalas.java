package pucrs.myflight.modelo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class VooVariasEscalas extends VooEscalas {

    private ArrayList<Rota> escalas;

    public VooVariasEscalas(ArrayList<Rota> escalas, LocalDateTime dataHora, Duration duracao) {
        super(escalas.get(0), escalas.get(escalas.size() - 1), dataHora, duracao);
        this.escalas = escalas;
    }

    @Override
    public String toString() {
        return super.toString() + "escalas ->" + escalas.toString();
    }
}
