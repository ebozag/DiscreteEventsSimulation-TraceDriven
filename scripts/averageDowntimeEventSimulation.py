#!/usr/bin/env python
import fileinput
import re

'''
   This script will receive the output from the "DiscreteEventSimulation" program
   via the stdin, and calculate the average downtime for a machine.
   
   @author: Edwin Boza
   @fecha: 13/06/2015
   
'''

# Variables Initialization
# "failureTime" will contain the time when a machine fails.
# totalDowntime will be the sum of all downtimes (repaire time - failureTime)
# downtimeEvents will count the number of downtime periods, this will be used to calculate the mean
failureTime = 0
totalDowntime = 0
downtimeEvents = 0

# Read and parse each line of stdin.
for line in fileinput.input():
    parsedEvent = re.findall(r'(.*)\s(.*)\s(.*)',line.rstrip())
    # If the event is a "machine failure", its time will be saved on the failureTime variable
    if parsedEvent[0][2] == 'failure':
        failureTime = float(parsedEvent[0][0])
    # If the event is a "machine working", we will subtract failureTime from its time and 
    # the result will be added to the "totalDowntime"variable
    if parsedEvent[0][2] == 'working':
        totalDowntime += (float(parsedEvent[0][0]) - failureTime)
        downtimeEvents += 1
        
# Calculate (and print) the average downtime.
# We are subtracting 1 from the downtime Events because the output from the simulation program
# starts with a "machine working" event at time 0, and our script is computing a downtime event 
# of zero seconds, so its better not considering it in the operation.
print 'Average Downtime = ', (totalDowntime/(downtimeEvents - 1))

