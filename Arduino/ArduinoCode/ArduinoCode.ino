#include <SPI.h>  
//#include <Pixy.h>

// This is the main Pixy object 
//Pixy pixy;
const int pwPin1 = 4;

void setup()
{
  Serial.begin(9600);
  Serial.print("Starting...\n");

  //pixy.init();
}

static int i = 0;
int j;
uint16_t blocks;
char buf[32]; 
int sendControl;

void loop()
{ 
  /*
  blocks = pixy.getBlocks();
  
  if (blocks)
  {
    i++;
    
    sendControl = Serial.read();
      
    if (sendControl == 49) {
      Serial.print(".");
      Serial.print(pulseIn(pwPin1, HIGH));
      Serial.println(".");
    }
    if (sendControl == 50) {
      Serial.print("/");
      Serial.print(pixy.blocks[j].width);
      Serial.println("/");
    }
  } 
 */ 
}

