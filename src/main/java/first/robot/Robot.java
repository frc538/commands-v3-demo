// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package first.robot;

import org.wpilib.command3.Scheduler;
import org.wpilib.epilogue.Epilogue;
import org.wpilib.epilogue.Logged;
import org.wpilib.framework.OpModeRobot;

import first.robot.mechanism.CounterMechanism;
import first.robot.mechanism.LetterMechanism;
import first.robot.mechanism.NumberMechanism;

@Logged
public class Robot extends OpModeRobot {
  public final CounterMechanism counter = new CounterMechanism();
  public final LetterMechanism letters = new LetterMechanism();
  public final NumberMechanism numbers = new NumberMechanism();

  public Robot() {}

  @Override
  public void robotPeriodic() {
    Scheduler.getDefault().run();
    Epilogue.update(this);
  }
}
