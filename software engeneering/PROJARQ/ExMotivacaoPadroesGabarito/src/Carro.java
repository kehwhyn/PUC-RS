public class Carro {

    private String modelo;
    private IMotor motor;
    private ITanqueCombustivel tanqueCombustivel;

    public Carro(String modelo, IMotor motor, ITanqueCombustivel tanqueCombustivel) {
        this.modelo = modelo;
        this.motor = motor;
        this.tanqueCombustivel = tanqueCombustivel;
    }

    public String getModelo() {
        return modelo;
    }

    public int getCombustivelDisponivel() {
        return tanqueCombustivel.getCombustivelDisponivel();
    }

    // Retorna a quantidade efetivamente abastecida
    public int abastece(TipoCombustivel tipoCombustivel, int quantidade) {
        int capacidadeLivre = tanqueCombustivel.getCapacidade() - tanqueCombustivel.getCombustivelDisponivel();
        if (capacidadeLivre < quantidade) {
            tanqueCombustivel.abastece(tipoCombustivel, capacidadeLivre);
            return capacidadeLivre;
        } else {
            tanqueCombustivel.abastece(tipoCombustivel, quantidade);
            return quantidade;
        }
    }

    // Retorna a distancia que consegue viajar com o combustivel remanescente
    public int verificaSePodeViajar(int distancia) {
        int combustivelNecessario = motor.combustivelNecessario(distancia);
        if (tanqueCombustivel.getCombustivelDisponivel() >= combustivelNecessario) {
            return distancia;
        } else {
            return tanqueCombustivel.getCombustivelDisponivel() * motor.getConsumo();
        }
    }

    // Retorna true se conseguiu viajar
    public boolean viaja(int distancia) {
        if (verificaSePodeViajar(distancia) >= distancia) {
            motor.percorre(distancia);
            tanqueCombustivel.gasta(motor.combustivelNecessario(distancia));
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Carro:\n  Modelo=" + modelo + "\n  Motor=" + motor + "\n  Tanque=" + tanqueCombustivel;
    }
}
