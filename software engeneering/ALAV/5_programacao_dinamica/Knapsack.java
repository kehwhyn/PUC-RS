import java.util.ArrayList;

public class Knapsack {
    public static void main(String[] args) {
        var itens1 = new ArrayList<itens>() {
            {
                add(new itens(23, 92));
                add(new itens(31, 57));
                add(new itens(29, 49));
                add(new itens(44, 68));
                add(new itens(53, 60));
                add(new itens(38, 43));
                add(new itens(63, 67));
                add(new itens(85, 84));
                add(new itens(89, 87));
                add(new itens(82, 72));
            }
        };

        var response = maxTab(165, 10, itens1);

        System.out.println("Response 1: " + response);

        var itens2 = new ArrayList<itens>() {
            {
                add(new itens(56, 50));
                add(new itens(59, 50));
                add(new itens(80, 64));
                add(new itens(64, 46));
                add(new itens(75, 50));
                add(new itens(17, 05));
            }
        };

        response = maxTab(190, 6, itens2);
        System.out.println("Response 2: " + response);
    }

    // o de força bruta não foi implementado ainda
    public static class itens {
        public int peso;
        public int valor;

        public itens(int peso, int valor) {
            this.valor = valor;
            this.peso = peso;
        }
    }

    public static int maxTab(int C, int N, ArrayList<itens> itens) {
        int[][] maxtab = new int[N + 1][C + 1];

        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= C; j++)
                if (itens.get(i - 1).peso <= j)
                    maxtab[i][j] = Math.max(maxtab[i - 1][j],
                            itens.get(i - 1).valor + maxtab[i - 1][j - itens.get(i - 1).peso]);

                else
                    maxtab[i][j] = maxtab[i - 1][j];

        return maxtab[N][C];
    }
}