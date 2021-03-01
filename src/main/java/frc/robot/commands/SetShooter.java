package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import static frc.robot.HAL.shoot;


public class SetShooter extends Command {

    private double output;
	  
    public SetShooter(double output) {
        this.output = output;
    }
    

    @Override
    protected void initialize() {
        //
    }
  
    
    @Override
    protected void execute() {
        shoot.out(output);
    }

    @Override
    protected boolean isFinished() {
      return isTimedOut();
        }
    

    @Override
    protected void end() {
        shoot.stop();

    }

    @Override
    protected void interrupted() {
    	end();
    }
}