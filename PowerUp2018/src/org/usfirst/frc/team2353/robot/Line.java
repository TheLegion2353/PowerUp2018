package org.usfirst.frc.team2353.robot;

import org.opencv.core.Point;

public class Line {
	private double startX = 0;
	private double startY = 0;
	private double endX = 0;
	private double endY = 0;
	
	private double length = -1;
	
	Line(double startX, double startY, double endX, double endY) {
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
	}
	
	Line() {
		
	}
	
	Line(Point startPt, Point endPt) {
		this.startX = startPt.x;
		this.startY = startPt.y;
		this.endX = endPt.x;
		this.endY = endPt.y;
	}
	
	public Point getStartPoint() {
		return new Point(startX, startY);
	}
	
	public Point getEndPoint() {
		return new Point(endX, endY);
	}
	
	public void setStartX(double startX) {
		length = -1;
		this.startX = startX;
	}
	
	public void setStartY(double startY) {
		length = -1;
		this.startY = startY;
	}
	
	public void setEndX(double endX) {
		length = -1;
		this.endX = endX;
	}
	
	public void setendY(double endY) {
		length = -1;
		this.endY = endY;
	}
	
	public double getStartX() {
		return this.startX;
	}
	
	public double getStartY() {
		return this.startY;
	}
	
	public double getEndX() {
		return this.endX;
	}
	
	public double getEndY() {
		return this.endY;
	}
	
	public double length() {
		if(length == -1) {
			length =  Math.sqrt(Math.pow(startX-endX, 2) + Math.pow(startY-endY, 2));
		}
		return length;
	}
	
	public Point midPoint() {
		Point pt = new Point();
		
		pt.x = (startX + endX) / 2;
		pt.y = (startY + endY) / 2;
		
		return pt;
	}
	
	public double slope() {
		return -((startY - endY) / (startX - endX)); //Slopes are reversed in OpenCV since y goes up as you go down the image
	}
	
	public double angle() {
		double m = slope();
		return Math.toDegrees(Math.atan(1/m));
		
	}
}
