import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.io.IOException;

class Classe {
    final static int LO = 0;
    final static int HI = 1;

    public static void main(final String[] args) {
        var option = new File(args[0]);
        if (option.isFile()) {
            process_single_file(option);
        } else {
            run_all_files(option);
        }
    }

    public static void process_single_file(final File file_path) {
        long start = System.currentTimeMillis();
        var answer = least_possible_list_size( 
            get_sorted_ip_list(file_path)
        );
        long time = System.currentTimeMillis() - start;
        var file_name = file_path.getName();
        System.out.println(
            ">>> " + file_name + " => " + answer
        );
        System.out.println(
            "[" + file_name + "] executed in: " + time + " ms"
        );
        System.out.println();
    }

    public static int least_possible_list_size(
        ArrayDeque<int[]> ip_list
    ) {
        var blocked_ranges = new ArrayDeque<int[]>();
        blocked_ranges.push(ip_list.poll());

        while(!ip_list.isEmpty()) {
            final var ip_pair = ip_list.poll();
            if (
                change_higher_value(
                    ip_pair, 
                    blocked_ranges.peek()
                )
            ) {
                blocked_ranges.peek()[HI] = ip_pair[HI];
            
            } else if (
                !contained(
                    ip_pair, 
                    blocked_ranges.peek()
                )
            ) {
                blocked_ranges.push(ip_pair);
            }
        }

        return blocked_ranges.size();
    }

    public static ArrayDeque<int[]> get_sorted_ip_list(
        final File file_path
    ) {
        var tmp = new ArrayList<int[]>();
        try (
            final var fr = new FileReader(file_path);
            final var br = new BufferedReader(fr)
        ) {
            var line = new String();
            while ((line = br.readLine()) != null) {
                final var aux = line.split("-");
                final int[] ip_pair = {
                    Integer.parseInt(aux[LO]),
                    Integer.parseInt(aux[HI])
                };
                tmp.add(ip_pair);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        tmp.sort(
            (ip_pair1, ip_pair2) -> ip_pair1[LO] - ip_pair2[LO]
        );
        return new ArrayDeque<int[]>(tmp);
    }

    public static boolean change_higher_value(
        final int[] interval_1,
        final int[] interval_2
    ) {
        return interval_2[LO] < interval_1[LO] 
            && interval_1[LO] < interval_2[HI]
            && interval_2[HI] <= interval_1[HI];
    }

    public static boolean contained(
        final int[] interval_1, 
        final int[] interval_2
    ) {
        return interval_2[LO] < interval_1[LO] 
            && interval_1[HI] < interval_2[HI];
    }

    public static void run_all_files(final File directory) {
        long start = System.currentTimeMillis();
        for (var file_path : directory.listFiles()) {
            process_single_file(file_path);
        }
        long time = System.currentTimeMillis() - start;
        System.out.println(
            "[All files] executed in: " + time + " ms"
        );
    }
}
