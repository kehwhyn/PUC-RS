public class BagagemFragil extends Bagagem {
    private double valorSeguro;

    public BagagemFragil(double peso, String descricao, double valorSeguro, Prioridade prioridade) {
        super(peso, descricao, prioridade);
        this.valorSeguro = valorSeguro;
    }

    public final double getValorSeguro() {
        return this.valorSeguro;
    }

    public void setValorSeguro(double valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    @Override
    public double getCusto() {
        var prioridade = getPrioridade();
        if (prioridade == Prioridade.PRIORITARIA)
            return this.valorSeguro + 5 * taxaBagagem;

        if (prioridade == Prioridade.URGENTE)
            return  this.valorSeguro + 10 * taxaBagagem;

        return this.valorSeguro;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
