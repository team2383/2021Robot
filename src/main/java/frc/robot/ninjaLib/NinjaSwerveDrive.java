package frc.robot.ninjaLib;

import java.util.function.DoubleConsumer;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.HAL;

// @SuppressWarnings("unused")
public class NinjaSwerveDrive {

  public static final int DEFAULT_ABSOLUTE_AZIMUTH_OFFSET = 0;
  private static final int WHEEL_COUNT = 4;
  private static final int TICKS = 4096;
//   private final AHRS gyro;
  private final double kLengthComponent;
  private final double kWidthComponent;
  private final double[] wd = new double[WHEEL_COUNT];
  private final double[] wa = new double[WHEEL_COUNT];
  private boolean FLIsInverted = false;
  private boolean FRIsInverted = false;
  private boolean BLIsInverted = false;
  private boolean BRIsInverted = false;
  protected DoubleConsumer driver;

  public NinjaSwerveDrive() {
    // gyro = config.gyro;
    // wheels = config.wheels;

    double length = 21.125;
    double width = 21.125;
    double radius = Math.hypot(length, width);
    kLengthComponent = length / radius;
    kWidthComponent = width / radius;
  }

/**
   * Return key that wheel zero information is stored under in WPI preferences.
   *
   * @param wheel the wheel number
   * @return the String key
   */
//   public static String getPreferenceKeyForWheel(int wheel) {
//     return String.format("%s/wheel.%d", NinjaSwerveDrive.class.getSimpleName(), wheel);
//   }

  public void drive(double forward, double strafe, double azimuth) {

    final double a = strafe - azimuth * kLengthComponent;
    final double b = strafe + azimuth * kLengthComponent;
    final double c = forward - azimuth * kWidthComponent;
    final double d = forward + azimuth * kWidthComponent;

    // wheel speed
    wd[0] = Math.hypot(b, d);
    wd[1] = Math.hypot(b, c);
    wd[2] = Math.hypot(a, d);
    wd[3] = Math.hypot(a, c);

    // wheel azimuth
    wa[0] = Math.atan2(b, d) * 0.5 / Math.PI;
    wa[1] = Math.atan2(b, c) * 0.5 / Math.PI;
    wa[2] = Math.atan2(a, d) * 0.5 / Math.PI;
    wa[3] = Math.atan2(a, c) * 0.5 / Math.PI;

    // normalize wheel speed
    final double maxWheelSpeed = Math.max(Math.max(wd[0], wd[1]), Math.max(wd[2], wd[3]));
    if (maxWheelSpeed > 1.0) {
      for (int i = 0; i < WHEEL_COUNT; i++) {
        wd[i] /= maxWheelSpeed;
      }
    }

    // if (wd[0] == 0) {
    //     driver.accept(0d);
    //     return;
    //   }

    wa[0] *= -TICKS;
    wa[1] *= -TICKS;
    wa[2] *= -TICKS;
    wa[3] *= -TICKS;

    double FLAzimuthPosition = HAL.drive.FLAzimuth.getSelectedSensorPosition(0);
    double FRAzimuthPosition = HAL.drive.FRAzimuth.getSelectedSensorPosition(0);
    double BLAzimuthPosition = HAL.drive.BLAzimuth.getSelectedSensorPosition(0);
    double BRAzimuthPosition = HAL.drive.BRAzimuth.getSelectedSensorPosition(0);
    double FLAzimuthError = Math.IEEEremainder(wa[0] - FLAzimuthPosition, TICKS);
    double FRAzimuthError = Math.IEEEremainder(wa[1] - FRAzimuthPosition, TICKS);
    double BLAzimuthError = Math.IEEEremainder(wa[2] - BLAzimuthPosition, TICKS);
    double BRAzimuthError = Math.IEEEremainder(wa[3] - BRAzimuthPosition, TICKS);

    SmartDashboard.putNumber("FLAzimuthError", FLAzimuthError);
    SmartDashboard.putNumber("FLAzimuthSpeed", wa[0]);

    FLIsInverted = Math.abs(FLAzimuthError) > 0.25 * TICKS;
    if (FLIsInverted) {
      FLAzimuthError -= Math.copySign(0.5 * TICKS, FLAzimuthError);
      wd[0] = -wd[0];
    }

    FRIsInverted = Math.abs(FRAzimuthError) > 0.25 * TICKS;
    if (FRIsInverted) {
      FRAzimuthError -= Math.copySign(0.5 * TICKS, FRAzimuthError);
      wd[1] = -wd[1];
    }

    BLIsInverted = Math.abs(BLAzimuthError) > 0.25 * TICKS;
    if (BLIsInverted) {
      BLAzimuthError -= Math.copySign(0.5 * TICKS, BLAzimuthError);
      wd[2] = -wd[2];
    }

    BRIsInverted = Math.abs(BRAzimuthError) > 0.25 * TICKS;
    if (BRIsInverted) {
      BRAzimuthError -= Math.copySign(0.5 * TICKS, BRAzimuthError);
      wd[3] = -wd[3];
    }

    HAL.drive.FLAzimuth.set(ControlMode.MotionMagic, FLAzimuthPosition + FLAzimuthError);
    HAL.drive.FRAzimuth.set(ControlMode.MotionMagic, FRAzimuthPosition + FRAzimuthError);
    HAL.drive.BLAzimuth.set(ControlMode.MotionMagic, BLAzimuthPosition + BLAzimuthError);
    HAL.drive.BRAzimuth.set(ControlMode.MotionMagic, BRAzimuthPosition + BRAzimuthError);

    HAL.drive.FLDrive.set(ControlMode.PercentOutput, wd[0]);
    HAL.drive.FRDrive.set(ControlMode.PercentOutput, wd[1]);
    HAL.drive.BLDrive.set(ControlMode.PercentOutput, wd[2]);
    HAL.drive.BRDrive.set(ControlMode.PercentOutput, wd[3]);
  }

