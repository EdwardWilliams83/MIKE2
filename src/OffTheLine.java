import lejos.robotics.subsumption.Behavior;

public class OffTheLine implements Behavior {
	public boolean suppress = false;

	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub

		return LineFollower.ls.readValue() > 40;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		int rotateAngle = 10;
		while (!suppress) {
			LineFollower.driver.rotate(rotateAngle, true);
			while (!suppress && LineFollower.driver.isMoving()) {
				Thread.yield();
			}
			rotateAngle *= -2;
		}
		LineFollower.driver.stop();
		suppress = false;
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		suppress = true;
	}

}
