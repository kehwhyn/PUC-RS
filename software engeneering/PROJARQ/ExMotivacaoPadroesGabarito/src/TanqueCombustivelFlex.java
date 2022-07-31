public class TanqueCombustivelFlex extends TanqueCombustivel {
    private TipoCombustivel tipoUltimoAbastecimento;

    public TanqueCombustivelFlex(int capacidade) {
        super(TipoCombustivel.FLEX, capacidade);
        this.tipoUltimoAbastecimento = TipoCombustivel.GASOLINA;
    }

    public TipoCombustivel getUltimoAbastecimento(){
        return this.tipoUltimoAbastecimento;
    }

    @Override
    public boolean abastece(TipoCombustivel tipoCombustivel, int quantidade) {
        boolean abasteceu = super.abastece(tipoCombustivel, quantidade);
        if (abasteceu){
            this.tipoUltimoAbastecimento = tipoCombustivel;
        }
        return abasteceu;
    }

    @Override
    public String toString() {
        return "TanqueCombustivelFlex [capacidade=" + getCapacidade() + ", combustivelDisponivel=" + getCombustivelDisponivel()
                + ", ultimoAbastecimento="+tipoUltimoAbastecimento + "]";
    }

}
