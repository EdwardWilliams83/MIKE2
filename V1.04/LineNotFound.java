import lejos.robotics.subsumption.Behavior;
import lejos.robotics.Color;
public class LineNotFound implements Behavior {
	public static boolean foundLine = false;
	@Override
	public boolean takeControl() {		
		return BallCollector.hasBall && BallCollector.cs.getColorID() == Color.BLACK && foundLine == false;
	}

	@Override
	public void action() {
		foundLine = true;
	}

	@Override
	public void suppress() {		
	}

}
