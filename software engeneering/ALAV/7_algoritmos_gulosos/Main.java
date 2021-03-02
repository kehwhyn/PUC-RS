import java.util.ArrayList;

public class Main {
    private final static int s = 0;
    private final static int f = 1;
    private static int iterations = 0;

    public static void main(String[] args) {

        System.out.println("+--------------------------------+");
        System.out.println("|             Troco              |");
        System.out.println("+--------------------------------+");
        System.out.println("| Length | Time(ms) | Iterations |");
        System.out.println("+--------------------------------+");

        var start = System.currentTimeMillis();
        var aux = troco(75);
        var end = System.currentTimeMillis() - start;

        System.out.println(String.format("|%8d|%10d|%12d|", aux.size(), end, iterations));
        System.out.println("+--------------------------------+\n");

        System.out.println("+--------------------------------+");
        System.out.println("|            SDM_Guloso          |");
        System.out.println("+--------------------------------+");
        System.out.println("| Length | Time(ms) | Iterations |");
        System.out.println("+--------------------------------+");
        ArrayList<int[]> intervalos = new ArrayList<int[]>() {
            {
                add(new int[] { 4, 8 });
                add(new int[] { 6, 7 });
                add(new int[] { 13, 14 });
                add(new int[] { 4, 5 });
                add(new int[] { 2, 4 });
                add(new int[] { 6, 9 });
                add(new int[] { 7, 10 });
                add(new int[] { 9, 11 });
                add(new int[] { 1, 6 });
                add(new int[] { 3, 13 });
                add(new int[] { 9, 12 });
            }
        };
        intervalos.sort((x, y) -> x[1] - y[1]);

        iterations = 0;
        start = System.currentTimeMillis();
        var tmp = SDM_Guloso(intervalos);
        end = System.currentTimeMillis() - start;

        System.out.println(String.format("|%8d|%10d|%12d|", intervalos.size() - 1, end, iterations));
        System.out.println("+--------------------------------+\n");
    }

    public static ArrayList<Integer> troco(int n) {
        final int[] C = { 100, 25, 10, 5, 1 };
        var S = new ArrayList<Integer>();
        int s = 0;

        while (s != n) {
            iterations++;
            var x = 0;
            for (Integer integer : C) {
                iterations++;
                x = integer;
                if (s + x <= n) {
                    x = integer;
                    break;
                }
            }
            S.add(x);
            s += x;
        }
        return S;
    }

    public static ArrayList<Integer> SDM_Guloso(ArrayList<int[]> y) {
        y.add(0, new int[] { -1, -1 }); // f0 <- -inf
        ArrayList<Integer> X = new ArrayList<>();
        for (int k = 1, i = 0; k < y.size(); k++) {
            iterations++;
            if (y.get(k)[s] > y.get(i)[f]) {
                X.add(k - 1);
                i = k;
            }
        }
        return X;
    }
}