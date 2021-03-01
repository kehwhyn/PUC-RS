require 'pathname'
require 'benchmark'

LO = 0
HI = 1

def main()
    option = ARGV[0]
    if File.file?(option)
        process_single_file(option)
    else
        run_all_files(option)
    end
end

def process_single_file(file_path)
    puts Benchmark.measure {
        answer = least_possible_list_size(
            get_sorted_ip_list(file_path)
        )
        print ">>> #{File.basename(file_path)} => #{answer}\n"
        print "\tuser     system      total        real\n"
    }
end

def least_possible_list_size(ip_list)
    blocked_ips = [ip_list.shift]

    while not ip_list.empty?
        ip_pair = ip_list.shift
        if change_higher_value(ip_pair, blocked_ips.last)
            blocked_ips.last[HI] = ip_pair[HI]         
        
        elsif not is_contained(ip_pair, blocked_ips.last)
            blocked_ips << ip_pair
        end
    end
    
    return blocked_ips.size
end

def get_sorted_ip_list(file_path)
    aux = []
    File.open(file_path).each do |line|
        aux << line.strip.split("-").map do
            |str| str.to_i           
        end
    end
    return aux.sort
end

def change_higher_value(interval_1, interval_2)
    return interval_2[LO] < interval_1[LO] \
        && interval_1[LO] < interval_2[HI] \
        && interval_2[HI] <= interval_1[HI]
end

def is_contained(interval_1, interval_2)
    return interval_2[LO] < interval_1[LO] \
        && interval_1[HI] < interval_2[HI]
end

def run_all_files(directory)
    puts Benchmark.measure {
        Pathname.new(directory).children.sort.each do |file_path|
            process_single_file(file_path)
        end
        print "All files executed in:\n"
        print "\tuser     system      total        real\n"
    }
end

main()
