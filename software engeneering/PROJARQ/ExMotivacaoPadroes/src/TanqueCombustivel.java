public class TanqueCombustivel {

    private int capacidade;
    private int combustivelDisponivel;
    private TipoCombustivel tipoCombustivel;

    public TanqueCombustivel(TipoCombustivel tipoCombustivel, int capacidade) {
        this.tipoCombustivel = tipoCombustivel;
        this.capacidade = capacidade;
        this.combustivelDisponivel = 0;
    }

    public TipoCombustivel getTipoCombustivel() {
        return tipoCombustivel;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public int getCombustivelDisponivel() {
        return combustivelDisponivel;
    }

    // Retorna false se o tipo de combustivel for incompativel ou se a quantidade
    // for maior que a capacidade livre
    public boolean abastece(TipoCombustivel tipoCombustivel, int quantidade) {
        if (this.tipoCombustivel == TipoCombustivel.FLEX
        && !(tipoCombustivel == TipoCombustivel.ALCOOL 
        ||   tipoCombustivel == TipoCombustivel.GASOLINA)
        )
            return false;

        else if(this.tipoCombustivel != TipoCombustivel.DIESEL)
            return false;
        
        if (getCombustivelDisponivel() + quantidade > getCapacidade())
            return false;

        combustivelDisponivel += quantidade;
        return true;
    }

    public boolean gasta(int quantidade) {
        if (getCombustivelDisponivel() - quantidade < 0)
            return false;

        combustivelDisponivel -= quantidade;
        return true;
    }

    @Override
    public String toString() {
        return "TanqueCombustivel [capacidade=" + capacidade + ", combustivelDisponivel=" + combustivelDisponivel
                + ", tipoCombustivel=" + tipoCombustivel + "]";
    }
}
