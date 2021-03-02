import java.util.Random;

public class Main {

    public static int iterations = 0;

    public static void main(String[] args) {
        var array1 = generateArray(32);
        var array2 = generateArray(2048);
        var array3 = generateArray(1_048_576);
        System.out.println("+--------------------------------+");
        System.out.println("|             maxVal1            |");
        System.out.println("+--------------------------------+");
        System.out.println("| Length | Time(ms) | Iterations |");
        System.out.println("+--------------------------------+");

        var start = System.currentTimeMillis();
        maxVal1(array1, array1.length);
        var end = System.currentTimeMillis() - start;
        System.out.println(String.format("|%8d|%10d|%12d|", array1.length, end, iterations));

        iterations = 0;
        start = System.currentTimeMillis();
        maxVal1(array2, array2.length);
        end = System.currentTimeMillis() - start;
        System.out.println(String.format("|%8d|%10d|%12d|", array2.length, end, iterations));

        iterations = 0;
        start = System.currentTimeMillis();
        maxVal1(array3, array3.length);
        end = System.currentTimeMillis() - start;
        System.out.println(String.format("|%8d|%10d|%12d|", array3.length, end, iterations));
        System.out.println("+--------------------------------+\n");

        System.out.println("+--------------------------------+");
        System.out.println("|             maxVal2            |");
        System.out.println("+--------------------------------+");
        System.out.println("| Length | Time(ms) | Iterations |");
        System.out.println("+--------------------------------+");
        iterations = 0;
        start = System.currentTimeMillis();
        maxVal2(array1, 0, array1.length - 1);
        end = System.currentTimeMillis() - start;
        System.out.println(String.format("|%8d|%10d|%12d|", array1.length, end, iterations));

        iterations = 0;
        start = System.currentTimeMillis();
        maxVal2(array2, 0, array2.length - 1);
        end = System.currentTimeMillis() - start;
        System.out.println(String.format("|%8d|%10d|%12d|", array2.length, end, iterations));

        iterations = 0;
        start = System.currentTimeMillis();
        maxVal2(array3, 0, array3.length - 1);
        end = System.currentTimeMillis() - start;
        System.out.println(String.format("|%8d|%10d|%12d|", array3.length, end, iterations));
        System.out.println("+--------------------------------+\n");

        System.out.println("+--------------------------------+");
        System.out.println("|           mergeSort            |");
        System.out.println("+--------------------------------+");
        System.out.println("| Length | Time(ms) | Iterations |");
        System.out.println("+--------------------------------+");
        iterations = 0;
        start = System.currentTimeMillis();
        mergeSort(array1, array1.length);
        end = System.currentTimeMillis() - start;
        System.out.println(String.format("|%8d|%10d|%12d|", array1.length, end, iterations));

        iterations = 0;
        start = System.currentTimeMillis();
        mergeSort(array2, array2.length);
        end = System.currentTimeMillis() - start;
        System.out.println(String.format("|%8d|%10d|%12d|", array2.length, end, iterations));

        iterations = 0;
        start = System.currentTimeMillis();
        mergeSort(array3, array3.length);
        end = System.currentTimeMillis() - start;
        System.out.println(String.format("|%8d|%10d|%12d|", array3.length, end, iterations));
        System.out.println("+--------------------------------+\n");

        System.out.println("+--------------------------------+");
        System.out.println("|           multiply             |");
        System.out.println("+--------------------------------+");
        System.out.println("|  Bits  | Time(ms) | Iterations |");
        System.out.println("+--------------------------------+");

        iterations = 0;
        start = System.currentTimeMillis();
        multiply(4, 5, 4);
        end = System.currentTimeMillis() - start;
        System.out.println(String.format("|%8d|%11d|%11d|", 4, end, iterations));

        iterations = 0;
        start = System.currentTimeMillis();
        multiply(4, 5, 16);
        end = System.currentTimeMillis() - start;
        System.out.println(String.format("|%8d|%11d|%11d|", 16, end, iterations));

        iterations = 0;
        start = System.currentTimeMillis();
        multiply(4, 5, 64);
        end = System.currentTimeMillis() - start;
        System.out.println(String.format("|%8d|%11d|%11d|", 64, end, iterations));
        System.out.println("+--------------------------------+");
    }

    public static int[] generateArray(int length) {
        int[] tmp = new int[length];
        Random random = new Random();
        for (int n = 0; n < length; n++)
            tmp[n] = random.nextInt(length);
        return tmp;
    }

    public static void mergeSort(int[] a, int n) {
        iterations++;
        if (n < 2)
            return;

        int mid = n / 2;

        int[] l = new int[mid];
        for (int i = 0; i < mid; i++)
            l[i] = a[i];

        int[] r = new int[n - mid];
        for (int i = mid; i < n; i++)
            r[i - mid] = a[i];

        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }

    public static void merge(int[] a, int[] l, int[] r, int left, int right) {
        iterations++;

        int i = 0, j = 0, k = 0;
        while (i < left && j < right)
            if (l[i] <= r[j])
                a[k++] = l[i++];
            else
                a[k++] = r[j++];

        while (i < left)
            a[k++] = l[i++];

        while (j < right)
            a[k++] = r[j++];
    }

    public static int maxVal1(int A[], int n) {
        int max = A[0];
        for (int i = 1; i < n; i++) {
            iterations++;
            if (A[i] > max)
                max = A[i];
        }

        return max;
    }

    public static int maxVal2(int A[], int init, int end) {
        iterations++;

        if (end - init <= 1)
            return max(A[init], A[end]);

        else {
            int m = (init + end) / 2;
            int v1 = maxVal2(A, init, m);
            int v2 = maxVal2(A, m + 1, end);
            return max(v1, v2);
        }
    }

    public static int max(int v1, int v2) {
        return v1 > v2 ? v1 : v2;
    }

    public static long multiply(long x, long y, long n) {
        iterations++;

        if (n == 1)
            return x * y;

        else {
            var m = n / 2;
            var a = (long) (x / Math.pow(2, m));
            var b = (long) (x % Math.pow(2, m));
            var c = (long) (y / Math.pow(2, m));
            var d = (long) (y % Math.pow(2, m));

            var e = multiply(a, c, m);
            var f = multiply(b, d, m);
            var g = multiply(b, c, m);
            var h = multiply(a, d, m);
            return (long) (Math.pow(2, (2 * m)) * e + Math.pow(2, m) * (g + h) + f);
        }
    }
}