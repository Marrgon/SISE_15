import os

a = ['01', '02', '03', '04', '05', '06', '07']
b = ['rdul', 'rdlu', 'drul', 'drlu', 'ludr', 'lurd', 'uldr', 'ulrd']
c = ['hamm', 'manh']

files = os.listdir('../puzzles')

for bs in c:
    for f in files:
        with open(f'../output/astr/solution_{f[:-4]}_astr_{bs}.txt', 'w') as newFile:
            with open('G:/Downloads/bfs_DRLU_4x4_02_00002.txt', 'r') as original:
                for line in original:
                    # print(line)
                    newFile.write(line)