public class MotorFlex extends MotorBasico {
    private int consumoAlcool;
    private TanqueCombustivelFlex tanqueCombustivelFLEX;

    public MotorFlex(int consumoGasolina, int consumoAlcool,TanqueCombustivelFlex tanqueCombustivel) {
        super(TipoCombustivel.FLEX, consumoGasolina);
        this.consumoAlcool = consumoAlcool;
        this.tanqueCombustivelFLEX = tanqueCombustivel;
    }

    @Override
    public int getConsumo() {
        if (tanqueCombustivelFLEX.getUltimoAbastecimento() == TipoCombustivel.ALCOOL){
            return this.consumoAlcool;
        }
        return super.getConsumo();
    }
    
    @Override
    public String toString() {
        return "MotorFlex [consumoGas=" + super.getConsumo() + 
               ", consumoAlc="+consumoAlcool+
               ", quilometragem=" + getQuilometragem() +  "]";
    }
}
