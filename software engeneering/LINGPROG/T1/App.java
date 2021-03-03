import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * OBS.: Necessário Java 11 Grupo 7 Autores: Jessica Rodrigues Freua; Jonatas
 * Van Groll Lemos; Kevin Boucinha Fiorentin; Marcelo Bernardy de Azevedo.
 */

public class App {
    private static byte[] memory = new byte[1000];
    private static ArrayList<Character> source = new ArrayList<>();
    private static ArrayList<Integer> ife = new ArrayList<>();
    private static ArrayList<Byte> of = new ArrayList<>();
    private static int data_pointer = 0; // memoria
    private static int program_counter = 0;// source
    private static ArrayDeque<Integer> stack = new ArrayDeque<>();

    public static void main(String[] args) {
        try {
            readFile(source, ife);

            while (program_counter != source.size()) {
                switch (source.get(program_counter)) {
                    case '<':
                        data_pointer--;
                        program_counter++;
                        break;
                    case '>':
                        data_pointer++;
                        program_counter++;
                        break;
                    case '[':
                        if (memory[data_pointer] == 0) {
                            program_counter = stack.pop() + 1;
                        } else {
                            stack.push(program_counter);
                            program_counter++;
                        }
                        break;
                    case ']':
                        if (memory[data_pointer] != 0) {
                            program_counter = stack.pop();
                            stack.push(program_counter);
                        } else {
                            program_counter++;
                        }
                        break;
                    case '$':
                        escreve_no_of(of);
                        program_counter++;
                        break;
                    case '+':
                        memory[data_pointer]++;
                        program_counter++;
                        break;
                    case '-':
                        memory[data_pointer]--;
                        program_counter++;
                        break;
                    case ',':
                        memory[data_pointer] = ife.remove(0).byteValue();
                        program_counter++;
                        break;
                    case '.':
                        of.add(memory[data_pointer]);
                        program_counter++;
                        break;
                    default:
                        program_counter++;
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void readFile(ArrayList<Character> source, ArrayList<Integer> ife) throws IOException {
        final String source_path = "./SOURCE";
        final String if_path = "./IF";
        try {

            // read source
            FileReader file = new FileReader(source_path);
            BufferedReader readFile = new BufferedReader(file);
            int c = 0;

            while ((c = readFile.read()) != -1) {
                source.add((char) c);
            }

            file.close();

            // read ife
            file = new FileReader(if_path);
            readFile = new BufferedReader(file);
            String line = readFile.readLine();
            while (line != null) {
                ife.add(Integer.parseInt(line));
            }

            file.close();

        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

    }

    public static void escreve_no_of(final ArrayList<Byte> of) {
        try (var bw = new BufferedWriter(new FileWriter("./OF"))) {
            for (Byte c : of) {
                bw.write((int) c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}