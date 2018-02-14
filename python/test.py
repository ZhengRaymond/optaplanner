
def calculateDistance(fromX, fromY, toX, toY):
    return abs(fromX - toX) + abs(fromY - toY)

def getTransportTime(fromX, fromY, toX, toY, vehicleType):
    if vehicleType == "Truck":
        veloFrom = min(2 + 0.0004 * max(0, toY - 2000), 6)
        veloTo = min(2 + 0.0004 * max(0, toY - 2000), 6)

        xTime = abs(fromX - toX) / ((veloTo+ veloFrom) / 2)
        yTime = abs(fromY / veloFrom - toY / veloTo)
        return xTime + yTime
    return calculateDistance(fromX, fromY, toX, toY) / 4

# bike should be 1020 seconds
# vehicle should be 1440 seconds

# bike should be 960 seconds
# vehicle should be 1260 seconds

print(getTransportTime(1000, 0, 1500, 3000, "Bicycle"))
print(getTransportTime(1000, 0, 1500, 3000, "Truck"))
print("")
print(getTransportTime(1000, 0, 1000, 3000, "Bicycle"))
print(getTransportTime(1000, 0, 1000, 3000, "Truck"))
print("")
print(getTransportTime(1000, 0, 1500, 9000, "Bicycle"))
print(getTransportTime(1000, 0, 1500, 9000, "Truck"))
print("")
print(getTransportTime(1000, 6000, 1500, 15000, "Bicycle"))
print(getTransportTime(1000, 6000, 1500, 15000, "Truck"))


def getAvgSpeed(i, j, vehicle):
    speeds = getTransportTime(i + 500, j, i, j, vehicle)
    speeds += getTransportTime(i, j + 500, i, j, vehicle)
    speeds += getTransportTime(i, j, i + 500, j, vehicle)
    speeds += getTransportTime(i, j, i, j + 500, vehicle)
    return speeds / 4

M = []
for i in range(0, 3000, 500):
    col = []
    for j in range(0, 11000, 500):
        # col.append(int(getAvgSpeed(i, j, "Truck")))
        col.append(int(getTransportTime(1500, 0, i, j, "Truck") / 60))
    M.append(col)


st = []
for i in range(len(M[0])):
    s = ""
    for j in range(len(M)):
        item = M[j][i]
        s += str(item)+ " "
    st.append(s)

for i in range(len(st)):
    print st[len(st) - i - 1]
