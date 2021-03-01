package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import frc.robot.subsystems.*;
import frc.robot.ninjaLib.Controls;
import edu.wpi.first.wpilibj.SPI;

public class HAL {

    public static final AHRS navX = new AHRS(SPI.Port.kMXP);
    public static final SwerveDrive drive = new SwerveDrive();
    public static final Controls control = new Controls();
    // public static final Controls controls = new Controls();
    public static final LimelightSubsystem limelight = new LimelightSubsystem();
    public static final Feeder feeder = new Feeder();
    public static final Conveyor conveyor = new Conveyor();
    public static final Trigger trig = new Trigger();
    public static final Shooter shoot = new Shooter();
    public static final Hood hoodie = new Hood();
}