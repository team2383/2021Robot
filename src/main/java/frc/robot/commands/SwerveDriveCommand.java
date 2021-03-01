package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.HAL;
import frc.robot.ninjaLib.Gamepad;

public final class SwerveDriveCommand extends Command {

  DoubleSupplier throttle;
  DoubleSupplier strafe;
  DoubleSupplier yaw;
  private static final double DEADBAND = 0.15;
  private static final Gamepad controls = HAL.control.getDriverControls();

  public SwerveDriveCommand(DoubleSupplier throttle, DoubleSupplier strafe, DoubleSupplier yaw) {
    this.throttle = throttle;
    this.strafe = strafe;
    this.yaw = yaw;
    requires(HAL.drive);
  }

  @Override
  public void execute() {
   double throttle = deadband(controls.getLeftY() * -0.5);
   double strafe = deadband((controls.getRightTrigger()-controls.getLeftTrigger()) * 0.5);
   double yaw = deadband(controls.getRightX() * 0.5);
    HAL.drive.drive(throttle, strafe, yaw);
  }

//@Override
  public void end(boolean interrupted) {
    HAL.drive.drive(0.0, 0.0, 0.0);
}

@Override
protected boolean isFinished(){
    return false;
}

  private double deadband(double value) {
    if (Math.abs(value) < DEADBAND) return 0.0;
    return value;
  }
}
