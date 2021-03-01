package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.SetTrigger;

public class Trigger extends Subsystem {
    WPI_VictorSPX trigger = new WPI_VictorSPX(RobotMap.TRIGGER_PORT);

    public Trigger() 
    {
      configMotorController();
    }
    public void configMotorController(){
      trigger.setNeutralMode(NeutralMode.Brake);
      trigger.setInverted(true);
    }
    
    public void spinLow (){
        trigger.set(ControlMode.PercentOutput, -0.15);
    }

    public void spinMedium (){
        trigger.set(ControlMode.PercentOutput, -1);
    }

    public void spin (double speed){
        trigger.set(ControlMode.PercentOutput, speed);
    }

    public void out (){
        trigger.set(ControlMode.PercentOutput, 1);
    }

    public void outSlow (){
      trigger.set(ControlMode.PercentOutput, 0.1);
  }

    public void interval_trigger(double interval){
      //System.out.println("start");
      double startTime = Timer.getFPGATimestamp();
      this.spinLow();
      while(Timer.getFPGATimestamp() <= (startTime + interval)) {
        ;
      }
      this.off();
      System.out.println("end");
    }


    public void off (){
        trigger.set(ControlMode.PercentOutput, 0);
    }

        public void initDefaultCommand() { 
          //  setDefaultCommand(new SetTrigger(0.5));
        }
}