package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import static frc.robot.subsystems.Hood.hoodc;

import com.ctre.phoenix.motorcontrol.ControlMode;


public class SetHood extends Command {

    double output;
	  
    public SetHood(double output) {
        this.output = output;
    }
    

    @Override
    protected void initialize() {
        //
    }
  
    
    @Override
    protected void execute() {
        hoodc.set(ControlMode.PercentOutput, output);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
    
    @Override
    protected void end() {
        hoodc.set(ControlMode.PercentOutput, 0);
    }

    @Override
    protected void interrupted() {
    	end();
    }
}
