import static java.lang.Math.min;

public class DistanciaEdicao {
    public static int iterations;
    public static void main(String[] args) {

        String S = "Casablanca";
        String T = "Portentoso";

        var response = ED(S, T, S.length(), T.length());

        System.out.println("Sem prog dinamica: " + response + " iterações: " + iterations);
        
        String S2 = "Maven, a Yiddish word meaning accumulator of knowledge, began as an attempt to " +
        "simplify the build processes in the Jakarta Turbine project. There were several" + 
        " projects, each with their own Ant build files, that were all slightly different." +
        "JARs were checked into CVS. We wanted a standard way to build the projects, a clear "+ 
        "definition of what the project consisted of, an easy way to publish project information" +
        "and a way to share JARs across several projects. The result is a tool that can now be" +
        "used for building and managing any Java-based project. We hope that we have created " +
        "something that will make the day-to-day work of Java developers easier and generally help " +
        "with the comprehension of any Java-based project.";

        String T2 = "This post is not about deep learning. But it could be might as well. This is the power of " +
        "kernels. They are universally applicable in any machine learning algorithm. Why you might" +
        "ask? I am going to try to answer this question in this article." + 
        "Go to the profile of Marin Vlastelica Pogančić" + 
        "Marin Vlastelica Pogančić Jun";

        iterations = 0;
        response = distEdProgDina(S2, T2);

        System.out.println("Com prog dinâmica: " + response + " iterações: " + iterations);

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

        return min(min(sub, ins), rem);
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
                matriz[i][j] = min(
                    min(matriz[i - 1][j] + 1, matriz[i][j - 1] + 1), 
                    matriz[i - 1][j - 1] + custoExtra);
            }

        return matriz[m][n];
    }
}