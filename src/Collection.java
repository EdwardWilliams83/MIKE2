import lejos.nxt.Motor;
import lejos.robotics.Color;
import lejos.robotics.subsumption.Behavior;

public class Collection implements Behavior {
	public static boolean hasBall = false;

	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return (((LineFollower.calcDistance(LineFollower.EOPDConstant, LineFollower.eopdSense.readRawValue())) < 2)
				&& hasBall == false);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void action() {
		// TODO Auto-generated method stub
		if (LineFollower.cs.getColorID() == Color.RED) {
			Motor.B.setStallThreshold(100, 100);
			while (!Motor.B.isStalled()) {
				Motor.B.forward();
			}
			Motor.B.lock(100);
			hasBall = true;
		}
		// Motor.B.flt();
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub

	}

}
