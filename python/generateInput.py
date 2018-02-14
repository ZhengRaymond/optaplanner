import math
import random
import datetime

manhattan_x = 3000 # meters
manhattan_y = 11000 # meters
average_store_size = 8 # meters


def sampleX():
    x = -1
    while (x < 0 or x > manhattan_x):
        x = random.normalvariate(manhattan_x / 2, (manhattan_x / 4))
    return int(x / average_store_size) * average_store_size

def sampleY():
    y = -1
    while (y < 0 or y > manhattan_y):
        y = random.normalvariate(manhattan_y / 3, (manhattan_y / 8)) # manhattan_y divided by 3 to simulate downtown.
    return int(y / average_store_size) * average_store_size

def gen(size, filename):
    f = open(filename, "w")
    for i in range(size):
        pickup_x = sampleX()
        pickup_y = sampleY()
        f.write(str(pickup_x) + " " + str(pickup_y) + "\n")

        deliv_x = random.randint(0, manhattan_x)
        deliv_y = random.randint(0, manhattan_y)
        f.write(str(deliv_x) + " " + str(deliv_y) + "\n")

timestamp = datetime.datetime.now().isoformat()
gen(25, "./input_small_" + timestamp + ".txt")
gen(150, "./input_medium_" + timestamp + ".txt")
gen(1000, "./input_large_" + timestamp + ".txt")
