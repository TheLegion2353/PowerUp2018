package org.usfirst.frc.team2353.robot.subsystems;

import org.usfirst.frc.team2353.robot.RobotMap;
import org.usfirst.frc.team2353.robot.commands.GrabberMove;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Grabber extends Subsystem {

    private Victor rightMotor, leftMotor;
    
    public Grabber() {
    	rightMotor = new Victor(RobotMap.rightMotor);
    	leftMotor = new Victor(RobotMap.leftMotor);
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new GrabberMove());
    }
    
    public void move(double speed) {
    	rightMotor.set(speed);
    	leftMotor.set(-speed);
    }
}

