package org.usfirst.frc.team2353.robot.subsystems;

import org.usfirst.frc.team2353.robot.EncoderInterface;
import org.usfirst.frc.team2353.robot.RobotMap;
import org.usfirst.frc.team2353.robot.commands.EncoderMove;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Encoder extends Subsystem {

	private Victor encoderMotor;

	EncoderInterface encoderInterface = new EncoderInterface(0, 1, false);

	public Encoder() {
		encoderMotor = new Victor(RobotMap.encoderMotor);
    }

    public void initDefaultCommand() {
    	setDefaultCommand(new EncoderMove());
    }

    public void move(double speed) {
    	encoderMotor.set(-speed);
    	printCount();
    }

    public void printCount() {
    	double count = encoderInterface.count();
    	System.out.println("Count: " + count);
    }
}
