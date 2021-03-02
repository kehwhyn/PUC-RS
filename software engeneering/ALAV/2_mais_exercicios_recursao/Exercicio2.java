import java.util.ArrayList;

class Exercicio2 {
    public static void main(String[] args) {
    }

    // int fatorial(int n)
    // Ex: n = 1 -> 1; n = 0 -> 1; n = 3 -> 6;
    // Exceção: n<0
    // Base: n == 1, n == 0
    // Recursão: fatorial(n-1) * n
    public static int fatorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        } else if (n == 0 || n == 1) {
            return 1;
        }
        return fatorial(n - 1) * n;
    }

    // int somatorio(int n)
    // Ex: n = 2 -> 3
    // Exceção: n < 0
    // Base: n == 1
    // Recursão: somatorio(n - 1) + 1
    public static int somatorio(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        if (n == 0) {
            return 0;
        }
        return somatorio(n - 1) + 1;
    }

    // int fibonacci(int n)
    // Ex: n = 0 -> 0; n = 1 -> 1; n = 4 -> 2;
    // Exceção: n<0
    // Base: n == 1, n == 0
    // Recursão: fibonacci(n-1) + fibonacci(n-2)
    public static int fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        } else if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // int somatorio_intervalado(int k, int j)
    // Ex: k = 5, j = 8 -> 13
    // Exceção: j < k
    // Base: k == j - 1
    // Recursão: somatorio_intervalado(k, j - 1) + (j - 1);
    public static int somatorio_intervalado(int menor, int maior) {
        if (maior < menor) {
            throw new IllegalArgumentException();
        }
        if (menor == maior - 1) {
            return 0;
        }
        return somatorio_intervalado(menor, maior - 1) + (maior - 1);
    }

    // Boolean isPal(String s)
    // Ex: s = 'ARARA' -> true, s = 'TEEE' -> false
    // Exceção: s = "";
    // Base: s.charAt(0) != s.charAt(s.length()-1) ou s.length() == 1
    // Recursão: isPal(s.substring(1,s.length() - 1)) && true;
    public static Boolean isPal(String s) {
        if (s.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (s.charAt(0) != s.charAt(s.length() - 1)) {
            return false;
        } else if (s.length() == 1) {
            return true;
        }
        return isPal(s.substring(1, s.length() - 1)) && true;
    }

    // string convBase2(int num)
    // Ex: num = 3 -> 0011; num = 4 -> 0100
    // Exceção: num < 0
    // Base: num == 0 -> "0000"; num -> "0001"
    // Recursão: convBase2(k, j - 1) + (j - 1);
    public static String convBase2(int num) {
        if (num < 0) {
            throw new IllegalArgumentException();
        }
        if (num == 0) {
            return "";
        }
        return convBase2(num / 2) + Integer.toString(num % 2);
    }

    // int soma_array(ArrayList<Integer> arr)
    // Ex: arr = [1,1,1] -> 3; arr = [3,9,2] -> 14
    // Exceção: arr.isEmpty() -> 0
    // Base: arr.size() == 1
    // Recursão: arr.remove(0) + soma_array(arr);
    public static int soma_array(ArrayList<Integer> arr) {
        if (arr.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (arr.size() == 1) {
            return arr.remove(0);
        }
        return arr.remove(0) + soma_array(arr);
    }
}