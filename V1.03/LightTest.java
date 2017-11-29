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

class LightTest{
	public static DifferentialPilot driver = new DifferentialPilot(58, 120, Motor.A, Motor.C);
	public static LightSensor ls = new LightSensor(SensorPort.S4);
	public static EOPD eopdSense = new EOPD(SensorPort.S2);
	public static ColorSensor cs = new ColorSensor(SensorPort.S3);
	public static int greenLight = 0;
	public static int redLight = 0;
	public static int blackLight = 0;
	public static boolean hasBall = false;
	public static double EOPDConstant = 0;
	public static void main(String[] args){
		calibrateLightSensor();
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
		/*LCD.clear();
		blackLight = minLight + 20;
		LCD.drawString("Point at red", 0, 0);
		Button.ENTER.waitForPressAndRelease();
		redLight = ls.readValue() + 10;
		LCD.drawString("Point at green", 0, 0);
		Button.ENTER.waitForPressAndRelease();
		greenLight = ls.readValue() + 10;
		LCD.clear();
		LCD.drawString("Green: " + greenLight, 0,0);
		LCD.drawString("Red: " + redLight, 0,1);
		LCD.drawString("Black: " + blackLight, 0,2);
		*/
		LCD.drawString("white:" + maxLight,0,0);
		Button.ENTER.waitForPressAndRelease();
		
		
	}
}