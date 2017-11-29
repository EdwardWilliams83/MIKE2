import lejos.nxt.Motor;
import lejos.robotics.subsumption.Behavior;

public class FloorDetection implements Behavior {

	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return ((LineFollower.ls.readValue() < LineFollower.greenLight
				|| LineFollower.ls.readValue() < LineFollower.redLight) && Collection.hasBall == true);
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		LineFollower.driver.stop();
		Motor.A.rotate(720);
		Motor.C.rotate(720);
		Motor.B.setStallThreshold(100, 100);
		Motor.B.flt();
		while (!Motor.B.isStalled()) {
			Motor.B.backward();
		}
		Collection.hasBall = false;
		Motor.A.rotate(-1000);
		Motor.C.rotate(-1000);
		LineFollower.driver.rotate(180);
		LineFollower.driver.forward();
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub

	}

}
