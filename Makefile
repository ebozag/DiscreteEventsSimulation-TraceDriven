
source_dir=src
bin_dir=bin

all: folders
	javac -d $(bin_dir) -cp $(source_dir) $(source_dir)/DiscreteEventSimulation.java 

clean:
	rm -rf $(bin_dir)

folders:
	-mkdir -p $(bin_dir)
