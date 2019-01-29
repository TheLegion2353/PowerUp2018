package org.usfirst.frc.team2353.robot;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class MainVisionTargetDetection {
	public double[] normal() throws IOException {
		//Loading the OpenCV core library  
	      System.loadLibrary( Core.NATIVE_LIBRARY_NAME ); 
	     
	      //Instantiating the Imagecodecs class 
	      Imgcodecs imageCodecs = new Imgcodecs(); 
	     
	      //Reading the Image from the file  
	      Mat mat = imageCodecs.imread("C:\\Users\\Robotics\\Pictures\\Screenshots\\a.jpg");
	      
	      Mat processed = new Mat();
	      
	      Imgproc.blur(mat, processed, new Size(7, 7));
	      
	      Scalar minValues = new Scalar(0 / 2, 0 * 2.55, 97 * 2.55);
	      Scalar maxValues = new Scalar(360 / 2, 100 * 2.55, 100 * 2.55);
		  
		  Imgproc.cvtColor(processed, processed, Imgproc.COLOR_BGR2HSV);
		  
		  Core.inRange(processed, minValues, maxValues, processed);
	      
		  Mat dilateElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(24, 24));
		  Mat erodeElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(12, 12));

		  Imgproc.erode(processed, processed, erodeElement);
		  Imgproc.erode(processed, processed, erodeElement);

		  Imgproc.dilate(processed, processed, dilateElement);
		  Imgproc.dilate(processed, processed, dilateElement);
		  
		  List<MatOfPoint> contours = new ArrayList<>();
		  Mat hierarchy = new Mat();
		  
		  Imgproc.findContours(processed, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);
		  // if any contour exist...
		  if (hierarchy.size().height > 0 && hierarchy.size().width > 0)
		  {
			  
		          // for each contour, display it in blue
		          for (int idx = 0; idx >= 0; idx = (int) hierarchy.get(0, idx)[0])
		          {
		                  Imgproc.drawContours(mat, contours, idx, new Scalar(250, 0, 0));
		          }
		  }
		  
		  //https://stackoverflow.com/questions/18581633/fill-in-and-detect-contour-rectangles-in-java-opencv
		  /*
		  Scalar green = new Scalar(81, 190, 0);
		  for(int i = 0; i < contours.size(); i++) {
		    RotatedRect rotatedRect = Imgproc.minAreaRect(new MatOfPoint2f(contours.get(i).toArray()));
		    drawRotatedRect(processed, rotatedRect, green, 4);
		  }
		  */
		  
		  //https://stackoverflow.com/questions/38748508/java-opencv-rectangle-detection-with-hough-transform
		  MatOfPoint2f matOfPoint2f = new MatOfPoint2f();
		  MatOfPoint2f approxCurve = new MatOfPoint2f();
		  
		  double[] returnVar = {-1, -1};
		  

 		  // if any contour exist...
		  if (hierarchy.size().height > 0 && hierarchy.size().width > 0)
		  {
			  	List<Line> centerLines = new ArrayList<>();
			  	List<Rect> centerLinesRects = new ArrayList<>();
			  	
		        // for each contour, display it in blue
		        for (int idx = 0; idx >= 0; idx = (int) hierarchy.get(0, idx)[0])
		        {
	                MatOfPoint contour = contours.get(idx);
	                Rect rect = Imgproc.boundingRect(contour);
	                
	                double contourArea = Imgproc.contourArea(contour);
	                matOfPoint2f.fromList(contour.toList());
	                Imgproc.approxPolyDP(matOfPoint2f, approxCurve, Imgproc.arcLength(matOfPoint2f, true) * 0.05, true);
	                
	                MatOfPoint approxf1 = new MatOfPoint();
	                approxCurve.convertTo(approxf1, CvType.CV_32S);
	                List<MatOfPoint> contourTemp = new ArrayList<>();
	                contourTemp.add(approxf1);
	                Imgproc.drawContours(mat, contourTemp, 0, new Scalar(0, 0, 0), 10);
	                
	                Point[] points = contour.toArray();
	                
	                if(approxCurve.total() == 4) {
	                	double ratio = Math.abs(1 - (double) rect.width / rect.height);
	                	
	                	String text = "";
	                	if(ratio <= 0.02) {
                      	text = "SQU";
                      }
	                  else {
	                		text = "RECT";
                      	
	                		//This takes any parallelograms and turns them into rectangles.
	                      	RotatedRect rotatedRect = Imgproc.minAreaRect(new MatOfPoint2f(contour.toArray()));
	                      	Point[] vertices = new Point[4];
	                  	    rotatedRect.points(vertices);
	                  	    
	                      	for(int i = 0; i < vertices.length; i++) {
	                      		System.out.println(vertices[i].x + " " + vertices[i].y);
	                      	}
	                      	
	                      	double dist1 = distance(vertices[0].x, vertices[0].y, vertices[1].x, vertices[1].y);
	                      	double dist2 = distance(vertices[0].x, vertices[0].y, vertices[2].x, vertices[2].y);
	                      	double dist3 = distance(vertices[0].x, vertices[0].y, vertices[3].x, vertices[3].y);
	                      	
	                      	double longest = Math.max(Math.max(dist1, dist2), dist3);
	                      	
	                      	int furthestPoint = 0;
	                      	if(longest == dist1) {
	                      		furthestPoint = 1;
	                      	}
	                      	else if(longest == dist2) {
	                      		furthestPoint = 2;
	                      	}
	                      	else {
	                      		furthestPoint = 3;
	                      	}
	                      	
	                      	Line[] lines = new Line[4];
	                      	int count = 0;
	                      	for(int i = 1; i < 4; i++) {
	                      		if(i == furthestPoint) {
	                      			continue;
	                      		}
	                      		Line line = new Line(vertices[0].x, vertices[0].y, vertices[i].x, vertices[i].y);
	                      		lines[count] = line;
	                      		count++;
	                      	}
	                      	for(int i = 1; i < 4; i++) {
	                      		if(i == furthestPoint) {
	                      			continue;
	                      		}
	                      		Line line = new Line(vertices[furthestPoint].x, vertices[furthestPoint].y, vertices[i].x, vertices[i].y);
	                      		lines[count] = line;
	                      		count++;
	                      	}
	                      	
	                      	for(int i = 0; i < lines.length; i++) {
	                      		Imgproc.line (
		                        	         mat,                    //Matrix obj of the image
		                        	         new Point(lines[i].getStartX(), lines[i].getStartY()),        //p1
		                        	         new Point(lines[i].getEndX(), lines[i].getEndY()),       //p2
		                        	         new Scalar(0, 0, 255),     //Scalar object for color
		                        	         5                          //Thickness of the line
		                        	      );
	                      	}
	                      	
	                      	Line[] shortestLines = new Line[2];
	                      	shortestLines[0] = lines[0];
	                      	
	                      	for(int i = 1; i < lines.length; i++) {
	                      		if(Math.abs(lines[i].length() - shortestLines[0].length()) <= shortestLines[0].length() * 0.01) {
	                      			shortestLines[1] = lines[i];
	                      		}
	                      		else if(lines[i].length() < shortestLines[0].length()) {
	                      			shortestLines[0] = lines[i];
	                      		}
	                      	}
	                      	
	                      	/*
	                      	for(int i = 0; i < shortestLines.length; i++) {
	                      		Imgproc.line (
		                        	         mat,                    //Matrix obj of the image
		                        	         new Point(shortestLines[i].getStartX(), shortestLines[i].getStartY()),        //p1
		                        	         new Point(shortestLines[i].getEndX(), shortestLines[i].getEndY()),       //p2
		                        	         new Scalar(255, 0, 0),     //Scalar object for color
		                        	         5                          //Thickness of the line
		                        	      );
	                      	}
	                      	*/
	                      	
	                      	Line centerLine = new Line(shortestLines[0].midPoint(), shortestLines[1].midPoint());
	                      	
	                      	if(!(Math.abs(centerLine.angle()) > 45 || Math.abs(centerLine.angle()) < 5)) {
	                      		Imgproc.line (
		                        	         mat,                    //Matrix obj of the image
		                        	         centerLine.getStartPoint(),        //p1
		                        	         centerLine.getEndPoint(),       //p2
		                        	         new Scalar(0, 255, 0),     //Scalar object for color
		                        	         5                          //Thickness of the line
		                        	      );
	                      		drawRotatedRect(mat, rotatedRect, new Scalar(0, 0, 255), 4);
	                      		
	                      		centerLines.add(centerLine);
	                      		centerLinesRects.add(rotatedRect.boundingRect());
	                      	}
	                	}
	                }
	                System.out.println("");
		        }
		        
		        if(centerLines.size() >= 2) {
		        	//Sort center lines left to right
	                Line temp;
	                Rect tempRect;
	                
	                for (int i = 0; i < centerLines.size()-1; i++)
	                {
	                    if(centerLines.get(i).midPoint().x > centerLines.get(i + 1).midPoint().x)
	                    {
	                        temp = centerLines.get(i);
	                        tempRect = centerLinesRects.get(i);
	                        
	                        centerLines.set(i, centerLines.get(i + 1));
	                        centerLines.set(i + 1, temp);
	                        
	                        centerLinesRects.set(i, centerLinesRects.get(i + 1));
	                        centerLinesRects.set(i + 1, tempRect);
	                        
	                        i=-1;
	                    }
	                }
	                
	                Line line = null;
	                Rect[] rect = new Rect[2];
	                
	                for(int i = 0; i < centerLines.size() - 1; i++) {
	                	if(centerLines.get(i).slope() > 0 && centerLines.get(i + 1).slope() < 0) {	                		
	                		line = new Line(centerLines.get(i).midPoint(), centerLines.get(i + 1).midPoint());
	                		
	                		Point finalPt = line.midPoint();
	                		
	                		Imgproc.line (
                     	         mat,                    //Matrix obj of the image
                     	         finalPt,        //p1
                     	         finalPt,       //p2
                     	         new Scalar(255, 0, 242),     //Scalar object for color
                     	         5                          //Thickness of the line
                     	      );
	                		
	                		rect[0] = centerLinesRects.get(i);
	                		rect[1] = centerLinesRects.get(i + 1);
	                		
	                		break;
	                	}
	                }
	                
	                if(line != null) {
	                	double midPointX = (mat.size().width / 2);
	                	
	                	double ratioAreas = rect[0].area() / rect[1].area();
	                	
	                	Imgproc.line (
                    	         mat,                    //Matrix obj of the image
                    	         new Point(mat.size().width / 2, mat.size().height / 2),        //p1
                    	         new Point(mat.size().width / 2, mat.size().height / 2),       //p2
                    	         new Scalar(255, 0, 242),     //Scalar object for color
                    	         5                          //Thickness of the line
                    	      );
	                	
	                	double[] temp2 = {line.midPoint().x - midPointX, ratioAreas};
	                	returnVar = temp2;
	                }
		        }
		  }

		  
	      //Instantiate JFrame 
	      JFrame frame = new JFrame(); 

	      //Set Content to the JFrame 
	      frame.getContentPane().add(new JLabel(new ImageIcon(resize(MatToBufferedImage(mat), 1280, 720)))); 
	      frame.pack(); 
	      frame.setVisible(true);
	      
	      System.out.println("Image Loaded");
	      
	      return returnVar;
	}
	
	public static BufferedImage MatToBufferedImage(Mat in) throws IOException {
		  //Encoding the image 
	      MatOfByte matOfByte = new MatOfByte();       
	      Imgcodecs.imencode(".png", in, matOfByte); 

	      //Storing the encoded Mat in a byte array 
	      byte[] byteArray = matOfByte.toArray(); 

	      //Preparing the Buffered Image 
	      InputStream input = new ByteArrayInputStream(byteArray); 
	      return ImageIO.read(input); 
	}
	
	public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	} 
	
	private static double angle(Point pt1, Point pt2, Point pt0) {
	    double dx1 = pt1.x - pt0.x;
	    double dy1 = pt1.y - pt0.y;
	    double dx2 = pt2.x - pt0.x;
	    double dy2 = pt2.y - pt0.y;
	    return (dx1*dx2 + dy1*dy2)/Math.sqrt((dx1*dx1 + dy1*dy1)*(dx2*dx2 + dy2*dy2) + 1e-10);
	}
	
	public static void drawRotatedRect(Mat image, RotatedRect rotatedRect, Scalar color, int thickness) {
	    Point[] vertices = new Point[4];
	    rotatedRect.points(vertices);
	    MatOfPoint points = new MatOfPoint(vertices);
	    Imgproc.drawContours(image, Arrays.asList(points), -1, color, thickness);
	}
	
	public static double distance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
	}
}
