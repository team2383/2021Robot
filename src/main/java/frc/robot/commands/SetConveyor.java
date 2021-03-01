package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import static frc.robot.HAL.conveyor;


public class SetConveyor extends Command {
     double output;
	  
    public SetConveyor(double output) {
        this.output = output;
    }
    

    @Override
    protected void initialize() {
        //
    }
  
    
    @Override
    protected void execute() {
        conveyor.spin(output);
    }

    @Override
    protected boolean isFinished() {
      return false;
        }
    

    @Override
    protected void end() {
        conveyor.spin(0);

    }

    @Override
    protected void interrupted() {
    	end();
    }
}