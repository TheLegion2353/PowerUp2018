package org.usfirst.frc.team2353.robot.subsystems;

import org.usfirst.frc.team2353.robot.RobotMap;
import org.usfirst.frc.team2353.robot.commands.GrabberMove;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Grabber extends Subsystem {

    private static Victor rightMotor;
	private static Victor leftMotor;
    
    public Grabber() {
    	rightMotor = new Victor(RobotMap.rightGrabberMotor);
    	leftMotor = new Victor(RobotMap.leftGrabberMotor);
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new GrabberMove());
    }
    
    public static void move(double speed) {
    	rightMotor.set(speed);
    	leftMotor.set(-speed);
    }
    
    public void moveRight(double speed) {
    	rightMotor.set(speed);
    }
    
    public void moveLeft(double speed) {
    	leftMotor.set(speed);
    }
}

