package org.usfirst.frc.team2353.robot.commands;

import org.usfirst.frc.team2353.robot.Robot;
import org.usfirst.frc.team2353.robot.subsystems.Lifter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveUp extends Command {

	double speed = 0;
	
    public MoveUp(double time, double speed) {
    	requires(Robot.lifter);
    	
    	setTimeout(time);
    	
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Lifter.moveLifter(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
