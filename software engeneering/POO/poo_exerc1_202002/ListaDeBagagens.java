import java.util.ArrayList;

public class ListaDeBagagens {

    private ArrayList<Bagagem> bagagens;

    public ListaDeBagagens() {
        this.bagagens = new ArrayList<>();
    }

    public void incluir(Bagagem bagagem) {
        this.bagagens.add(bagagem);
    }

    public double custoTotal() {
        return this.bagagens.stream().mapToDouble(Bagagem::getCusto).sum();
    }

    public int getTotal() {
        return this.bagagens.size();
    }

    public int bagagensFrageis() {
        return (int) this.bagagens.stream()
                .filter((x) -> x.getDescricao().equals("fragil"))
                .count();
    }

    public double valorTotalSegurosContratados() {
        return this.bagagens.stream()
                .filter(x -> x.getDescricao().equals("fragil"))
                .map(x -> (BagagemFragil) x)
                .mapToDouble(BagagemFragil::getValorSeguro)
                .sum();
    }

    public void ordenadaPorPeso() {
        this.bagagens.sort((x, y) -> (int) (x.getPeso() - y.getPeso()));
        System.out.println(this.bagagens.toString());
    }

    public void ordenadaPorCusto() {
        this.bagagens.sort((x, y) -> (int) (x.getCusto() - y.getCusto()));
        System.out.println(this.bagagens.toString());
    }
}
