#!/usr/bin/env python

import sys
from random import randint, choice

if len(sys.argv)<3:
  sys.exit("Enter number of blocks and number of commands.\nUsage: python generateInput.py 25 1000")
blocks = sys.argv[1]
commands = sys.argv[2]

fout = sys.stdout
fout.write(blocks+"\n")
for i in range(int(commands)):
  fout.write(choice(["move ", "pile "])+str(randint(0,int(blocks)-1))+
             choice([" over ", " onto "])+str(randint(0,int(blocks)-1))+"\n")
fout.write("quit\n")
