public class BagagemDeMao extends Bagagem {

    public BagagemDeMao(double peso, String descricao, Prioridade prioridade) {
        super(peso, descricao, prioridade);
    }

    @Override
    public double getCusto() {
        var peso = getPeso();
        if (peso <= 5)
            return 0;

        return (peso - 5) * 3 * taxaBagagem;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
