package org.usfirst.frc.team2353.robot.commands;

import java.nio.ByteBuffer;

import org.usfirst.frc.team2353.robot.OI;
import org.usfirst.frc.team2353.robot.Robot;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class UltrasonicRead extends Command {

	private static SerialPort serialPort;
	int BaudRate = 9600;
	int databits = 8;
	
    public UltrasonicRead() {
       requires(Robot.ultrasonic);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//serialPort = new SerialPort(BaudRate, SerialPort.Port.kUSB, databits);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(OI.xboxController.getRawButton(OI.AButtonNum) == true) {
    		byte[] bytes = new byte[4];
    		ByteBuffer.wrap(bytes).putInt(1);
    		
    		System.out.println(bytes.toString());
    		
    		serialPort.write(bytes, bytes.length);
    		
    		try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		String in = serialPort.readString();
    		System.out.println("Distance: " + in);
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
}
