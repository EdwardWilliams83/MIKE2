import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.EOPD;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.robotics.Color;

public class OffTheLine implements Behavior {
	public boolean suppress = false;

	@Override
	public boolean takeControl() {
		return BallCollector.cs.getColorID() != Color.BLACK && BallCollector.hasBall == true && FindLineWithBall.foundLine;
	}

	@Override
	public void action() {
		int rotateAngle = 10;
		while (!suppress) {
			BallCollector.driver.rotate(rotateAngle, true);
			while ( BallCollector.driver.isMoving()) {
				Thread.yield();
			}
			rotateAngle *= -2;
		}
		BallCollector.driver.stop();
		suppress = false;
	}

	@Override
	public void suppress() {
		suppress = true;
	}

}
