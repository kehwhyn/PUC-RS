abstract class Bagagem {
    final static double taxaBagagem = 5;
    private final double peso;
    private final String descricao;
    private final Prioridade prioridade;

    public Bagagem(double peso, String descricao, Prioridade prioridade) {
        this.peso = peso;
        this.descricao = descricao;
        this.prioridade = prioridade;
    }

    public final double getPeso() {
        return this.peso;
    }

    public final String getDescricao() {
        return this.descricao;
    }

    public final Prioridade getPrioridade() {
        return this.prioridade;
    }

    public abstract double getCusto();

    @Override
    public String toString() {
        return "Bagagem {" +
                "peso = " + getPeso() +
                ", descricao = '" + getDescricao() + '\'' +
                ", prioridade = " + getPrioridade() +
                ", custo = " + getCusto() +
                '}';
    }
}
