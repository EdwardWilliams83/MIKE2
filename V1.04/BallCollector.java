import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.EOPD;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class BallCollector {
	public static DifferentialPilot driver = new DifferentialPilot(58, 120, Motor.A, Motor.C);
	public static LightSensor ls = new LightSensor(SensorPort.S4);
	public static EOPD eopdSense = new EOPD(SensorPort.S2);
	public static ColorSensor cs = new ColorSensor(SensorPort.S3);
	public static UltrasonicSensor us = new UltrasonicSensor(SensorPort.S1);
	public static int greenLight = 0;
	public static int redLight = 0;
	public static boolean hasBall = false;
	public static double EOPDConstant = 0;

	public static void main(String[] args) {
		driver.setRotateSpeed(90);
		driver.setTravelSpeed(80);
		Behavior OffLine = new OffTheLine();
		Behavior forwardOnLine = new ForwardOnLine();
		Behavior collector = new Collection();
		Behavior redDetection = new DetectRed();
		Behavior greenDetection = new DetectGreen();
		Behavior findLine = new FindLineWithBall();
		Behavior lineNotFound = new LineNotFound();
		Behavior objectDetection = new MoveAroundObject();
		LCD.drawString("Start",0,0);
		Button.ENTER.waitForPressAndRelease();
		calibrateEOPDSensor();
		calibrateLightSensor();
		Behavior[] behaviours = {collector,findLine,OffLine, redDetection, greenDetection,forwardOnLine,findLine, objectDetection};
		Arbitrator ab = new Arbitrator(behaviours);
		LCD.clear();
		LCD.drawString("Ready", 0,0);
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
		//blackLight = minLight;
		LCD.drawString("Point at red", 0, 0);
		Button.ENTER.waitForPressAndRelease();
		redLight += ls.readNormalizedValue();
		LCD.clear();
		LCD.drawString("Point at green", 0, 0);
		Button.ENTER.waitForPressAndRelease();
		greenLight += ls.readNormalizedValue();
		LCD.clear();
		LCD.drawString("Point at red", 0, 0);
		Button.ENTER.waitForPressAndRelease();
		redLight += ls.readNormalizedValue();
		LCD.clear();
		LCD.drawString("Point at green", 0, 0);
		Button.ENTER.waitForPressAndRelease();
		greenLight += ls.readNormalizedValue();
		LCD.clear();
		LCD.drawString("Point at red", 0, 0);
		Button.ENTER.waitForPressAndRelease();
		redLight += ls.readNormalizedValue();
		LCD.clear();
		LCD.drawString("Point at green", 0, 0);
		Button.ENTER.waitForPressAndRelease();
		greenLight += ls.readNormalizedValue();
		redLight /= 3;
		greenLight /= 3;
		LCD.drawString("Green: " + greenLight, 0, 0);
		LCD.drawString("Red: " + redLight, 0, 1);
	}
}
