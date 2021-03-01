package frc.robot.ninjaLib;

public class Controls {

  private final Gamepad gamepad = new Gamepad(0);

  public Gamepad getDriverControls() {
    return gamepad;
  }
}