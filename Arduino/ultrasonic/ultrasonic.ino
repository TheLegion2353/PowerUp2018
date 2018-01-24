/*
Name: Ultrasonic Setup and Arduino to Roborio Communication
Author: Richard McHorgh (http://mchorghwebdesign.com)
Started: 18 Jan 2018
*/
#include "Wire.h";

const int pwPin1 = 3;
long sensor, mm, inches;

void setup() {
  Wire.begin();
  Serial.begin(9600);
  pinMode(pwPin1, INPUT);
}

void readSensor () {
  sensor = pulseIn(pwPin1, HIGH);
  mm = sensor;
  inches = mm/25.4;
}

void printRangeS(){
  // Print the range over Serial
  Serial.println(mm);

}

void printRangeI() {
  // Print the range over I2C
  Wire.beginTransmission(1);
  Wire.write(mm);
  Wire.endTransmission();
}

void loop() {
  readSensor();

  //Comment out the function that you don't want to run
  printRangeS();
  //printRangeI();

  delay(100);

}
