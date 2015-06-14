#!/usr/bin/env python
import fileinput
import re

'''
   This script will receive the output from the "DiscreteEventSimulation" program
   via the stdin, and calculate the average service time for a machine.
   
   @author: Edwin Boza
   @fecha: 13/06/2015
   
'''

# Variables Initialization
# startRepairTime will contain the time when the repair process begins.
# totalServiceTime will be the sum of all service times (finishing repair time - startRepairTime)
# repairEvents will count the number of repair periods, this will be used to calculate the average
startRepairTime = 0
totalServiceTime = 0
repairEvents = 0

# Read and parse each line of stdin.
for line in fileinput.input():
    parsedEvent = re.findall(r'(.*)\s(.*)\s(.*)',line.rstrip())
    # If the event is a "starting repair", its time will be saved on the startRepairTime variable
    if parsedEvent[0][1] == 'starting':
        startRepairTime = float(parsedEvent[0][0])
    # If the event is a "finishing repair", we will subtract startRepairTime from its time and 
    # the result will be added to the "totalServiceTime"variable
    if parsedEvent[0][1] == 'finishing':
        totalServiceTime += (float(parsedEvent[0][0]) - startRepairTime)
        repairEvents += 1
        
# Calculate (and print) the average service time.
print 'Average Service Time = ', (totalServiceTime/(repairEvents))