  public double[] azimuthSpeed(){
      return wa;
  }
  
  public double[] driveSpeed(){
    return wd;
}


  /**
   * Stops all wheels' azimuth and drive movement. Calling this in the robots {@code teleopInit} and
   * {@code autonomousInit} will reset wheel azimuth relative encoders to the current position and
   * thereby prevent wheel rotation if the wheels were moved manually while the robot was disabled.
   */
  public void stop() {
    HAL.drive.FLAzimuth.set(ControlMode.PercentOutput, 0);
    HAL.drive.FRAzimuth.set(ControlMode.PercentOutput, 0);
    HAL.drive.BLAzimuth.set(ControlMode.PercentOutput, 0);
    HAL.drive.BRAzimuth.set(ControlMode.PercentOutput, 0);
    HAL.drive.FLDrive.set(ControlMode.PercentOutput, 0);
    HAL.drive.FRDrive.set(ControlMode.PercentOutput, 0);
    HAL.drive.BLDrive.set(ControlMode.PercentOutput, 0);
    HAL.drive.BRDrive.set(ControlMode.PercentOutput, 0);
  }

  /**
   * Save the wheels' azimuth current position as read by absolute encoder. These values are saved
   * persistently on the roboRIO and are normally used to calculate the relative encoder offset
   * during wheel initialization.
   *
   * <p>The wheel alignment data is saved in the WPI preferences data store and may be viewed using
   * a network tables viewer.
   *
   * @see #zeroAzimuthEncoders()
   */
//   public void saveAzimuthPositions() {
//     saveAzimuthPositions(Preferences.getInstance());
//   }

//   void saveAzimuthPositions(Preferences prefs) {
//     for (int i = 0; i < WHEEL_COUNT; i++) {
//       int position = wheels[i].getAzimuthAbsolutePosition();
//       prefs.putInt(getPreferenceKeyForWheel(i), position);
//     }
//   }

  /**
   * Set wheels' azimuth relative offset from zero based on the current absolute position. This uses
   * the physical zero position as read by the absolute encoder and saved during the wheel alignment
   * process.
   *
   * @see #saveAzimuthPositions()
   */
//   public void zeroAzimuthEncoders() {
//     zeroAzimuthEncoders(Preferences.getInstance());
//   }

//   void zeroAzimuthEncoders(Preferences prefs) {
//     for (int i = 0; i < WHEEL_COUNT; i++) {
//       int position = prefs.getInt(getPreferenceKeyForWheel(i), DEFAULT_ABSOLUTE_AZIMUTH_OFFSET);
//       wheels[i].setAzimuthZero(position);
//     }
//   }

  /**
   * Get the gyro instance being used by the drive.
   *
   * @return the gyro instance
   */
//   public AHRS getGyro() {
//     return gyro;
//   }

  /**
   * Unit testing
   * @return length
   */
  double getLengthComponent() {
    return kLengthComponent;
  }

  /**
   * Unit testing
   * @return width
   */
  double getWidthComponent() {
    return kWidthComponent;
  }

  /** Swerve Drive drive mode */
  public enum NinjaDriveMode {
    OPEN_LOOP,
    CLOSED_LOOP,
    TELEOP,
    TRAJECTORY,
    AZIMUTH
  }
}
