import lejos.nxt.Motor;
import lejos.robotics.Color;
import lejos.robotics.subsumption.Behavior;
import lejos.nxt.LCD;

public class Collection implements Behavior {
	public static boolean isRed = false;
	public static boolean isGreen = false;
	@Override
	public boolean takeControl() {
		return BallCollector.hasBall == false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void action() {
		LCD.clear();
		if( BallCollector.calcDistance(BallCollector.EOPDConstant, BallCollector.eopdSense.readRawValue()) < 2){
			
		if (BallCollector.ls.readNormalizedValue() > BallCollector.redLight - 5 && BallCollector.ls.readNormalizedValue() < BallCollector.redLight + 5) {
			BallCollector.driver.stop();
			Motor.B.setStallThreshold(10, 300);
			BallCollector.hasBall = true;
			while (!Motor.B.isStalled()) {
				Motor.B.forward();
			}
			Motor.B.lock(100);
			isRed = true;
			BallCollector.hasBall = true;
			LCD.drawString("Red", 0,0);
			BallCollector.driver.forward();
		}
		if (BallCollector.ls.readNormalizedValue() < BallCollector.greenLight + 5 && BallCollector.ls.readNormalizedValue() > BallCollector.greenLight - 5) {
			BallCollector.driver.stop();
			Motor.B.setStallThreshold(10, 300);
			BallCollector.hasBall = true;
			while (!Motor.B.isStalled()) {
				Motor.B.forward();
			}
			Motor.B.lock(100);
			isGreen = true;
			
			LCD.drawString("Green", 0,0);
			BallCollector.hasBall = true;
			BallCollector.driver.forward();
		}
		} 
		if(BallCollector.hasBall == false){
			LCD.drawInt(21,0,0);
			if(BallCollector.cs.getColorID() == Color.WHITE){
				BallCollector.driver.forward();
			} else {
				BallCollector.driver.rotate(90);
				BallCollector.driver.travel(40);
				BallCollector.driver.rotate(90);
			}
		}
	}

	@Override
	public void suppress() {
	}
	

}
