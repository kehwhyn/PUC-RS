import os
import times
import deques
import strutils
import sequtils
import algorithm

const LO = 0
const HI = 1

template benchmark(benchmarkName: string, code: untyped) =
  block:
    let t0 = epochTime()
    code
    let elapsed = int((epochTime() - t0) * 1000)
    echo "CPU Time [", benchmarkName, "] ", elapsed, " ms", "\n"

proc change_higher_value(interval_1: seq[int], interval_2: seq[int]): bool =
    return interval_2[LO] < interval_1[LO] and
           interval_1[LO] < interval_2[HI] and
           interval_2[HI] <= interval_1[HI]

proc is_contained(interval_1: seq[int], interval_2: seq[int]): bool =
    return interval_2[LO] < interval_1[LO] and
           interval_1[HI] < interval_2[HI]

proc get_ip_list(file_path: string): seq[seq[int]] =
    let file = open(file_path)
    defer: file.close()
    return file.readAll()
        .strip(chars = {'\n'})
        .split()
        .mapIt(
            it.split("-")
            .map(parseInt)
        )

proc least_possible_list_size(ip_list: seq[seq[int]]): int =
    var blocked_ranges = initDeque[seq[int]]()
    blocked_ranges.addLast(ip_list[0])

    for ip_pair in ip_list:
        let last_item = blocked_ranges.peekLast()

        if change_higher_value(ip_pair, last_item):
            var aux = blocked_ranges.popLast()
            aux[HI] = ip_pair[HI]
            blocked_ranges.addLast(aux)
        
        elif not is_contained(ip_pair, last_item):
            blocked_ranges.addLast(ip_pair)

    return blocked_ranges.len - 1

proc process_single_file(file_path: string) =
    let file_name = splitPath(file_path).tail
    benchmark(file_name):
        let answer = least_possible_list_size(
            get_ip_list(file_path)
            .sortedByIt(it[LO])
        )
        echo ">>> ", file_name, " => ", answer

proc run_all_files(directory: string) =
    benchmark("all files"):
        for _, file_path in walkDir(directory):
            process_single_file(file_path)

proc main() =
    let option = commandLineParams()[0]
    if fileExists(option):
        process_single_file(option)
    else:
        run_all_files(option)
   
main()