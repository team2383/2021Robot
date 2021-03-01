package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;
  //
  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  //OI
  public static final int JOYSTICK_PORT = 0;


  //DRIVE
  public static final int RIGHT_MASTER_PORT = 3;
  public static final int RIGHT_SLAVE_PORT = 4;
  public static final int LEFT_MASTER_PORT = 2;
  public static final int LEFT_SLAVE_PORT = 1;

  //CHAMBER
  public static final int FEEDER_PORT = 9;
  public static final int CONVEYOR_PORT = 10;
  public static final int TRIGGER_PORT = 11;
  public static final int SHOOTER_MASTER_PORT = 14;
  public static final int SHOOTER_FOLLOWER_PORT = 13;

  //HOOD
  public static final int HOOD_PORT = 12;
  

  

}
