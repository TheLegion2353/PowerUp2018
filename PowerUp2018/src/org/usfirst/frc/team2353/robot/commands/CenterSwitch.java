package org.usfirst.frc.team2353.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterSwitch extends CommandGroup {

    public CenterSwitch() {
    	addSequential(new TimedDrive(0, 0, 0.3, 0, 0.9));
    	addSequential(new TimedDrive(0, .5, 0, 0, 1.95)); 
		addSequential(new TimedDrive(0, 0, -0.22, 0, 0.52));
		//addSequential(new TimedDrive(0, 0.5, 0, 0, 0.1));
		addSequential(new MoveUp(2.7, 1));
		addSequential(new FireOut(0.5, 0.6));
    }
}
