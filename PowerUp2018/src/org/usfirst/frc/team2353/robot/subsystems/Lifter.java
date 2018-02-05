package org.usfirst.frc.team2353.robot.subsystems;

import org.usfirst.frc.team2353.robot.RobotMap;
import org.usfirst.frc.team2353.robot.commands.LifterMove;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lifter extends Subsystem {

	private Victor lifterMotor;
	
	public Lifter() {
		lifterMotor = new Victor(RobotMap.lifter);
	}

    public void initDefaultCommand() {
        setDefaultCommand(new LifterMove());
    }
    
    public void moveLifter(double speed) {
    	lifterMotor.set(speed);
    }
}

