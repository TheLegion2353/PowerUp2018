package org.usfirst.frc.team2353.robot.commands;

import java.io.IOException;

import org.opencv.core.Mat;
import org.usfirst.frc.team2353.robot.MainVisionTargetDetection;
import org.usfirst.frc.team2353.robot.PIDController;
import org.usfirst.frc.team2353.robot.Robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AngledTapeTrack extends Command {
	MainVisionTargetDetection mainVisionTargetDetection = new MainVisionTargetDetection();
	PIDController rotationPID;
	
	private double width = 0;
	
    public AngledTapeTrack() {
    	requires(Robot.chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	width = getCameraWidth();
    	rotationPID = new PIDController(width / 2, -999999, 9999999, 1, 0, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double[] frameData = new double[2];
    	
    	try {
			frameData = mainVisionTargetDetection.normal();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	double centerOfTapeX = frameData[0];
    	double ratioOfTapeAreas = frameData[1]; //When the robot is further away from one tape than the other, the area of the further tape should be smaller. This ratio defines by how much. 
    	
    	//First check rotation
    	if(Math.abs((width / 2) - centerOfTapeX) > 20) { //If there's a small error, the code is fine
    		double rotationTime = rotationPID.Update(centerOfTapeX);
        	new TimedDrive(0,0, 0.5 * Math.signum(rotationTime), 0, Math.abs(rotationTime));
    	}
    	//Then check strafing. The reason that it's in an else if is that we don't want this to run using the old frame data, we want new data, so if a rotation was scheduled using this frame, we'll just wait for the next frame to try again. 
    	else if(Math.abs(ratioOfTapeAreas - 1) > 0.35) { //If the ratio is within 0.35, it's close enough
    		new TimedDrive(0.5 * Math.signum(ratioOfTapeAreas),0, 0, 0, 1);
    	}
    	else {
    		//We're in position!
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
    
    private double getCameraWidth() {
    	CameraServer.getInstance().startAutomaticCapture();
	      
        CvSink cvSink = CameraServer.getInstance().getVideo();
        
        Mat mat = new Mat();
        cvSink.grabFrame(mat);
        
        return mat.size().width;
    }
}
