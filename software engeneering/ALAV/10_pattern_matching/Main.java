class Main {
    private static int iterations = 0;
    private static int instructions = 0;

    public static void main(String[] args) {
        var pat1 = "ABCDCBDCBDACBDABDCBADF";
        var pat2 = stringGenerator(600_000);
        var txt = "ADF";

        System.out.println("+----------------------------------------------------------+");
        System.out.println("|                     Pattern Matching                     |");
        System.out.println("+----------------------------------------------------------+");
        System.out.println("| 1st Occurence | #Instructions | #Iterations | Complexity |");
        System.out.println("+----------------------------------------------------------+");

        var aux = patternMatching(pat1, txt);
        System.out.println(String.format("|%15d|%15d|%13d|%12s|", aux, instructions, iterations, "O(m * n)"));

        instructions = 0;
        iterations = 0;
        aux = patternMatching(pat2, txt);
        System.out.println(String.format("|%15d|%15d|%13d|%12s|", aux, instructions, iterations, "O(m * n)"));
        System.out.println("+----------------------------------------------------------+\n");

        System.out.println("+----------------------------------------------------------+");
        System.out.println("|                        Rabin-Karp                        |");
        System.out.println("+----------------------------------------------------------+");
        System.out.println("| 1st Occurence | #Instructions | #Iterations | Complexity |");
        System.out.println("+----------------------------------------------------------+");

        instructions = 0;
        iterations = 0;
        aux = rabinKarp(pat1, txt);
        System.out.println(String.format("|%15d|%15d|%13d|%12s|", aux, instructions, iterations, "O(m + n)"));

        instructions = 0;
        iterations = 0;
        aux = rabinKarp(pat2, txt);
        System.out.println(String.format("|%15d|%15d|%13d|%12s|", aux, instructions, iterations, "O(m + n)"));
        System.out.println("+----------------------------------------------------------+\n");

        System.out.println("+----------------------------------------------------------+");
        System.out.println("|                    Knuth-Morris-Pratt                    |");
        System.out.println("+----------------------------------------------------------+");
        System.out.println("| 1st Occurence | #Instructions | #Iterations | Complexity |");
        System.out.println("+----------------------------------------------------------+");

        instructions = 0;
        iterations = 0;
        KMPSearch(pat1, txt);
        System.out.println(String.format("|%15d|%15d|%13d|%12s|", aux, instructions, iterations, "O(n)"));

        instructions = 0;
        iterations = 0;
        KMPSearch(pat2, txt);
        System.out.println(String.format("|%15d|%15d|%13d|%12s|", aux, instructions, iterations, "O(n)"));
        System.out.println("+----------------------------------------------------------+\n");
    }

    private static int patternMatching(String pat, String txt) {
        for (int i = 0; i <= pat.length() - txt.length(); i++) {
            iterations++;
            var aux = new String();
            incInstructions(1);

            for (int j = 0; j < txt.length(); j++) {
                iterations++;
                aux += pat.charAt(i + j);
                incInstructions(3);
            }
            incInstructions(1);
            if (aux.equals(txt))
                return i;
        }
        return txt.length();
    }

    private static int rabinKarp(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();
        long patHash = hash(pat, M);

        for (int i = 0; i <= N - M; i++) {
            iterations++;
            long txtHash = hash(txt.substring(i, i + M), M);
            incInstructions(2);
            if (patHash == txtHash) {
                incInstructions(1);
                return i;
            }
        }
        incInstructions(1);
        return N;
    }

    private static long hash(String s, int M) {
        long h = 0;
        long Q = 2_147_483_647;
        long R = 26;
        for (int j = 0; j < M; j++) {
            iterations++;
            h = (h * R + s.charAt(j)) % Q;
            incInstructions(4);
        }
        incInstructions(1);
        return h;
    }

    private static void KMPSearch(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();
        int lps[] = new int[M];
        int j = 0;
        incInstructions(8);

        computeLPSArray(pat, M, lps);
        incInstructions(1);

        int i = 0;
        incInstructions(2);

        while (i < N) {
            iterations++;
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
                incInstructions(5);
            }
            if (j == M) {
                System.out.println("Found pattern " + "at index " + (i - j));
                j = lps[j - 1];
                incInstructions(3);

            } else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                    incInstructions(3);
                } else {
                    i++;
                    incInstructions(2);
                }
                incInstructions(1);
            }
        }
    }

    private static void computeLPSArray(String pat, int M, int lps[]) {
        int len = 0;
        int i = 1;
        lps[0] = 0;
        incInstructions(3);

        while (i < M) {
            iterations++;
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
                incInstructions(6);

            } else {
                if (len != 0) {
                    len = lps[len - 1];
                    incInstructions(3);
                } else {
                    lps[i++] = len;
                    incInstructions(4);
                }
                incInstructions(1);
            }
        }
    }

    // auxiliary functions
    private static String stringGenerator(int length) {
        var alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        var generatedString = new StringBuilder(length);
        while (length-- != 0) {
            var index = (int) (alphabet.length() * Math.random());
            generatedString.append(alphabet.charAt(index));
        }
        return generatedString.toString();
    }

    private static void incInstructions(int i) {
        instructions += i;
    }
}