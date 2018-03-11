package org.usfirst.frc.team2353.robot.commands;

import org.usfirst.frc.team2353.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveForward extends CommandGroup {

    public MoveForward() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	if(Robot.position == 0) {
    		addSequential(new TimedDrive(0, .5, 0, 0, 1.8));
    		//addSequential(new TimedDrive(1, 0, 0, 0, 1));
    	}
    	else if(Robot.position == 1) {
    		addSequential(new TimedDrive(0, .5, 0, 0, .8));
    		addSequential(new TimedDrive(0, 0, 0.175, 0, 3) );
    		addSequential(new TimedDrive(0, .5, 0, 0, 1));
    		addSequential(new TimedDrive(0, 0, -0.3, 0, 3) );
    		addSequential(new TimedDrive(0, .5, 0, 0, .8));

    		addSequential(new TimedDrive(0, 0, -0.2, 0, 3) );

    		addSequential(new TimedDrive(0, .5, 0, 0, .8));
    		
    	}
    	else if(Robot.position == 2) {
    		addSequential(new TimedDrive(0, 0, 0, 0, 2.5));    		
    	}
    }
}
