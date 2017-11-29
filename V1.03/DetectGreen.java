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

class DetectGreen implements Behavior{

	@Override
	public boolean takeControl() {
		return LineFollower.cs.getColorID() == Color.GREEN && LineFollower.hasBall && Collection.isGreen;
	}

	@Override
	public void action() {
		Motor.B.rotate(-180);
		LineFollower.driver.stop();
		Motor.A.rotate(-600, true);
		Motor.C.rotate(-600);
		LineFollower.driver.rotate(180);
		LineFollower.hasBall = false;
		Collection.isGreen = false;
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}

}

