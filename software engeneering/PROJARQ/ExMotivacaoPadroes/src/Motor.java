public class Motor {

    protected int consumo; // em quilometros por unidade. Ex: Km/Lt
    private int quilometragem;
    private TipoCombustivel tipoMotor;

    public Motor(TipoCombustivel tipoMotor, int consumo) {
        this.consumo = consumo;
        this.tipoMotor = tipoMotor;
    }

    public void setConsumo(int consumo) {
        this.consumo = consumo;
    }

    public int getConsumo() {
        return this.consumo;
    }

    public int getQuilometragem() {
        return this.quilometragem;
    }

    public TipoCombustivel getTipoMotor() {
        return this.tipoMotor;
    }

    public int combustivelNecessario(int distancia) {
        return distancia / consumo;
    }

    public void percorre(int distancia) {
        quilometragem += distancia;
    }

    @Override
    public String toString() {
        return "Motor [consumo=" + consumo + ", quilometragem=" + quilometragem + ", tipoMotor=" + tipoMotor + "]";
    }
}