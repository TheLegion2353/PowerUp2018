package org.usfirst.frc.team2353.robot.commands;

import org.usfirst.frc.team2353.robot.OI;
import org.usfirst.frc.team2353.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LifterMove extends Command {

    public LifterMove() {
        requires(Robot.lifter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.lifter.moveLifter(OI.getTriggerValue());
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
