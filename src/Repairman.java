
public class Repairman implements EventHandler {

//	double MTTR;
//	double MTTRvariance;
	
	public Repairman() {
//		MTTR = 10.0;
//		MTTRvariance = 2.0;
	}
	
	
	@Override
	public void respondToEvent(Event e, Simulation s) {
		if (e.getType()==s.startRepair) {
			System.out.println(e.getTime()+" starting repair");
			// double timeToRepair = Math.abs(s.generator.nextGaussian()*MTTRvariance+MTTR);
			double timeToRepair = s.traceRepairTime.removeFirst();
			e.setType(s.finishRepair);
			e.setTime(s.now+timeToRepair);
			s.scheduleEvent(e);
			return;
		}
		if (e.getType()==s.finishRepair) {
			System.out.println(e.getTime()+" finishing repair");
			e.setHandler(s.m);
			e.setType(s.working);
			e.setTime(s.now);
			s.scheduleEvent(e);
			return;
		}
	}

}
