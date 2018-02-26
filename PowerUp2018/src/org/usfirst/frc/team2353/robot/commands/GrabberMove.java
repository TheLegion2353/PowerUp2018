package org.usfirst.frc.team2353.robot.commands;

import org.usfirst.frc.team2353.robot.OI;
import org.usfirst.frc.team2353.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GrabberMove extends Command {

    public GrabberMove() {
        requires(Robot.grabber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(OI.xboxController.getRawButton(OI.right_Bumper) == true && OI.xboxController.getRawButton(OI.left_Bumper) == false) {
			Robot.grabber.moveRight(-0.6);
			Robot.grabber.moveLeft(0);
		}
    	else if(OI.xboxController.getRawButton(OI.left_Bumper) == true && OI.xboxController.getRawButton(OI.right_Bumper) == false) {
    		Robot.grabber.moveLeft(0.6);
    		Robot.grabber.moveRight(0);
		}
    	else if(OI.xboxController.getRawButton(OI.right_Bumper) == true && OI.xboxController.getRawButton(OI.left_Bumper) == true) {
    		Robot.grabber.move(-0.4);
    	}
    	else if(OI.xboxController.getRawButton(OI.AButtonNum) == true) {
    		Robot.grabber.move(1);
    	}
    	else {
    		Robot.grabber.move(0);
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
