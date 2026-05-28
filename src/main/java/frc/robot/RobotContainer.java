// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.Autos;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import frc.robot.subsystems.swervedrive.Vision;
import swervelib.SwerveDrive;
import swervelib.SwerveInputStream;

import java.io.File;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandPS5Controller;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  public Vision vision;
  public SwerveDrive swerveDrive;
  public SwerveSubsystem swerveSubsystem = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(), "swerve"));
  public final CommandXboxController driver = new CommandXboxController(Constants.DriverID);
  public final CommandPS5Controller operator = new CommandPS5Controller(Constants.OperatorID);
  SwerveInputStream driveAngularVelocity = SwerveInputStream.of(swerveSubsystem.getSwerveDrive(),
                                                                () -> -operator.getLeftY(),
                                                                () -> -operator.getLeftX())
                                                            .withControllerRotationAxis( () -> -operator.getRightX())
                                                            .deadband(0.1)
                                                            .scaleTranslation(1.0)
                                                            .scaleRotation(1)
                                                            .allianceRelativeControl(true)
                                                            .robotRelative(false);
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    swerveSubsystem.setDefaultCommand(swerveSubsystem.driveFieldOriented(driveAngularVelocity));
    operator.R2().onTrue(Commands.runOnce(() -> driveAngularVelocity.scaleRotation(0.4).scaleTranslation(0.4)))
                          .onFalse(Commands.runOnce(() -> driveAngularVelocity.scaleRotation(1).scaleTranslation(1)));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return (null);
  }
}
