import lejos.robotics.subsumption.Behavior;

public class ForwardOnLine implements Behavior {

	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return LineFollower.ls.readValue() < 40;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		LineFollower.driver.forward();
		while (LineFollower.ls.readValue() < 40) {
			Thread.yield();
		}
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		LineFollower.driver.stop();
	}

}
