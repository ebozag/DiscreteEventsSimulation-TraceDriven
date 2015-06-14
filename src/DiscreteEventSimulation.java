
public class DiscreteEventSimulation {

	public static void main(String[] args) {
		
		if(args.length != 1) {
 			System.err.println("The name for the trace file is required.");
  			System.exit(1);
		}

		Simulation s = new Simulation(args[0]);
		
		s.setup(); // setup simulation;
		
		s.run(10000); // run of simulation
		
	}

}
