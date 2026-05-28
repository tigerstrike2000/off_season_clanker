// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase{
    public TalonFX intakeSpinny;
    public SparkMax leftIntake;
    public SparkMax rightIntake;
    public DutyCycleEncoder intakeEncoder;
    public double intakeEncoderValue;

    
    public IntakeSubsystem() {
        leftIntake = new SparkMax(Constants.IntakeConstants.leftIntakeID, MotorType.kBrushless);
        rightIntake = new SparkMax(Constants.IntakeConstants.rightIntakeID, MotorType.kBrushless);
        intakeSpinny = new TalonFX(Constants.IntakeConstants.intakeSpinnyID);
        intakeEncoder = new DutyCycleEncoder(Constants.IntakeConstants.intakeEncoderID);

    }
}
