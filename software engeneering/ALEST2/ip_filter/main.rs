use std::{
    collections::VecDeque,
    fs::File,
    io::{BufRead, BufReader, Lines, Result},
    path::Path,
    time::Instant,
};

const HI: usize = 1;
const LO: usize = 0;
const FILES_PATH: [&str; 13] = [
    "./ip_list_filter_test_cases/casoenunciado.txt",
    "./ip_list_filter_test_cases/cohen01.txt",
    "./ip_list_filter_test_cases/cohen02.txt",
    "./ip_list_filter_test_cases/cohen03.txt",
    "./ip_list_filter_test_cases/cohen04.txt",
    "./ip_list_filter_test_cases/cohen05.txt",
    "./ip_list_filter_test_cases/cohen06.txt",
    "./ip_list_filter_test_cases/cohen07.txt",
    "./ip_list_filter_test_cases/cohen08.txt",
    "./ip_list_filter_test_cases/cohen09.txt",
    "./ip_list_filter_test_cases/cohen10.txt",
    "./ip_list_filter_test_cases/cohen11.txt",
    "./ip_list_filter_test_cases/cohen12.txt",
];
const FILES_NAME: [&str; 13] = [
    "casoenunciado.txt",
    "cohen01.txt",
    "cohen02.txt",
    "cohen03.txt",
    "cohen04.txt",
    "cohen05.txt",
    "cohen06.txt",
    "cohen07.txt",
    "cohen08.txt",
    "cohen09.txt",
    "cohen10.txt",
    "cohen11.txt",
    "cohen12.txt",
];

fn main() {
    let args: Vec<String> = env::args().collect();
    if Path::new(args[0]).is_file() {
        process_single_file(args[0]);
    } else {
        run_all_files(args[0]);
    }
}

fn menu() -> usize {
    show_menu_options();
    let option = read_user_input();
    match option {
        1 => {
            choose_one_file();
        }
        2 => {
            run_all_files();
        }
        3 => {
            println!();
            println!(">>> Bye ^_~");
        }
        _ => {
            println!();
            println!("Opção inválida, tente novamente.");
        }
    }
    option
}

fn show_menu_options() {
    println!();
    println!("1 - Executar um único arquivo.");
    println!("2 - Executar todos os arquivos.");
    println!("3 - Encerrar.");
}

fn choose_one_file() {
    loop {
        show_file_options();
        let chosen_file = read_user_input();
        if chosen_file < 13 {
            process_single_file(chosen_file);
            break;
        }
        println!("Por favor digite um número válido.");
    }
}

fn show_file_options() {
    println!();
    println!("Estes são os arquivos disponiveis:");
    for (index, file_name) in FILES_NAME.iter().enumerate() {
        println!(">>> {} - {}", index, file_name)
    }
}

fn read_user_input() -> usize {
    println!();
    println!("Digite o número da sua opção:");

    let mut input = String::new();
    std::io::stdin().read_line(&mut input).unwrap();
    match input.trim().parse::<usize>() {
        Ok(parsed_string) => parsed_string,
        Err(_) => 13,
    }
}

fn process_single_file(chosen_file: usize) {
    let start = Instant::now();
    let answer = least_possible_list_size(get_sorted_ip_list(file_path));
    let duration = start.elapsed();
    println!();
    println!("Time to process file: {:?}", duration);
    println!(">>> {} => {}", file_path, answer);
}

fn get_sorted_ip_list(file_path: &str) -> Vec<Vec<i32>> {
    let mut ip_list = Vec::<Vec<i32>>::new();
    let file_path = Path::new(file_path);
    for line in read_lines(file_path).unwrap() {
        ip_list.push(
            line.unwrap()
                .split('-')
                .collect::<Vec<&str>>()
                .into_iter()
                .map(|string| string.parse::<i32>().unwrap())
                .collect(),
        );
    }
    ip_list.sort();
    ip_list
}

fn read_lines(filename: &Path) -> Result<Lines<BufReader<File>>> {
    let file = File::open(filename).expect("Error opening file.");
    Ok(BufReader::new(file).lines())
}

fn least_possible_list_size(ip_list: Vec<Vec<i32>>) -> usize {
    let mut blocked_ranges = VecDeque::<Vec<i32>>::new();

    for ip_pair in ip_list {
        if blocked_ranges.is_empty() {
            blocked_ranges.push_back(ip_pair);
        } else if change_higher_value(&ip_pair, &blocked_ranges.back().unwrap()) {
            blocked_ranges.back_mut().unwrap()[HI] = ip_pair[HI];
        } else if !contained(&ip_pair, &blocked_ranges.back().unwrap()) {
            blocked_ranges.push_back(ip_pair);
        }
    }
    blocked_ranges.len()
}

fn change_higher_value(interval_1: &Vec<i32>, interval_2: &Vec<i32>) -> bool {
    return interval_2[LO] < interval_1[LO]
        && interval_1[LO] < interval_2[HI]
        && interval_2[HI] <= interval_1[HI];
}

fn contained(interval_1: &Vec<i32>, interval_2: &Vec<i32>) -> bool {
    return interval_2[LO] < interval_1[LO] && interval_1[HI] < interval_2[HI];
}

fn run_all_files() {
    let start = Instant::now();
    for chosen_file in 0..13 {
        process_single_file(chosen_file);
    }
    let duration = start.elapsed();
    println!();
    println!("Time to process all files: {:?}", duration);
}
