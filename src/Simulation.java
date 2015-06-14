import java.util.Random;


public class Simulation {
	public Random generator = new Random(); // random number generator
	public EventHeap h;
	double now;
	
	public Machine m = new Machine();
	public Repairman r = new Repairman();
	public User u = new User();
	
	public Simulation() {
		generator = new Random();
		h = new EventHeap(10000);
		now = 0;
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
		while (!h.isEmpty() && h.peek().getTime()<=maxTime) {
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
