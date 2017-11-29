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
		// TODO Auto-generated method stub
		LCD.drawInt(LineFollower.ls.readValue(),0,0);
		return (LineFollower.cs.getColorID() == Color.BLACK) && LineFollower.hasBall == true;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		LineFollower.driver.forward();
		while (LineFollower.cs.getColorID() == Color.BLACK) {
			Thread.yield();
		}
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		LineFollower.driver.stop();
	}

}
