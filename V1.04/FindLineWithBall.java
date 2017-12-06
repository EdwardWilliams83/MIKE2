import lejos.robotics.subsumption.Behavior;
import lejos.robotics.Color;
public class FindLineWithBall implements Behavior {
	public static boolean foundLine = false;
	@Override
	public boolean takeControl() {		
		return BallCollector.hasBall && foundLine == false;
	}

	@Override
	public void action() {
		if(BallCollector.cs.getColorID() == Color.BLACK){
			foundLine = true;
		} else {
			BallCollector.driver.forward();
		}
	}

	@Override
	public void suppress() {		
	}

}
