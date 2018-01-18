package org.usfirst.frc.team2353.robot;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;

public class EncoderInterface {

	private Encoder encoder;
	
	public EncoderInterface(int channelA, int channelB, boolean isInverted, CounterBase.EncodingType EncodingType) {
		encoder = new Encoder(channelA, channelB, isInverted, EncodingType);
		
		encoder.setMaxPeriod(.1);
		encoder.setMinRate(10);
		encoder.setDistancePerPulse(5);
		encoder.setReverseDirection(true);
		encoder.setSamplesToAverage(7);
	}
	
	public EncoderInterface(int channelA, int channelB, boolean isInverted) {
		encoder = new Encoder(channelA, channelB, isInverted, Encoder.EncodingType.k4X);
		
		encoder.setMaxPeriod(.1);
		encoder.setMinRate(10);
		encoder.setDistancePerPulse(5);
		encoder.setReverseDirection(true);
		encoder.setSamplesToAverage(7);
	}
	
	public void reset() {
		encoder.reset();
	}
	
	public double count() {
		return encoder.getRaw();
	}
}
