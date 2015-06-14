#!/usr/bin/env python
import fileinput
import re
from decimal import *

'''
   This script will receive the output from the original "DiscreteEventSimulation" program
   via the stdin, and generate a trace input for the new "DiscreteEventSimulation-TraceDriven"
   program.
   
   @author: Edwin Boza
   @fecha: 13/06/2015
   
'''

# Variables Initialization
# "failureTime" will contain the time when a machine fails.
# totalDowntime will be the sum of all downtimes (repaire time - failureTime)
# downtimeEvents will count the number of downtime periods, this will be used to calculate the mean
startWorkingTime = Decimal(0.0)
timeToFailure = Decimal(0.0)
startRepairTime = Decimal(0.0)
repairTime = Decimal(0.0)
line = []
# Read and parse each line of stdin.
for line in fileinput.input():
    parsedEvent = re.findall(r'(.*)\s(.*)\s(.*)',line.rstrip())
    # If the event is a "machine failure", its time will be saved on the failureTime variable
    if parsedEvent[0][2] == 'working':
        startWorkingTime = Decimal(parsedEvent[0][0])
    # If the event is a "machine working", we will subtract failureTime from its time and 
    # the result will be added to the "totalDowntime"variable
    if parsedEvent[0][2] == 'failure':
        timeToFailure = (Decimal(parsedEvent[0][0]) - startWorkingTime)

    # If the event is a "starting repair", its time will be saved on the startRepairTime variable
    if parsedEvent[0][1] == 'starting':
        startRepairTime = Decimal(parsedEvent[0][0])
    # If the event is a "finishing repair", we will subtract startRepairTime from its time and 
    # the result will be added to the "totalServiceTime"variable
    if parsedEvent[0][1] == 'finishing':
        serviceTime = (Decimal(parsedEvent[0][0]) - startRepairTime)
	print timeToFailure,',',serviceTime
        

