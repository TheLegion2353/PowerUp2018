#include <SPI.h>  
#include <Pixy.h>

// This is the main Pixy object 
Pixy pixy;
const int pwPin1 = 4;

int sendControl;
int lastRead;

void setup()
{
  Serial.begin(9600);
  Serial.print("Starting...\n");

  pixy.init();
}

uint16_t block;
char buf[32];

int countNotFound;

void loop()
{
  sendControl = Serial.read();
  
  if (sendControl == 49 || sendControl == 50) {
    lastRead = sendControl;
  }
  
  block = pixy.getBlocks();
  
  if(block != 0) {
    if (lastRead == 50) {
      lastRead = 0;
      Serial.print("/");
      Serial.print(pixy.blocks[0].x);
      Serial.println("/");
    }
  }
  else {
    if (lastRead == 50) {
        countNotFound++;
        if(countNotFound >= 1000) {
          countNotFound = 0;
          lastRead = 0;
          Serial.println("/-1/");
        }
    }
  }
  
  if (lastRead == 49) {
    lastRead = 0;
    Serial.print(".");
    Serial.print(pulseIn(pwPin1, HIGH));
    Serial.println(".");
  }
}

