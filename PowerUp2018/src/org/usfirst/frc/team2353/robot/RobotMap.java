package org.usfirst.frc.team2353.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//USB Ports
	public final static int xboxPort = 0;

	//Motors
	public final static int frontLeftMotor = 0, rearLeftMotor = 2, frontRightMotor = 1, rearRightMotor = 3;
	public final static int encoderMotor = 8;
	public final static int rightGrabberMotor = 4, leftGrabberMotor = 5;
	public final static int lifter = 6;
	
	//Sensor
	public final static int encoderPortOne = 2, encoderPortTwo = 3;

	//Gets the possession of each of the switches for autonomous.
	public static boolean closeSwitch, scale, farScale; //Right is true, left is false.
}
