import lejos.nxt.Motor;
import lejos.robotics.Color;
import lejos.robotics.subsumption.Behavior;

public class Collection implements Behavior {
	//public static boolean hasBall = false;
	public static boolean isRed = false;
	public static boolean isGreen = false;
	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return (((LineFollower.calcDistance(LineFollower.EOPDConstant, LineFollower.eopdSense.readRawValue())) < 2)
				&& LineFollower.hasBall == false);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void action() {
		// TODO Auto-generated method stub
		if (LineFollower.ls.readValue() > LineFollower.redLight - 10 && LineFollower.ls.readValue() < LineFollower.redLight + 30) {
			Motor.B.setStallThreshold(10, 300);
			LineFollower.hasBall = true;
			while (!Motor.B.isStalled()) {
				Motor.B.forward();
			}
			Motor.B.lock(100);
			isRed = true;
			LineFollower.hasBall = true;
		}
		if (LineFollower.cs.getColorID() < LineFollower.greenLight + 10) {
			Motor.B.setStallThreshold(10, 300);
			LineFollower.hasBall = true;
			while (!Motor.B.isStalled()) {
				Motor.B.forward();
			}
			Motor.B.lock(100);
			isGreen = true;
			LineFollower.hasBall = true;
		}
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub

	}

}
