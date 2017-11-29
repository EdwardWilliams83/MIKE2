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
		// TODO Auto-generated method stub
		
		return LineFollower.cs.getColorID() == Color.WHITE && LineFollower.hasBall == true;
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
