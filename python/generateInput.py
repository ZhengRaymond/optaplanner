import math
from random import randint as ri
import datetime

manhattan_x = 3000
manhattan_y = 11000


def gen(size, filename):
    f = open(filename, "w")
    for i in range(size):
        pickup_x = ri(0, manhattan_x)
        pickup_y = ri(0, manhattan_y)
        f.write(str(pickup_x) + " " + str(pickup_y) + "\n")

        deliv_x = ri(0, manhattan_x)
        deliv_y = ri(0, manhattan_y)
        f.write(str(deliv_x) + " " + str(deliv_y) + "\n")

timestamp = datetime.datetime.now().isoformat()
gen(25, "./input_small_" + timestamp + ".txt")
gen(150, "./input_medium_" + timestamp + ".txt")
gen(1000, "./input_large_" + timestamp + ".txt")
