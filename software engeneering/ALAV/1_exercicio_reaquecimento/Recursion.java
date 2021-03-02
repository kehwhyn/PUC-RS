package br.pucrs.alav;

public class Recursion {
    public static int somaRecursiva(int num1, int num2) {
        if (num1 == 0) {
            return 0;
        }
        return somaRecursiva(num1 - 1, num2) + num2;
    }

    public static int incrementoSucessivoRecursivo(int num1, int num2) {
        if (num1 != 0) {
            return incrementoSucessivoRecursivo(num1 - 1, num2) + 1;
        } else if (num2 != 0) {
            return incrementoSucessivoRecursivo(num1, num2 - 1) + 1;
        }

        return 0;
    }

    public static double somaDivisoresRecursivo(int n) {
        if (n == 0) {
            return 0;
        }
        return somaDivisoresRecursivo(n - 1) + 1.0 / n;
    }

    public static String inversaoStringRecursivo(String str) {
        if (str.isEmpty())
            return str;
        return inversaoStringRecursivo(str.substring(1)) + str.charAt(0);
    }

    public static int geradorDeSequenciaRecursivo(int num) {
        if (num < 2) {
            return num;
        }
        return num * geradorDeSequenciaRecursivo(num - 1) + (num + 1) * geradorDeSequenciaRecursivo(num - 2);
    }

    public static int sequenciaDeAckerman(int m, int n) {
        if (m == 0) {
            return n + 1;
        } else if (m != 0 && n == 0) {
            return sequenciaDeAckerman(m - 1, 1);
        }
        return sequenciaDeAckerman(m - 1, sequenciaDeAckerman(m, n - 1));
    }

    public static int somaNumerosVetorRecursivo(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }

        int[] aux = new int[arr.length - 1];

        for (int i = 0; i < arr.length - 1; i++) {
            aux[i] = arr[i];
        }

        return somaNumerosVetorRecursivo(aux) + arr[arr.length - 1];
    }

    public static int produtoNumerosVetorRecursivo(int[] arr) {
        if (arr.length == 0) {
            return 1;
        }

        int[] aux = new int[arr.length - 1];

        for (int i = 0; i < arr.length - 1; i++) {
            aux[i] = arr[i];
        }

        return produtoNumerosVetorRecursivo(aux) * arr[arr.length - 1];
    }
}
