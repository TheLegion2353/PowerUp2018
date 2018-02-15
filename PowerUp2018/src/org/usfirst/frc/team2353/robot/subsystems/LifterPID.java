package org.usfirst.frc.team2353.robot.subsystems;

import org.usfirst.frc.team2353.robot.EncoderInterface;
import org.usfirst.frc.team2353.robot.PDControl;
import org.usfirst.frc.team2353.robot.RobotMap;
import org.usfirst.frc.team2353.robot.commands.LifterMovePID;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LifterPID extends Subsystem {

	private Victor lifterMotor;
	EncoderInterface encoderInterface;
	PDControl motorPID;
	
	public LifterPID() {
		lifterMotor = new Victor(RobotMap.lifter);
		encoderInterface = new EncoderInterface(RobotMap.encoderPortOne, RobotMap.encoderPortTwo, false);
		motorPID = new PDControl(0.1, 0.1);
	}

    public void initDefaultCommand() {
        setDefaultCommand(new LifterMovePID());
    }
    
    public void moveLifter(double speed) {
    	lifterMotor.set(speed);
    	System.out.println(encoderInterface.count());
    }
    
    public void setHeight(double targetCount) {
    	double currentCount = encoderInterface.count();
    	
    	double error = currentCount - targetCount;
    	
    	if(Math.abs(error) < 50) {
    		error = 0;
		}
    	
    	motorPID.Update(error);
    	
    	//moveLifter(((motorPID.currentPos * 2) - 1) * 0.2);
    }
    
    public void addHeight(double addedCount) {
    	setHeight(encoderInterface.count() + addedCount);
    }
}

