/*
Name: Ultrasonic Setup and Arduino to Roborio Communication
Author: Richard McHorgh (http://mchorghwebdesign.com)
Started: 18 Jan 2018
*/
const int pwPin1 = 3;
long sensor, mm, inches;

void setup() {
  Serial.begin(9600);
  pinMode(pwPin1, INPUT);
}

void read_sensor () {
  sensor = pulseIn(pwPin1, HIGH);
  mm = sensor;
  inches = mm/25.4;
}

void print_range(){
  Serial.print(mm);
  Serial.print("mm ");
  Serial.print(inches);
  Serial.println("in");
}

void loop() {
  read_sensor();
  print_range();
  delay(100);
}
