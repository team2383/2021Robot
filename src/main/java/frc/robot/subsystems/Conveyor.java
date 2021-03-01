package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Conveyor extends Subsystem{

  WPI_VictorSPX conveyorM = new WPI_VictorSPX(RobotMap.CONVEYOR_PORT);

  public Conveyor(){
    conveyorM.setInverted(false);
  }
  
  public void pull(){
      conveyorM.set(ControlMode.PercentOutput, .80);
  }

  public void off(){
    conveyorM.set(ControlMode.PercentOutput, 0);
  }

  public void out(){
    conveyorM.set(ControlMode.PercentOutput, -0.75);
  }

  public void fire(){
    conveyorM.set(ControlMode.PercentOutput, 1);
  }

  public void spin(double speed){
    conveyorM.set(ControlMode.PercentOutput, speed);
  }

    public void initDefaultCommand() {
        // 
    }
}
