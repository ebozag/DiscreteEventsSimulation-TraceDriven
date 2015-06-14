import java.io.*;
//import java.util.Random;
import java.util.LinkedList;

public class Simulation {
//	public Random generator = new Random(); // random number generator
	public EventHeap h;
	double now;
	
	public Machine m = new Machine();
	public Repairman r = new Repairman();
	public User u = new User();
	
	//Added 2 LinkedLists to keep the data from trace file
	public LinkedList<Double> traceMachineNextFail = new LinkedList<Double>();
	public LinkedList<Double> traceRepairTime = new LinkedList<Double>();
	
	public Simulation(String filename) {
//		generator = new Random();
		h = new EventHeap(10000);
		now = 0;

		// Load data from trace file into the LinkedLists
		try{
			FileReader inputTraceFile = new FileReader(filename);
			BufferedReader bufferReader = new BufferedReader(inputTraceFile);
			String pairedValues;
			while ((pairedValues = bufferReader.readLine()) != null)   {
				String[] values = pairedValues.split(",");
				traceMachineNextFail.add(Double.parseDouble(values[0]));
				traceRepairTime.add(Double.parseDouble(values[1]));
			}		
			bufferReader.close();
		}catch(Exception e){
			System.out.println("Error:" + e.getMessage());
		}

	}

	public void scheduleEvent(Event e) {
		h.insert(e);
	}
	
	public void setup() {
		Event machineEvent = new Event();
		machineEvent.setHandler(m);
		machineEvent.setType(working);
		machineEvent.setTime(0);
		scheduleEvent(machineEvent);
		
		Event userEvent = new Event();
		userEvent.setHandler(u);
		userEvent.setType(userCheck);
		userEvent.setTime(60);
		scheduleEvent(userEvent);
		return;
	}
	
	public void run(double maxTime) {
		while (!traceMachineNextFail.isEmpty() && !traceRepairTime.isEmpty() && !h.isEmpty() && h.peek().getTime()<=maxTime) {
			Event nextEvent = h.remove();
			now = nextEvent.getTime();
			nextEvent.getHandler().respondToEvent(nextEvent, this);
		}
	}
	// events
	public final int working = 1;
	public final int failure = 2;
	public final int startRepair = 3;
	public final int finishRepair = 4;
	public final int userCheck = 5;

}
