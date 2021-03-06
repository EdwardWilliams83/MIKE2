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
		return BallCollector.cs.getColorID() == Color.GREEN && BallCollector.hasBall && Collection.isGreen;
	}

	@Override
	public void action() {
		Motor.B.rotate(-180);
		BallCollector.driver.stop();
		BallCollector.driver.travel(-150);
		BallCollector.driver.rotate(90);
		BallCollector.hasBall = false;
		Collection.isGreen = false;
		FindLineWithBall.foundLine = false;
	}

	@Override
	public void suppress() {
		
	}

}

