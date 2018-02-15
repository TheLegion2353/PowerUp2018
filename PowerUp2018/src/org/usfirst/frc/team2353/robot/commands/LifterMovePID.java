package org.usfirst.frc.team2353.robot.commands;

import org.usfirst.frc.team2353.robot.OI;
import org.usfirst.frc.team2353.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LifterMovePID extends Command {

	boolean lifterPID = false;
	
    public LifterMovePID() {
        requires(Robot.lifterPID);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(OI.xboxController.getRawButton(OI.XButtonNum) == true) {
    		lifterPID = !lifterPID;
    	}
    	if(lifterPID) {
    		if(OI.getTriggerValue() > 0) {
        		Robot.lifterPID.addHeight(OI.getTriggerValue() * 50);
        	}
    	}
    	else {
    		Robot.lifterPID.moveLifter(OI.getTriggerValue());
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
