package org.usfirst.frc.team2353.robot;

//http://robotsforroboticists.com/pid-control/
public class PIDController {
	private double proportionalGain, derivativeGain, integralGain;
	private double output, minOutput, maxOutput;
	private double target, previousError;
	private double previousIntegral;
	
	private long startTime = System.currentTimeMillis();
	private long endTime = System.currentTimeMillis();
	
	private boolean firstRun = true;
	
	public PIDController(double target, double minOutput, double maxOutput, double proportionalGain, double derivativeGain, double integralGain) {
		this.target = target;
		this.minOutput = minOutput;
		this.maxOutput = maxOutput;
		this.proportionalGain = proportionalGain;
		this.derivativeGain = derivativeGain;
		this.integralGain = integralGain;
	}
	
	public double Update(double currentValue) {
		endTime = System.currentTimeMillis();
		
		double iterationTime = endTime - startTime;

		if(firstRun) {
			previousError = target - currentValue;
			previousIntegral = 0;
			iterationTime = 1;
			
			firstRun = false;
		}
		
		double proportionalComponent = target - currentValue;
		double error = proportionalComponent;
		double integralComponent = previousIntegral + (error * iterationTime);
		double derivativeComponent = (error - previousError) / iterationTime;
		
		output = (proportionalGain * proportionalComponent) +  (integralGain * integralComponent) + (derivativeGain * derivativeComponent);
		
		if(output < minOutput) {
			output = minOutput;
		}
		if(output > maxOutput) {
			output = maxOutput;
		}
		
		startTime = System.currentTimeMillis();
		return output;
	}
	
	public double getOutput() {
		return output;
	}
}
