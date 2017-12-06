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

public class ForwardOnLine implements Behavior {

	@Override
	public boolean takeControl() {
		return (BallCollector.cs.getColorID() == Color.BLACK) && BallCollector.hasBall == true && FindLineWithBall.foundLine;
	}

	@Override
	public void action() {
		BallCollector.driver.forward();
		while (BallCollector.cs.getColorID() == Color.BLACK) {
			Thread.yield();
		}
	}

	@Override
	public void suppress() {
		BallCollector.driver.stop();
	}

}
