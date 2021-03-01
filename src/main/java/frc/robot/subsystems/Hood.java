package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;

import frc.robot.RobotMap;
import frc.robot.commands.SetHood;
import frc.robot.HAL;

public class Hood extends Subsystem{
   public static WPI_TalonSRX hoodc = new WPI_TalonSRX(RobotMap.HOOD_PORT);
   
    public Hood() 
    {
        hoodc.setSensorPhase(true);
        hoodc.setInverted(true);
    }


    public void slowMoveUP (){
        // up is negative % output
        // double speed = hoodc.getMotorOutputPercent();
        // if(this.getHoodPosition() > 6003 - 50) { //6400 is all the way up
        //     if (speed < 0){
        //         // hoodc.set(ControlMode.PercentOutput, 0);
        //     }
        // }
        // else {
  
        //     hoodc.set(ControlMode.PercentOutput, -0.15);
        // }
        hoodc.set(ControlMode.PercentOutput, 0.15);
    }

    public void moveup(boolean ready){
        if(ready){
            hoodc.set(ControlMode.PercentOutput, .1);
            }
            else{
                hoodc.set(ControlMode.PercentOutput, 0);
            }
    }

    public void movedown(boolean ready){
        if(ready){
        hoodc.set(ControlMode.PercentOutput, -0.1);
        }
        else{
            hoodc.set(ControlMode.PercentOutput, 0);
        }
    }

    public void slowMoveDown (){
        // double speed = hoodc.getMotorOutputPercent();
        // if(this.getHoodPosition() < 50) {
        //     if (speed > 0){
        //         // hoodc.set(ControlMode.PercentOutput, 0);
        //     }
        // }
        // else {
        //     hoodc.set(ControlMode.PercentOutput, 0.15);
        // }
        hoodc.set(ControlMode.PercentOutput, -0.15);
    }
    public void johnIsJohn(){
        double speed = hoodc.getMotorOutputPercent();
        if(this.getHoodPosition() > -6003 + 50 && speed < 0) { //6400 is all the way up
            hoodc.set(ControlMode.PercentOutput, 0);
        }
        else if(this.getHoodPosition() < -50 && speed > 0) {
                hoodc.set(ControlMode.PercentOutput, 0);
        }

        else if(HAL.limelight.area() >= 4.2){
            moveto(-55);
        }
        else if(HAL.limelight.area() <= 1.2){
            moveto(-3425);
        }
   
    }


    public void moveto(double pos){
        hoodc.set(ControlMode.Position, pos);
      }


    public void off (){
        hoodc.set(ControlMode.PercentOutput, 0);
    }

    
    // public void limeH(){
    //     double area = HAL.limelight.area();
    //     boolean hasTarget = HAL.limelight.hasTargets();
    //     double factor = 8; //random value
    //     double height;
    //     height = area*factor;

    //     if(hasTarget){
    //         if (getHoodPosition() > 3700 && hoodc.getMotorOutputPercent() < 0){
    //             hoodc.set(ControlMode.PercentOutput, 0);
    //         }
    //         else if (getHoodPosition() < -2700 && hoodc.getMotorOutputPercent() > 0){
    //             hoodc.set(ControlMode.PercentOutput, 0);
    //         }
    //         else{
    //            moveto(height);
    //         } 
    //     }
    //     else{
    //         moveto(100);
    //     }
    // }


    public double getHoodPosition(){
        return hoodc.getSelectedSensorPosition();
    }

    public void initDefaultCommand() { 
      //  setDefaultCommand(new SetHood(0.5));
    }
}