import os
from fileinput import filename
import matplotlib.pyplot as plt
import json
import numpy as np

input_path = '../output'

strategies = os.listdir(input_path)
if 'plots' in strategies: strategies.remove('plots')
results = {}
l = {}


def jprint(d):
    print(json.dumps(d, indent=4))


for strategy in strategies:
    l[strategy] = []
    max_depth = 0
    for f in os.listdir(f'{input_path}/{strategy}'):
        if max_depth < int(f.split('_')[2]):
            max_depth = int(f.split('_')[2])
    for i in range(1, max_depth + 1):
        l[strategy].append(f'{i:02d}')

for strategy in strategies:
    results[strategy] = {}
    for level in l[strategy]:
        results[strategy][level] = {}
    for f in os.listdir(f'{input_path}/{strategy}'):
        with open(f'{input_path}/{strategy}/{f}') as file:
            filename_array = f[:-4].split("_")
            results[strategy][filename_array[2]][filename_array[-1]] = {
                'solution_length': int(file.readline()),
                'number_of_visited_states': int(file.readline()),
                'number_of_processed_states': int(file.readline()),
                'max_recursion_depth': int(file.readline()),
                'processing_time': float(file.readline().replace(",", ".")),
            }
    print(f'wczytano {strategy}')

jprint(results)
colors = ['#1368aa', '#4091c9', '#9dcee2', '#fedfd4', '#f29479', '#f26a4f', '#ef3c2d', '#cb1b16']

if not os.path.exists(f'{input_path}/plots'): os.mkdir(f'{input_path}/plots')
for strategy, strategyValues in results.items():
    levels = list(results[strategy].keys())
    strategy_args = list(results[strategy][levels[0]].keys())
    parameters = list(results[strategy][levels[0]][strategy_args[0]].keys())
    for parameter in parameters:
        labels = levels
        values = {}
        for arg in strategy_args:
            values[arg] = []
            for level in levels:
                values[arg].append(results[strategy][level][arg][parameter])
        # jprint(values)
        number_of_bars = len(list(values.keys()))
        group_width = .7
        width = group_width / number_of_bars
        x = np.arange(len(labels))
        start = 0 - (number_of_bars * width / 2) + width / 2
        bars = []
        fig, ax = plt.subplots(figsize=(13, 7))
        for i in range(len(values)):
            bars.append(ax.bar(
                x + start,
                values[list(values.keys())[i]],
                width,
                label=list(values.keys())[i],
                color=colors[i]
            ))
            start += width
        ax.set_ylabel(parameter)
        ax.set_xticks(x)
        int_labels = []
        for label in labels:
            int_labels.append(str(int(label)))
        ax.set_xticklabels(int_labels)
        ax.legend(loc='best')
        title = parameter.replace('_', ' ').capitalize()
        ax.set_title(title, size=20)
        ax.grid(True, 'both', 'y')
        ax.set_axisbelow(True)

        # for bar in bars:
        #     ax.bar_label(bar, padding=.5)

        fig.tight_layout()
        plt.savefig(f'{input_path}/plots/{strategy}_{parameter}.svg')
        # plt.show()
        plt.clf()

with open(f'{input_path}/plots/results.json', 'w+') as f:
    json.dump(results, f, ensure_ascii=False, indent=4)