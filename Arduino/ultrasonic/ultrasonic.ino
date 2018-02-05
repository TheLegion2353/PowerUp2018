/*
Name: Ultrasonic Setup and Arduino to Roborio Communication
Author: Richard McHorgh (http://mchorghwebdesign.com)
Started: 18 Jan 2018
*/
#include <SPI.h>
#include <Pixy.h>

Pixy pixy;

const int pwPin1 = 4;
long sensor, mm, inches;
int sendControl, powerCube;


void setup() {

  pinMode(pwPin1, INPUT);

  Serial.begin(9600);
  Serial.println("Pixy Starting");
  pixy.init();
}

void readSensor () {
  mm = pulseIn(pwPin1, HIGH);
  inches = mm/25.4;
}

void printRangeS() {
  sendControl = Serial.read();
  if (sendControl == 49) {
    readSensor();
    Serial.print(".");
    Serial.print(mm);
    Serial.print(".");
  }
  else if(sendControl == 50) {
    for( int i = 0; i < 10; i++) {
      readSensor();
    Serial.print(".");
    Serial.print(mm);
    Serial.print(".");
    }
  }
}

void pixyData() {
  static int i = 0;
  int j;
  uint16_t blocks;
  char buf[32];

  powerCube = 1;
  sendControl = Serial.read();
  
  if(sendControl == 51) {
    Serial.print(sendControl);
    blocks = pixy.getBlocks();
    if (blocks) {
      i++;
      if (i%50==0) {
        for (j=0; j<blocks; j++) {
          if (pixy.blocks[j].signature == powerCube) {
            Serial.print("/");
            Serial.print(pixy.blocks[j].x);
            Serial.print("/");
            Serial.print(pixy.blocks[j].width);
            Serial.print("/");
            Serial.print(pixy.blocks[j].height);
            Serial.println("/");
  
            pixy.blocks[j].print();
          }
        }
      }
    }
  }
  
}

void loop() {
  Serial.flush();
  printRangeS();
  pixyData();

}
