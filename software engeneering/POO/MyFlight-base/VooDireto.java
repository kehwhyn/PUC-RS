package pucrs.myflight.modelo;

import java.time.Duration;
import java.time.LocalDateTime;

public class VooDireto extends Voo {

    private Rota rota;

    public VooDireto(LocalDateTime datahora, Rota rota) {
        super(datahora);
        this.rota = rota;
    }
    @Override
    public LocalDateTime getDataHora() {
        return null;
    }

    @Override
    public Duration getDuracao() {
        return null;
    }

    @Override
    public Rota getRota() {
        return null;
    }

    @Override
    public Status getStatus() {
        return null;
    }
}
