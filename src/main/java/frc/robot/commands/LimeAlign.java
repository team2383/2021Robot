package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.HAL;


public class LimeAlign extends Command {
    public  LimeAlign(){
      execute();
    }


    @Override
    protected void execute(){
        // if offset is greater than a certain number 


        double xOffset = HAL.limelight.xOffset();
        if(xOffset > 1 || xOffset < -1 ){
          HAL.drive.FLDrive.set(ControlMode.PercentOutput,HAL.limelight.xOffset()/54);
          HAL.drive.FRDrive.set(ControlMode.PercentOutput,HAL.limelight.xOffset()/54);
        } 

        else{
          HAL.drive.FLDrive.set(ControlMode.PercentOutput, -0.0);
          HAL.drive.FRDrive.set(ControlMode.PercentOutput ,0.0);
          end();
        }
    }



    @Override
    protected boolean isFinished() {
      double xOffset = HAL.limelight.xOffset();
      if(xOffset < 1 && xOffset > -1 ){
        return true;
      }
      return false;
    }
}