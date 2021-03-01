package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import static frc.robot.HAL.conveyor;
import static frc.robot.HAL.trig;
import static frc.robot.HAL.feeder;


public class SetChamber extends Command {

  double outputF;
  double outputC;
  double outputT;
 
 public SetChamber(double outputF, double outputC, double outputT) {
     this.outputF = outputF;
     this.outputC = outputC;
     this.outputT = outputT;
 }
 

 @Override
 protected void initialize() {
     //
 }

 
 @Override
 protected void execute() {
  feeder.spin(outputF);
  conveyor.spin(outputC);
  trig.spin(outputT);
 }

 @Override
 protected boolean isFinished() {
     return false;
 }

 @Override
 protected void end() {
  feeder.spin(0);
  conveyor.spin(0);
  trig.spin(0);
 }

 @Override
 protected void interrupted() {
   end();
 }
}
