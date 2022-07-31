public class MotorEcono extends MotorBasico {
    private static final int consumoInicial = 20;
    private static final int kmDesgaste = 5000;
    private int consumoVariavel;

    public MotorEcono() {
        super(TipoCombustivel.GASOLINA, consumoInicial);
        this.consumoVariavel = consumoInicial;
    }

    @Override
    public int getConsumo() {
        return consumoVariavel;
    }

    @Override
    public void percorre(int distancia){
        super.percorre(distancia);
        consumoVariavel = consumoInicial - getQuilometragem() / kmDesgaste;
        consumoVariavel = (consumoVariavel<10) ? 10 : consumoVariavel;
    }

    @Override
    public String toString() {
        return "Motor [consumo=" + consumoVariavel + ", quilometragem=" + getQuilometragem() + "]";
    }
    
}
