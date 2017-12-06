import lejos.robotics.subsumption.Behavior;
import lejos.robotics.Color;
public class MoveAroundObject implements Behavior {
	public static boolean foundLine = false;
	@Override
	public boolean takeControl() {		
		return BallCollector.hasBall && BallCollector.us.getDistance() < 4;
	}

	@Override
	public void action() {
		BallCollector.driver.stop();
		BallCollector.driver.travel(-50);
		BallCollector.driver.rotate(90);
		BallCollector.driver.travel(50);
		BallCollector.driver.rotate(90);
		BallCollector.driver.forward();
	}

	@Override
	public void suppress() {		
	}

}
