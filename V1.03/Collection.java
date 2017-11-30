import lejos.nxt.Motor;
import lejos.robotics.Color;
import lejos.robotics.subsumption.Behavior;
import lejos.nxt.LCD;

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
		LCD.clear();
		if (LineFollower.ls.readValue() > LineFollower.redLight - 5 && LineFollower.ls.readValue() < LineFollower.redLight + 5) {
			LineFollower.driver.stop();
			Motor.B.setStallThreshold(10, 300);
			LineFollower.hasBall = true;
			while (!Motor.B.isStalled()) {
				Motor.B.forward();
			}
			Motor.B.lock(100);
			isRed = true;
			LineFollower.hasBall = true;
			LCD.drawString("Red", 0,0);
			LineFollower.driver.forward();
		}
		if (LineFollower.ls.readValue() < LineFollower.greenLight + 5 && LineFollower.ls.readValue() > LineFollower.greenLight - 5) {
			LineFollower.driver.stop();
			Motor.B.setStallThreshold(10, 300);
			LineFollower.hasBall = true;
			while (!Motor.B.isStalled()) {
				Motor.B.forward();
			}
			Motor.B.lock(100);
			isGreen = true;
			
			LCD.drawString("Green", 0,0);
			LineFollower.hasBall = true;
			LineFollower.driver.forward();
		}
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub

	}

}
