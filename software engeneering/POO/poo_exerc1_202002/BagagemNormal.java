public class BagagemNormal extends Bagagem {

    public BagagemNormal(double peso, String descricao, Prioridade prioridade) {
        super(peso, descricao, prioridade);
    }

    @Override
    public double getCusto() {
        double custo = 0;

        if (getPrioridade() == Prioridade.PRIORITARIA)
            custo += 5 * taxaBagagem;

        if (getPrioridade() == Prioridade.URGENTE)
            custo += 10 * taxaBagagem;

        var peso = getPeso();
        if (peso <= 20)
            return custo;

        return (peso - 20) * taxaBagagem + custo;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
