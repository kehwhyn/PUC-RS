public interface ITanqueCombustivel {
    TipoCombustivel getTipoCombustivel();
    int getCapacidade();
    int getCombustivelDisponivel();
    boolean abastece(TipoCombustivel tipoCombustivel, int quantidade);
    boolean gasta(int quantidade);
}
