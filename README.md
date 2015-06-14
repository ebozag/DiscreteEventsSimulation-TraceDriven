# DiscreteEventsSimulation-TraceDriven

=====================================================
WARNING:
    This software comes with ABSOLUTELY NO WARRANTEE.
    USE AT YOUR OWN RISK!
=====================================================

This is a student project developed by Edwin Boza, for the Course "Sistemas Operativos Avanzados" at the 
Computer Science MS Degree program at Escuela Superior Politécnica del Litoral (www.mcc.espol.edu.ec)

The original code was posted as example for the "Lecture 30: Discrete Event Simulation",
from the course "Data Structures and Algorithms" at the University of Massachussets 
Amherst - http://www.ecs.umass.edu/ece242/.

We have modified the code to change the original stochastic simulation into a trace-driven
approach.  

We also have included some Python scripts as an example on how to use shell scriting to process
the results from the simulation program.

The folder structure of the project is:

	bin/ - This folder is created by the compilation process and will include the .class files
	scripts/ - This folder includes the Python scripts to process the simulation output.
	src/ - This folder includes the source code files (JAVA)
	traces/ - This folder includes examples of "traces" to be used as input on the trace-driven simulation.


COMPILATION:

	From the project folder, run the following commands:

		make clean
		make

RUN:

	From the project forlder, run the following commands:

		java -cp bin DiscreteEventSimulation <path to trace file>

	Example:

		java -cp bin DiscreteEventSimulation traces/trace-fromOriginalDiscreteEventSimulation.txt


