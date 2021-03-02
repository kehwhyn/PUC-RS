import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Grupo 7
 * Autores: 
 * Jessica Rodrigues Freua;
 * Jonatas Van Groll Lemos; 
 * Kevin Boucinha Fiorentin;
 * Marcelo Bernardy de Azevedo.
 */

public class App {
    private static byte[] memory = new byte[1000];
    private static int data_pointer = 0;
    private static final char[] source = read_source();
    private static int program_counter = 0;
    private static final int[] ife = read_if();
    private static int if_counter = 0;
    private static ArrayList<Byte> of = new ArrayList<>();
    private static ArrayDeque<Integer> stack = new ArrayDeque<>();

    public static void main(String[] args) {
        while(program_counter != source.length) {
            switch(source[program_counter]) {
                case '<':
                    data_pointer--;
                    program_counter++;
                    break;
                case '>':
                    data_pointer++;
                    program_counter++;
                    break;
                case '[':
                    if (memory[data_pointer] == 0)
                        program_counter = stack.pop() + 1;
                    else {
                        stack.push(program_counter);
                        program_counter++;
                    }
                    break;
                case ']':
                    if (memory[data_pointer] != 0) {
                        program_counter = stack.pop();
                        stack.push(program_counter);
                    } else
                        program_counter++;
                    break;
                case '$':
                    write_of(of);
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
                    memory[data_pointer] = (byte) ife[if_counter++];
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
    }

    public static char[] read_source() {
        char[] tmp = {};
        try (final var br = new BufferedReader(new FileReader("./SOURCE"))) {
            tmp = br.lines().reduce("", String::concat).toCharArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public static int[] read_if() {
        int[] tmp = {};
        try (final var br = new BufferedReader(new FileReader("./IF"))) {
            tmp = br.lines().mapToInt(Integer::parseInt).toArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public static void write_of(final ArrayList<Byte> of) {
        try (final var bw = new BufferedWriter(new FileWriter("./OF"))) {
            for (Byte c : of) bw.write(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
