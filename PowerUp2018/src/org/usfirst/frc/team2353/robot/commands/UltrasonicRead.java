package org.usfirst.frc.team2353.robot.commands;

import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.frc.team2353.robot.OI;
import org.usfirst.frc.team2353.robot.Robot;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class UltrasonicRead extends Command {

	static SerialPort serialPort;
	int BaudRate = 9600;
	int databits = 8;

	boolean isPressed = false;
	int distance = 0;

	public UltrasonicRead() {
		requires(Robot.ultrasonic);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		serialPort = new SerialPort(BaudRate, SerialPort.Port.kUSB, databits);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (OI.xboxController.getRawButton(OI.AButtonNum) == true) {
			if (isPressed == false) {
				isPressed = true;

				int dist = getDistance();

				System.out.println("Distance: " + dist);
			}
		}
		else {
			isPressed = false;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}

	private int getDistance() {
		serialPort.writeString("1");

		Timer timer2 = new Timer();

		TimerTask task = new TimerTask() {
			public void run() {
				if(UltrasonicRead.serialPort.getBytesReceived() != 0) {
					distance = SerialRead();
					timer2.cancel();
				}
			}
		};

		timer2.schedule(task, 50);
		
		return distance;
	}
	
	int SerialRead() {
		distance = -1;
		
		int startIndex = -1;
		int endIndex = -1;

		String in = "";
		
		in = serialPort.readString();

		startIndex = in.indexOf('.');
		endIndex = 0;

		for (int i = startIndex + 1; i < in.length(); i++) {
			if (in.charAt(i) == '.') {
				endIndex = i;
				break;
			}
		}

		// System.out.println(in + " " + startIndex + " " + endIndex);

		if (startIndex != endIndex && startIndex != -1 && startIndex < endIndex && (startIndex + 1) != endIndex) {
			in = in.substring(startIndex + 1, endIndex);

			distance = Integer.parseInt(in);
		} else {
			distance = -1;
		}
		
		return distance;
	}
}
