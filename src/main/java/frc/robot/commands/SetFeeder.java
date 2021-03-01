package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import static frc.robot.HAL.feeder;


public class SetFeeder extends Command {

     double output;
	  
    public SetFeeder(double output) {
        this.output = output;
    }
    

    @Override
    protected void initialize() {
        //
    }
  
    
    @Override
    protected void execute() {
        feeder.spin(output);
    }

    @Override
    protected boolean isFinished() {
      return isTimedOut();
        }
    

    @Override
    protected void end() {
        feeder.spin(0);

    }

    @Override
    protected void interrupted() {
    	end();
    }
}