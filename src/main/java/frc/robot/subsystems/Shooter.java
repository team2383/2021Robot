package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.HAL;
import frc.robot.RobotMap;
import frc.robot.Constants;
import frc.robot.commands.*;


public class Shooter extends Subsystem{
  public double currentRPM=0;
  public double pastRPM=0;
  public double maxRPM=0;
  public double minRPM;//=maxRPM;
  public boolean minSet=false;
  public int timesRun=0;

  WPI_TalonFX shootMaster = new WPI_TalonFX(RobotMap.SHOOTER_MASTER_PORT);
  WPI_TalonFX shootFollower = new WPI_TalonFX(RobotMap.SHOOTER_FOLLOWER_PORT);

  public Shooter(){
    shootFollower.follow(shootMaster);
    shootMaster.setInverted(false);
    shootFollower.setInverted(true);
    shootMaster.setSensorPhase(true);
    configMotorController();
  }

  public void out(double power){
    shootMaster.set(-power);
    shootFollower.follow(shootMaster);
  }

  
  public double desiredRPM; //Max RPM is around 6280

  public void Run() {
    shootFollower.follow(shootMaster);
    desiredRPM = 2500;
    double velocity = desiredRPM * 2048.0 / 600.0; //was 2048
    shootMaster.set(ControlMode.Velocity, velocity);
  }

  public void alignThanShoot(){
    HAL.limelight.johnnyBoy();
    shootFollower.follow(shootMaster);
    desiredRPM = 2500;
    double velocity = desiredRPM * 2048.0 / 600.0; //was 2048
    shootMaster.set(ControlMode.Velocity, velocity);
    }
  


  public void shooterFire(double desiredRPM2) {
    shootFollower.follow(shootMaster);
    double velocity = desiredRPM2 * 2048.0 / 600.0; //was 2048
    shootMaster.set(ControlMode.Velocity, velocity);
  }

  public static double rpmToNative(double rpm, double countsPerRevolution){
    return rpm * countsPerRevolution / 600.0;
}
  

  public double differential(){
    double currentRPM = nativeToRPM(shootMaster.getSelectedSensorVelocity(), 2048);
    return desiredRPM - currentRPM;
  }

  public static double nativeToRPM(double nativeUnits, double countsPerRevolution) {
    return nativeUnits * 600.0 / 2048;
  }
  public double getRPM() {
    double currentRPM = nativeToRPM(shootMaster.getSelectedSensorVelocity(), 2048);
    return currentRPM;
  }

  public double getPercentageOutput(){
    return shootMaster.getMotorOutputPercent();
  }

  public double getPercentageOutputFollower(){
    return shootFollower.getMotorOutputPercent();
  }

public double getClosedLoopError(){
  return shootMaster.getClosedLoopError();
}




  public void configMotorController(){ //double nativeoutput
    // desired RPM : 5040
    // double maxvel = 21000.0;
    // double currentvel = shootMaster.getSelectedSensorVelocity();
    // double error = ((maxvel*nativeoutput) - currentvel);
    // double clerror = shootMaster.getClosedLoopError();
    shootMaster.configClosedloopRamp(0.25);
    shootMaster.config_kP(0, Constants.shooter_kP); //right now 0.071 3/7// .25                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
    shootMaster.config_kI(0, Constants.shooter_kI); //0.000115 || right now 0.0 3/7//0.000115
    shootMaster.config_kD(0, Constants.shooter_kD); //0.0 3/7//0.0
    shootMaster.config_kF(0, Constants.shooter_kF); //0.065(calculated mid Feb) //.0475 (calculated 2/25) 3/7//0.0475
    shootFollower.follow(shootMaster);
    // shootMaster.setInverted(true);
    // shootFollower.setInverted(false);
    shootMaster.setSensorPhase(false);
    //shootMaster.config_IntegralZone(0, 9);

    shootMaster.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);

    
    // shootMaster.configClosedloopRamp(.25);

  //  int smartMotionSlot = 0;
    //shootMaster.configMotionCruiseVelocity(100, smartMotionSlot);
    //shootMaster.configMotionAcceleration(100, smartMotionSlot);

    }

  public double getShooterVelocity(){
    return shootMaster.getSelectedSensorVelocity();
  }

  public void stop(){
    shootMaster.set(0);
    shootFollower.set(0);
    boolean limelightHeadache = false;
    System.out.println("MAX RPM: "+maxRPM);
    System.out.println("MIN RPM: " + minRPM);
    System.out.println("Min was set: "+minSet);
    System.out.println("Times run: "+timesRun);
  }

  public void in(){
    shootFollower.follow(shootMaster);
    shootMaster.set(-.75);
  } 

  public void fireOut(){
    //shoo
  }
  
  public void shoot(double output){
    //configMotorController(output);
    shootMaster.set(output);
    //shootFollower.set(-output);
  }

  //desired RPM : 5040
  // public void shootInfoDrop(double output){
  //   timesRun++;
  //   currentRPM=this.getRPM();
  //   System.out.println("CR "+currentRPM);
  //   System.out.println("PR "+pastRPM);
  //   if(currentRPM>maxRPM){
  //     maxRPM=currentRPM;
  //   } 
  //   if((Math.abs(currentRPM-pastRPM)<50)&&(!minSet)){
  //     minRPM=currentRPM;
  //     minSet=true;
  //   }
  //   if((currentRPM<minRPM)&&minSet){
  //     minRPM=currentRPM;
  //   }
  //   //System.out.println(this.getRPM());
  //   shootMaster.set(output);
  //   shootFollower.set(-output);
  //   System.out.println("Speed difference: "+(currentRPM-pastRPM));
  //   pastRPM=currentRPM;
    
  // }

  public void LimeS(){
  double area = HAL.limelight.area();
  double zone1areathreshold = 20; //random
  double zone2areathreshold = 40; //random
  double zone1output = 0.5; //random
  double zone2output = 0.7; //random
  double zone3output = 0.88; //random

  if (HAL.limelight.area() <= zone1areathreshold){
    shoot(zone1output);
  }

  else if (zone1areathreshold < area && area <= zone2areathreshold){
    shoot(zone2output);
  }

  else{
    shoot(zone3output);
    }
  }
  
  public void manualorlime(){
  boolean tracking = HAL.limelight.hasTargets();
  double manualoutput = 0.85;

  if (tracking){
    LimeS();
  }
    else{
      shoot(manualoutput);
    }
}
public void initDefaultCommand() { 
  //  setDefaultCommand(new SetShooter(0.8));
}

}

