package org.usfirst.frc.team2353.robot.subsystems;

import org.usfirst.frc.team2353.robot.commands.UltrasonicRead;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Ultrasonic extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new UltrasonicRead());
    }
    
    public void printSerial(String string) {
		System.out.println(string);
	}
}

