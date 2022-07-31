public class MotorEco extends Motor {
    
    public MotorEco(TipoCombustivel tipoMotor, int consumo) {
        super(tipoMotor, consumo);
    }

    private void ajustaConsumo() {
        var consumoAtual = this.getConsumo();
        var quilometragemAtual = this.getQuilometragem();
        
        if (quilometragemAtual >= 5000 && consumoAtual > 10) {
            var novoConsumo = consumoAtual - quilometragemAtual / 5000;
            this.consumo = novoConsumo;
        }
    }

    @Override
    public void percorre(int distancia) {
        this.ajustaConsumo();
        super.percorre(distancia);
    }
}
