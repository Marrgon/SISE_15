import os
import sys
import time

puzzles_path = '../puzzles'
output_path = '../output'
program_path = '../pietnastka.jar'
strategies = ['bfs', 'dfs', 'astr']
strategyArgs1 = ['RDUL', 'RDLU', 'DRUL', 'DRLU', 'LUDR', 'LURD', 'ULDR', 'ULRD']
strategyArgs2 = ['hamm', 'manh']

files_input = os.listdir(puzzles_path)

for strategy in strategies:
    print(strategy)
    print('0%', end='')
    finished = 0
    if not strategy == 'astr':
        strategyArgs = strategyArgs1
    else:
        strategyArgs = strategyArgs2
    all_elements = len(strategyArgs) * len(files_input)
    one = 100 / all_elements
    for file in files_input:
        for argument in strategyArgs:
            solution_path = f'{output_path}/{strategy}/solution_{file[:-4]}_{strategy}_{argument}.txt'
            stats_path = f'{output_path}/{strategy}/stats_{file[:-4]}_{strategy}_{argument}.txt'
            os.system(f'java -jar {program_path} {strategy} {argument} {solution_path} {stats_path}')
            finished += one
            # time.sleep(.01)
        sys.stdout.write('\b\b\b\b\b\b\b\b\b\b\b\b')

        sys.stdout.write(f'{finished:.2f}%')
    sys.stdout.write('\b\b\b\b\b\b\b\b\b\b\b\b100%')
    print('\n')
