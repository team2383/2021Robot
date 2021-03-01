package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import static frc.robot.HAL.trig;


public class SetTrigger extends Command {
     double output;
	  
    public SetTrigger(double output) {
        this.output = output;
    }
    

    @Override
    protected void initialize() {
        //
    }
  
    
    @Override
    protected void execute() {
        trig.spin(output);
    }

    @Override
    protected boolean isFinished() {
      return isTimedOut();
        }
    

    @Override
    protected void end() {
        trig.spin(0);

    }

    @Override
    protected void interrupted() {
    	end();
    }
}