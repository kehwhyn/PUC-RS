import java.util.ArrayList;

public class Main {
    private static int iterations = 0;

    public static void main(String[] args) {
        System.out.println("+-----------------------------------------+");
        System.out.println("|                 backPack                |");
        System.out.println("+-----------------------------------------+");
        System.out.println("| Length | Time(ms) | Iterations | Answer |");
        System.out.println("+-----------------------------------------+");

        int[] peso = { 23, 31, 29, 44, 53, 38, 63, 85, 89, 82 };
        int[] valores = { 92, 57, 49, 68, 60, 43, 67, 84, 87, 72 };

        var start = System.currentTimeMillis();
        var tmp = backPack(peso, valores, peso.length - 1, 165);
        var end = System.currentTimeMillis() - start;
        System.out.println(String.format("|%8d|%10d|%12d|%8d|", peso.length, end, iterations, tmp));

        int[] peso1 = { 56, 59, 80, 64, 75, 17 };
        int[] valores1 = { 50, 50, 64, 46, 50, 05 };

        start = System.currentTimeMillis();
        tmp = backPack(peso1, valores1, peso1.length - 1, 165);
        end = System.currentTimeMillis() - start;
        System.out.println(String.format("|%8d|%10d|%12d|%8d|", peso1.length, end, iterations, tmp));
        System.out.println("+--------------------------------+\n");

        System.out.println("+-----------------------------------------+");
        System.out.println("|                 backPackPD              |");
        System.out.println("+-----------------------------------------+");
        System.out.println("| Length | Time(ms) | Iterations | Answer |");
        System.out.println("+-----------------------------------------+");

        int capacidade = 165;
        ArrayList<itens> itens = new ArrayList<>() {
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

        start = System.currentTimeMillis();
        var response = backPackPD(capacidade, itens.size(), itens);
        end = System.currentTimeMillis() - start;
        System.out.println(String.format("|%8d|%10d|%12d|%8d|", itens.size(), end, iterations, response));

        iterations = 0;
        capacidade = 190;
        itens = new ArrayList<>() {
            {
                add(new itens(56, 50));
                add(new itens(59, 50));
                add(new itens(80, 64));
                add(new itens(64, 46));
                add(new itens(75, 50));
                add(new itens(17, 05));
            }
        };

        start = System.currentTimeMillis();
        response = backPackPD(capacidade, itens.size(), itens);
        end = System.currentTimeMillis() - start;
        System.out.println(String.format("|%8d|%10d|%12d|%8d|", itens.size(), end, iterations, response));
        System.out.println("+-----------------------------------------+\n");

        System.out.println("+--------------------------------+");
        System.out.println("|               ED               |");
        System.out.println("+--------------------------------+");
        System.out.println("| Time(ms) | Iterations | Answer |");
        System.out.println("+--------------------------------+");

        String S = "Casablanca";
        String T = "Portentoso";

        iterations = 0;
        start = System.currentTimeMillis();
        response = ED(S, T, S.length(), T.length());
        end = System.currentTimeMillis() - start;

        System.out.println(String.format("|%10d|%12d|%8d|", end, iterations, response));
        System.out.println("+--------------------------------+\n");

        System.out.println("+--------------------------------+");
        System.out.println("|          distEdProgDina        |");
        System.out.println("+--------------------------------+");
        System.out.println("| Time(ms) | Iterations | Answer |");
        System.out.println("+--------------------------------+");

        S = "Maven, a Yiddish word meaning accumulator of knowledge, began as an attempt to "
                + "simplify the build processes in the Jakarta Turbine project. There were several"
                + " projects, each with their own Ant build files, that were all slightly different."
                + "JARs were checked into CVS. We wanted a standard way to build the projects, a clear "
                + "definition of what the project consisted of, an easy way to publish project information"
                + "and a way to share JARs across several projects. The result is a tool that can now be"
                + "used for building and managing any Java-based project. We hope that we have created "
                + "something that will make the day-to-day work of Java developers easier and generally help "
                + "with the comprehension of any Java-based project.";

        T = "This post is not about deep learning. But it could be might as well. This is the power of "
                + "kernels. They are universally applicable in any machine learning algorithm. Why you might"
                + "ask? I am going to try to answer this question in this article."
                + "Go to the profile of Marin Vlastelica Pogančić" + "Marin Vlastelica Pogančić Jun";

        iterations = 0;
        start = System.currentTimeMillis();
        response = distEdProgDina(S, T);
        end = System.currentTimeMillis() - start;

        System.out.println(String.format("|%10d|%12d|%8d|", end, iterations, response));
        System.out.println("+--------------------------------+\n");
    }

    // o de força bruta não foi implementado ainda
    public static class itens {
        public int peso;
        public int valor;

        public itens(int peso, int valor) {
            this.peso = peso;
            this.valor = valor;
        }
    }

    public static int backPackPD(int C, int N, ArrayList<itens> itens) {
        int[][] maxtab = new int[N + 1][C + 1];

        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= C; j++) {
                iterations++;
                if (itens.get(i - 1).peso <= j)
                    maxtab[i][j] = Math.max(maxtab[i - 1][j],
                            itens.get(i - 1).valor + maxtab[i - 1][j - itens.get(i - 1).peso]);
                else
                    maxtab[i][j] = maxtab[i - 1][j];
            }

        return maxtab[N][C];
    }

    public static int backPack(int[] weight, int[] value, int n, int W) {
        if (n <= 0) {
            return 0;
        } else if (weight[n - 1] > W) {
            return backPack(weight, value, n - 1, W);
        } else {
            return Math.max(backPack(weight, value, n - 1, W),
                    value[n - 1] + backPack(weight, value, n - 1, W - weight[n - 1]));
        }
    }

    public static int ED(String S, String T, int i, int j) {
        iterations++;

        if (i == 0 && j == 0)
            return 0;

        if (i == 0)
            return j;

        if (j == 0)
            return i;

        if (S.charAt(i - 1) == T.charAt(j - 1))
            return ED(S, T, i - 1, j - 1);

        var sub = ED(S, T, i - 1, j - 1) + 1;
        var ins = ED(S, T, i, j - 1) + 1;
        var rem = ED(S, T, i - 1, j) + 1;

        return Math.min(Math.min(sub, ins), rem);
    }

    public static int distEdProgDina(String A, String B) {
        var m = A.length();
        var n = B.length();
        int[][] matriz = new int[m + 1][n + 1];

        matriz[0][0] = 0;
        for (int i = 1; i <= m; i++)
            matriz[i][0] = matriz[i - 1][0] + 1;

        for (int i = 1; i <= n; i++)
            matriz[0][i] = matriz[0][i - 1] + 1;

        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++) {
                iterations++;
                var custoExtra = (A.charAt(i - 1) == B.charAt(j - 1)) ? 0 : 1;
                matriz[i][j] = Math.min(Math.min(matriz[i - 1][j] + 1, matriz[i][j - 1] + 1),
                        matriz[i - 1][j - 1] + custoExtra);
            }

        return matriz[m][n];
    }
}