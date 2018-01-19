import sys
import matplotlib.pyplot as plt
from matplotlib.widgets import Button
import random
import math

fig, ax = plt.subplots(figsize=(20, 10))
colors = [
    ['#ff19c1', '#ff19c1', 'black'],
    ['#18ff4a', '#18ff4a', 'black'],
    ['#0c75ff', '#0c75ff', 'black'],
    ['#ffaf47', '#ffaf47', 'black'],
    ['k', 'k', 'black'],
    ['b', 'b', 'black'],
    ['w', 'w', 'black'],
]

msize = 16

vx = []
vy = []
vID = []
vtype = []

def getDistance(xarr, yarr):
    distance = 0
    px = -500
    py = -500
    for x, y in zip(xarr, yarr):
        if px > -500 and py > -500:
            dx = x - px
            dy = y - py
            distance += math.sqrt(dx*dx + dy*dy)
        px = x
        py = y
    return distance

vehicleData = sys.argv[1:]
numVeh = len(vehicleData)
for vehicle in vehicleData:
    arr = vehicle.split("_")
    n = len(arr) / 4
    vx.append([int(i) for i in arr[0:n]])
    vy.append([int(i) for i in arr[n:2*n]])
    vID.append(arr[2*n:3*n])
    vtype.append([int(i) for i in arr[3*n:4*n]])

def redraw(event, index):
    ax.clear()
    for i in range(numVeh):
        x = vx[i]
        y = vy[i]
        ID = vID[i]
        t = vtype[i]
        opacity = int(i == index) * 0.8 + 0.2

        ax.plot(x, y, '-', color=colors[i][1], markersize=msize, linewidth=3.0, alpha=opacity)
        for j in range(len(x)):
            cx = x[j]
            cy = y[j]
            cID = ID[j]
            ct = t[j]

            if ct == 1:
                ax.plot(cx, cy, 'ro', markersize=msize, alpha=opacity)
            elif ct == 2:
                ax.plot(cx + random.randint(-100, 100), cy + random.randint(-100, 100), '*', color=colors[i][1], markersize=msize*2, alpha=opacity, markeredgecolor='black')
            else:
                ax.plot(cx, cy, 'go', markersize=msize, alpha=opacity)

            ax.text(cx-0.15, cy-0.15, cID, color=colors[i][2], alpha=opacity)
        if i == index:
            distance = getDistance(x, y)
            numPackages = len(x)
            DPH = numPackages / ((distance / 10800) + (numPackages * 10)/60)
            ax.text(-800, -200, 'Distance (meters) = %.2f' % distance, fontsize=12)
            ax.text(-800, -675, "Packages Delivered = %d" % numPackages, fontsize=12)
            ax.text(-800, -1500, "DPH = %.3f" % DPH, fontsize=24)
        ax.margins(0.2, 0.2)
    plt.show()

def redrawGenerator(index):
    return lambda event: redraw(event, index)

axes = []
buttons = []
for i in range(numVeh):
    axes.append(plt.axes([0.15+0.1*i, 0.05, 0.06, 0.05]))
    buttons.append(Button(axes[i], "Vehicle " + str(i+1)))
    buttons[i].on_clicked(redrawGenerator(i))
#
# axes.append(plt.axes([0.05+0.15*0, 0.05, 0.1, 0.05]))
# axes.append(plt.axes([0.05+0.15*1, 0.05, 0.1, 0.05]))
# axes.append(plt.axes([0.05+0.15*2, 0.05, 0.1, 0.05]))
# axes.append(plt.axes([0.05+0.15*3, 0.05, 0.1, 0.05]))
# btn1 = Button(axes[0], "Vehicle " + str(1))
# btn2 = Button(axes[1], "Vehicle " + str(2))
# btn3 = Button(axes[2], "Vehicle " + str(3))
# btn4 = Button(axes[3], "Vehicle " + str(4))
# btn1.on_clicked(redraw)
# btn2.on_clicked(redraw)
# btn3.on_clicked(redraw)
# btn4.on_clicked(redraw)

#plt.gca().set_aspect('equal')
plt.show()
