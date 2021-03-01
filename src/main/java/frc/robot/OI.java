package frc.robot;

import java.util.function.DoubleSupplier;

import frc.robot.ninjaLib.Gamepad;
import frc.robot.commands.*;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI{
    
    //Gamepads
    public static final Gamepad driver = new Gamepad(0);
    
    //Drive Inputs
    public static DoubleSupplier throttle = () -> (driver.getLeftY());
    public static DoubleSupplier strafe = () -> (driver.getLeftX());
    //public static DoubleSupplier strafe = () -> (-driver.getRightTrigger()+driver.getLeftTrigger());
    public static DoubleSupplier yaw = () -> (driver.getRightX());

    public static Button revFlywheel = new JoystickButton(driver, Gamepad.BUTTON_A);
    public static Button lime = new JoystickButton(driver, Gamepad.BUTTON_B);
    public static Button chambernoT = new JoystickButton(driver, Gamepad.BUTTON_Y);
    public static Button fireAway = new JoystickButton(driver, Gamepad.BUTTON_X);
     
    public static Button chamberIN = new JoystickButton(driver, Gamepad.BUTTON_SHOULDER_RIGHT);
    public static Button chamberOUT = new JoystickButton(driver, Gamepad.BUTTON_SHOULDER_LEFT);
     
    public static Button hoodUP = new edu.wpi.first.wpilibj.buttons.POVButton(driver, 0);
    public static Button hoodDOWN = new edu.wpi.first.wpilibj.buttons.POVButton(driver, 180);
     
    // public static Button revFlywheel = new JoystickButton(driver, Gamepad.BUTTON_A);
    // public static Button lime = new JoystickButton(driver, Gamepad.BUTTON_B);
    // public static Button chambernoT = new JoystickButton(driver, Gamepad.BUTTON_Y);
    // public static Button fireAway = new JoystickButton(driver, Gamepad.BUTTON_X);
     
    // public static Button chamberIN = new JoystickButton(driver, Gamepad.BUTTON_SHOULDER_RIGHT);
    // public static Button chamberOUT = new JoystickButton(driver, Gamepad.BUTTON_SHOULDER_LEFT);
     
    // public static Button hoodUP = new edu.wpi.first.wpilibj.buttons.POVButton(driver, 0);
    // public static Button hoodDOWN = new edu.wpi.first.wpilibj.buttons.POVButton(driver, 180);
     
    public OI() {
                
    //     /**********************
    //     *** DRIVER COMMANDS ***
    //     **********************/
        
        // HAL.drive.drive(throttle, strafe, yaw);
        // HAL.drive.drive(driver.getLeftY(), driver.getLeftX(), driver.getRightX());
        // HAL.drive.drive(0, .2, 0);

    //     revFlywheel.whileHeld(new SetShooter(-0.8));
    //     revFlywheel.whenReleased(new SetShooter(0.0));

    //     lime.whileHeld(new LimeAlign());

    //     hoodUP.whileHeld(new SetHood(0.2));
    //     hoodDOWN.whileHeld(new SetHood(-0.2));

    //     chamberIN.whileHeld(new SetChamber(1, .75, 0.15)); // Feed IN
    //     chamberOUT.whileHeld(new SetChamber(-0.6, -0.6, 0.6)); // Feed OUT
    //     fireAway.whileHeld(new SetChamber(0.7, .75, -0.6));
    //     chambernoT.whileHeld(new SetChamber(0.6, 0.6, 0));

        revFlywheel.whileHeld(new SetShooter(-0.8));
        revFlywheel.whenReleased(new SetShooter(0.0));

        lime.whileHeld(new LimeAlign());

        hoodUP.whileHeld(new SetHood(0.2));
        hoodDOWN.whileHeld(new SetHood(-0.2));

        chamberIN.whileHeld(new SetChamber(1, .75, 0.15)); // Feed IN
        chamberOUT.whileHeld(new SetChamber(-0.6, -0.6, 0.6)); // Feed OUT
        fireAway.whileHeld(new SetChamber(0.7, .75, -0.6));
        chambernoT.whileHeld(new SetChamber(0.6, 0.6, 0));
        }   
    }