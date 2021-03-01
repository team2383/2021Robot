package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.HAL;



/**
 * Add your docs here.
 */
public class LimelightSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private double get(String name) {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry(name).getDouble(0);
  }

  public boolean hasTargets() {
    return get("tv") > 0;
  }

  public double pipeline() {
    return get("getpipe");
  }

  public double xOffset() {
    return get("tx");
  }

  public double yOffset() {
    return get("ty");
  }

  public double area() {
    return get("ta");
  }

  public double skew() {
    return get("ts");
  }

  public double latency() {
    return get("tl");
  }

  public double shortSideLength() {
    return get("tshort");
  }

  public double longSideLength() {
    return get("tlong");
  }

  public double horizontalLength() {
    return get("thoriz");
  }

  public double verticalLength() {
    return get("tvert");
  }

  public double camtran()
  {
    return get("camtran");
  }

  public void johnnyBoy(){
  //by intake ft - ta
  // rpm 2500
  //1ft - 3.645 h = 1478
  //2ft - 3.26  h = 1974
  //3ft - 2.72  h = 2383
  //4ft - 2.57  h = 2019
  //5ft - 2.33  h = 2625
  //6ft - 2.18  h = 2400
  // rpm 2700
  //7ft - 1.84  h = 2309
  //8ft - 1.7   h = 2297
  // rpm 2900
  //9ft - 
  //10ft -
  //11ft -
  //12ft -
  //13ft -
  //14ft -
  //15ft -
  //16ft -
      if (HAL.limelight.area() > 2 || hasTargets() == false){
          // HAL.hood.moveto(0);
      }

      else if ( HAL.limelight.area() < 1.5 && 1<HAL.limelight.area()){
          // HAL.hood.moveto(1738);
      }
      
      else{
          // HAL.hood.moveto(3765);
      }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void setPipeline(double pipeline) {
		NetworkTableEntry pipelineEntry = NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline");
    	pipelineEntry.setNumber(pipeline);
    }
}
