/*
Name: Ultrasonic Setup and Arduino to Roborio Communication
Author: Richard McHorgh (http://mchorghwebdesign.com)
Started: 18 Jan 2018
*/
#include "Wire.h"

const int anPin = 0;
long anVolt, mm, inches, feet;

void setup() {
  Serial.begin(9600);

  Wire.begin(1);
}

void read_sensor(){
  anVolt = analogRead(anPin);
  mm = anVolt * 5;
  inches = mm/25.4;
  feet = inches*12;
}

void print_range(){
  Serial.print(mm);
  Serial.print("mm");
  Serial.print(inches);
  Serial.print("inches away");
  Serial.print(feet);
  Serial.println("feet away");
}

void transmit(){
  Wire.beginTransmission(2);
  Wire.write("Distance: ");
  Wire.write(mm);
  Wire.write("mm");
  Wire.endTransmission();

  delay(500);
}

void loop() {
  read_sensor();
  print_range();
  delay(100);
  transmit();
}
