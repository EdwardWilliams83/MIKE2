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

public class LineFollower {
	public static DifferentialPilot driver = new DifferentialPilot(58, 120, Motor.A, Motor.C);
	public static LightSensor ls = new LightSensor(SensorPort.S4);
	public static EOPD eopdSense = new EOPD(SensorPort.S2);
	public static ColorSensor cs = new ColorSensor(SensorPort.S3);
	public static int greenLight = 0;
	public static int redLight = 0;
	public static int blackLight = 0;
	public static double EOPDConstant = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		driver.setRotateSpeed(150);
		driver.setTravelSpeed(150);
		Behavior OffLine = new OffTheLine();
		Behavior forwardOnLine = new ForwardOnLine();
		Behavior collector = new Collection();
		calibrateEOPDSensor();
		Behavior[] behaviours = { OffLine, forwardOnLine, collector };
		Arbitrator ab = new Arbitrator(behaviours);
		Button.ENTER.waitForPressAndRelease();
		ab.start();
	}

	public static double calibrateEOPDSensor() {
		double k = 0;
		double sensorValue = 0;
		LCD.clear();
		LCD.drawString("Point an object 2cm from sensor", 0, 0);
		Button.ENTER.waitForPressAndRelease();
		sensorValue = eopdSense.readRawValue();
		k = 4 * sensorValue;
		return k;
	}

	public static double calcDistance(double Constant, double rawVal) {
		double dist = 0;
		dist = Math.sqrt(Constant) / Math.sqrt(rawVal);
		return dist;
	}

	public static void calibrateLightSensor() {
		int maxLight = 0;
		int minLight = 0;
		LCD.clear();
		LCD.drawString("Point at light", 2, 3);
		Button.ENTER.waitForPressAndRelease();
		maxLight = ls.readValue();
		ls.calibrateHigh();
		LCD.clear();
		LCD.drawString("Point at dark", 2, 3);
		Button.ENTER.waitForPressAndRelease();
		minLight = ls.readValue();
		ls.calibrateLow();
		LCD.clear();
		blackLight = minLight + 20;
		LCD.drawString("Point at red", 0, 0);
		Button.ENTER.waitForPressAndRelease();
		redLight = ls.readValue() + 10;
		LCD.drawString("Point at green", 0, 0);
		Button.ENTER.waitForPressAndRelease();
		greenLight = ls.readValue() + 10;
	}

}
