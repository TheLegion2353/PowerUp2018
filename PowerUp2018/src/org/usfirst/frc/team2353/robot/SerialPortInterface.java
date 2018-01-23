package org.usfirst.frc.team2353.robot;

import edu.wpi.first.wpilibj.SerialPort;

public class SerialPortInterface {
	int BaudRate = 9600;
	int databits = 8;
	
	private static SerialPort serialPort;
	
	public SerialPortInterface() {
		serialPort = new SerialPort(BaudRate, SerialPort.Port.kUSB1, databits);
	}
	
	public static String Read() {
		return serialPort.readString(15);
	}
}
