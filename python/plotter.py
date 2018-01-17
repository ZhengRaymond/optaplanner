import sys
import matplotlib.pyplot as plt

fig, ax = plt.subplots(figsize=(20, 10))
colors = [
    ['r', 'r', 'black'],
    ['g', 'g', 'black'],
    ['r', 'r', 'black'],
    ['c', 'c', 'black'],
    ['m', 'm', 'black'],
    ['b', 'b', 'black'],
    ['k', 'k', 'black'],
]
index = 0
for vehicle in sys.argv[1:]:
    arr = vehicle.split("_")
    n = len(arr) / 4
    if n < 2:
        continue
    x = [int(i) for i in arr[0:n]]
    y = [int(i) for i in arr[n:2*n]]
    id = arr[2*n:3*n]
    isSource =[int(i) for i in arr[3*n:4*n]]

    msize = 16

    ax.plot(x, y, '-'+colors[index][1], markersize=msize)

    ind = 0
    for i, j in zip(x, y):
        if isSource[ind] == 1:
            ax.plot([i], [j], 'co', markersize=msize)
        elif isSource[ind] == 2:
            ax.plot([i], [j], 'rs', markersize=msize*2)
        else:
            ax.plot([i], [j], 'mo', markersize=msize)
        ind += 1

    ind = 0
    for i, j in zip(x, y):
        corr = -0.15
        ax.text(i + corr, j + corr, id[ind], color=colors[index][2])
        ind += 1

    ax.margins(0.2, 0.2)
    index += 1

plt.gca().set_aspect('equal')
plt.show()
