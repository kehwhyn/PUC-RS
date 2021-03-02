use std::{
    collections::VecDeque,
    fs::File,
    io::{BufRead, BufReader, Lines, Result},
    path::PathBuf,
};
use structopt::StructOpt;

/// Search for a pattern in a file and display the lines that contain it.
#[derive(Debug, StructOpt)]
#[structopt(
    name = "main",
    about = "filters an IP filter from a file or directory."
)]
struct Cli {
    /// The path to the file to read
    #[structopt(short, long, help = "path to file or directory", parse(from_os_str))]
    input: PathBuf,
}

fn main() {
    let args = Cli::from_args();
    if args.input.is_file() {
        exec_time(process_one_file, &args.input);
    } else {
        exec_time(process_all_files, &args.input);
    }
}

fn exec_time(f: fn(&PathBuf), file_or_dir: &PathBuf) {
    let start = std::time::Instant::now();
    f(file_or_dir);
    let duration = start.elapsed();
    println!("Time to process one/all file(s): {:?}", duration);
}

fn process_one_file(chosen_file: &PathBuf) {
    let file_name = chosen_file.file_name().unwrap();
    let (mut maze, hero, villain, maze_size) = map_maze(chosen_file);
    let answer = shortest_path(&mut maze, maze_size, hero, villain);
    println!();
    println!(">>> {:?} => {}", file_name, answer);
}

fn map_maze(file_path: &PathBuf) -> (Vec<(char, usize)>, usize, usize, usize) {
    let mut maze = Vec::<(char, usize)>::new();
    let mut hero: usize = 0;
    let mut villain: usize = 0;
    for (index, character) in read_lines(file_path)
        .unwrap()
        .flat_map(|line| line.unwrap().chars().collect::<Vec<char>>())
        .enumerate()
    {
        maze.push((character, 0));
        if character == 'A' {
            hero = index;
        }
        if character == 'B' {
            villain = index;
        }
    }
    let maze_size: usize = ((maze.len() + 1) as f64).sqrt() as usize;
    (maze, hero, villain, maze_size)
}

fn read_lines(filename: &PathBuf) -> Result<Lines<BufReader<File>>> {
    let file = File::open(filename).expect("Error opening file.");
    Ok(BufReader::new(file).lines())
}

fn shortest_path(
    maze: &mut Vec<(char, usize)>,
    offset: usize,
    source: usize,
    destiny: usize,
) -> usize {
    let mut queue = VecDeque::new();
    queue.push_back(source);
    let mut aux: usize = 0;
    while aux != destiny {
        let curr_pos = queue.pop_front().unwrap();
        for next_pos in vec![
            curr_pos - offset,
            curr_pos - 1,
            curr_pos + offset,
            curr_pos + 1,
        ] {
            if maze[next_pos].0 != '#' && maze[next_pos].1 == 0 {
                maze[next_pos].1 = curr_pos + 1;
                queue.push_back(next_pos);
            }
            aux = next_pos;
        }
    }
    maze[destiny].1
}

fn process_all_files(_tmp: &PathBuf) {
    println!("This might take a while...");
    for _chosen_file in 0..=7 {
        //process_one_file(chosen_file);
    }
}
