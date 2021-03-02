public class Main {
    public static void main(String[] args) {
        System.out.println();

        /* 1(a)
         * Sim, o polimorfismo pode ser observado através das diferentes definições da classe Bagagem.
         * */

        /* 1(b)
         * Não, um mesmo método não foi implementado várias vezes com diferentes assinaturas na mesma classe.
         * */

        /* 1(c)
         * Sim, as classes filhas vão ter que sobrescrever o método "getCusto()".
         * */

        /* 1(d)
         * Ela não pode ser instânciada por si só.
         * */

        /* 1(e)
         * Classes derivadas são obrigadas a implementar o método.
         * */

        ListaDeBagagens listaDeBagagens = new ListaDeBagagens();

        listaDeBagagens.incluir(new BagagemDeMao(2, "mao", Prioridade.NORMAL));
        listaDeBagagens.incluir(new BagagemDeMao(6, "mao", Prioridade.NORMAL));

        listaDeBagagens.incluir(new BagagemNormal(18, "normal", Prioridade.NORMAL));
        listaDeBagagens.incluir(new BagagemNormal(22, "normal", Prioridade.NORMAL));
        listaDeBagagens.incluir(new BagagemNormal(18, "normal", Prioridade.PRIORITARIA));
        listaDeBagagens.incluir(new BagagemNormal(18, "normal", Prioridade.URGENTE));

        listaDeBagagens.incluir(new BagagemFragil(200, "fragil", 150, Prioridade.NORMAL));
        listaDeBagagens.incluir(new BagagemFragil(200, "fragil", 150, Prioridade.PRIORITARIA));
        listaDeBagagens.incluir(new BagagemFragil(200, "fragil", 150, Prioridade.URGENTE));

        System.out.println("Custo total: " + listaDeBagagens.custoTotal());

        System.out.println("Nro bagagens frageis: " + listaDeBagagens.bagagensFrageis());

        System.out.println("Valor total seguro: " + listaDeBagagens.valorTotalSegurosContratados());

        System.out.println("Ordenado por peso: ");
        listaDeBagagens.ordenadaPorPeso();

        System.out.println("Ordenado por custo: ");
        listaDeBagagens.ordenadaPorCusto();
    }
}
