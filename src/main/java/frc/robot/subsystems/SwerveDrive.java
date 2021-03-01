package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import frc.robot.ninjaLib.NinjaSwerveDriveConfig;
import frc.robot.ninjaLib.NinjaSwerveDrive;
import frc.robot.OI;
import frc.robot.commands.SwerveDriveCommand;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SwerveDrive extends Subsystem {

  public TalonSRX FLAzimuth = new TalonSRX(0);
  public TalonSRX FRAzimuth = new TalonSRX(1);
  public TalonSRX BLAzimuth = new TalonSRX(2);
  public TalonSRX BRAzimuth = new TalonSRX(3);

  public TalonFX FLDrive = new TalonFX(20);
  public TalonFX FRDrive = new TalonFX(21);
  public TalonFX BLDrive = new TalonFX(22);
  public TalonFX BRDrive = new TalonFX(23);

  private static final double ROBOT_LENGTH = 21.125; //assuming this is in inches
  private static final double ROBOT_WIDTH = 21.125;  

  private final NinjaSwerveDrive swerve = getSwerve();

  public SwerveDrive(){
    configMotorController();
  }

  public void configMotorController(){
    FLAzimuth.selectProfileSlot(0, 0);
    FRAzimuth.selectProfileSlot(0, 0);
    BLAzimuth.selectProfileSlot(0, 0);
    BRAzimuth.selectProfileSlot(0, 0);
    FLDrive.selectProfileSlot(0, 0);
    FRDrive.selectProfileSlot(0, 0);
    BLDrive.selectProfileSlot(0, 0);
    BRDrive.selectProfileSlot(0, 0);

    double Azimuth_P = 1.00;
    double Azimuth_I = 0.00;
    double Azimuth_D = 0.00;
    FLAzimuth.config_kP(0, Azimuth_P);
    FRAzimuth.config_kP(0, Azimuth_P);
    BLAzimuth.config_kP(0, Azimuth_P);
    BRAzimuth.config_kP(0, Azimuth_P);
    FLAzimuth.config_kI(0, Azimuth_I);
    FRAzimuth.config_kI(0, Azimuth_I);
    BLAzimuth.config_kI(0, Azimuth_I);
    BRAzimuth.config_kI(0, Azimuth_I);
    FLAzimuth.config_kD(0, Azimuth_D);
    FRAzimuth.config_kD(0, Azimuth_D);
    BLAzimuth.config_kD(0, Azimuth_D);
    BRAzimuth.config_kD(0, Azimuth_D);

    FLAzimuth.setSensorPhase(true);
    FRAzimuth.setSensorPhase(true);
    BLAzimuth.setSensorPhase(true);
    BRAzimuth.setSensorPhase(true);

    FLAzimuth.setInverted(false);
    FRAzimuth.setInverted(false);
    BLAzimuth.setInverted(false);
    BRAzimuth.setInverted(false);

    FLAzimuth.setNeutralMode(NeutralMode.Brake);
    FRAzimuth.setNeutralMode(NeutralMode.Brake);
    BLAzimuth.setNeutralMode(NeutralMode.Brake);
    BRAzimuth.setNeutralMode(NeutralMode.Brake);

    FLDrive.setNeutralMode(NeutralMode.Brake);
    FRDrive.setNeutralMode(NeutralMode.Brake);
    BLDrive.setNeutralMode(NeutralMode.Brake);
    BRDrive.setNeutralMode(NeutralMode.Brake);
  }

  public void drive(double forward, double strafe, double yaw) {
    swerve.drive(forward, strafe, yaw);
  }

  public void zeroAzimuthEncoders(){
    FLAzimuth.setSelectedSensorPosition(0);
    FRAzimuth.setSelectedSensorPosition(0);
    BLAzimuth.setSelectedSensorPosition(0);
    BRAzimuth.setSelectedSensorPosition(0);
  }

  private NinjaSwerveDrive getSwerve(){
    NinjaSwerveDriveConfig config = new NinjaSwerveDriveConfig();
    // config.wheels = getWheels();
    // config.gyro = new AHRS(SPI.Port.kMXP);
    config.length = ROBOT_LENGTH;
    config.width = ROBOT_WIDTH;
    config.gyroLoggingEnabled = true;
    config.summarizeTalonErrors = false;
    return new NinjaSwerveDrive();
  }

  public double getFLAzimuthPos(){
    return FLAzimuth.getSelectedSensorPosition();
  }

  public double getFRAzimuthPos(){
    return FRAzimuth.getSelectedSensorPosition();
  }

  public double getBLAzimuthPos(){
    return BLAzimuth.getSelectedSensorPosition();
  }

  public double getBRAzimuthPos(){
    return BRAzimuth.getSelectedSensorPosition();
  }

  @Override
  public void initDefaultCommand() {
    this.setDefaultCommand(new SwerveDriveCommand(OI.throttle, OI.strafe, OI.yaw));
  }
}
