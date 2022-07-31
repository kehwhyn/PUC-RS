public class SUVFlex extends Carro {

    public SUVFlex(String modelo, int capacidadeTanque) {
        super();
        this.modelo = modelo;
        this.motor = new Motor(TipoCombustivel.FLEX, 8);
        this.tanque = new TanqueCombustivel(TipoCombustivel.FLEX, capacidadeTanque);
    }

    @Override
    public int abastece(TipoCombustivel tipoCombustivel, int quantidade) {
        var consumoMotor = tipoCombustivel == TipoCombustivel.GASOLINA ? 8 : 6;
        this.motor.setConsumo(consumoMotor);
        return super.abastece(tipoCombustivel, quantidade);
    }
}
