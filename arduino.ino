#include <PRIZM.h>                    
#include <TELEOP.h>                   

PRIZM prizm;                         
PS4 ps4;                              

// My variables
int speed_movement = 100;
int speed_turning = 50;
int Servo1; // flag
int Servo2 = 0; // fork lift

int mode = 1;

void setup() {
 prizm.PrizmBegin();                  
 prizm.setMotorInvert(2,1);  
 ps4.setDeadZone(LEFT,10);           
 ps4.setDeadZone(RIGHT,10); 
}

void loop() {
  ps4.getPS4();   
  
  //toggle modes
  if(ps4.Button(TRIANGLE)) // controller mode
  {
    mode = 1;
  }
  else if(ps4.Button(CIRCLE)) // safe mode
  {
    mode = 2;
  }
  
  if(mode == 1) 
  {
    ps4.setLED(GREEN);
    
    // moving forward and backward
    if (ps4.Button(DOWN)) {
      prizm.setMotorPower(1,speed_movement);
      prizm.setMotorPower(2,speed_movement);
    }
    else if(ps4.Button(UP)) {
      prizm.setMotorPower(1,-speed_movement);
      prizm.setMotorPower(2,-speed_movement);
    }
    else
    {
      prizm.setMotorPower(1,0);
      prizm.setMotorPower(2,0);
    }
  
    // turning
    if (ps4.Button(L2) == 1)
    {
      prizm.setMotorPower(1,speed_turning);
      prizm.setMotorPower(2,-speed_turning);
    }
    else if (ps4.Button(R2) == 1)
    {
      prizm.setMotorPower(1,-speed_turning);
      prizm.setMotorPower(2,speed_turning);
    }
    else
    {
      prizm.setMotorPower(1,0);
      prizm.setMotorPower(2,0);
    }
    
    // wave the flag
    Servo1 = ps4.Servo(RX);                   
    prizm.setServoPosition(1, Servo1);
    
    // end effector
    if(ps4.Stick(LY) < 118 && Servo2 > -15)
    {
      Servo2 -= 15;
    }
    else if(ps4.Stick(LY) > 138 && Servo2 < 100)
    {
      Servo2 += 15;
    }
    prizm.setServoPosition(2, 125 + Servo2);
  }
  else if(mode == 2)
  {
    ps4.setLED(RED);
  }
}                                                                                   
