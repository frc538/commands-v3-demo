package first.robot.opmode;

import org.wpilib.command3.button.CommandGamepad;
import org.wpilib.opmode.PeriodicOpMode;
import org.wpilib.opmode.Utility;

import first.robot.Robot;

@Utility
public class TestOpMode extends PeriodicOpMode {
    private final Robot mRobot;
    private final CommandGamepad mGamepad = new CommandGamepad(0);

    public TestOpMode(Robot robot) {
        mRobot = robot;
    }

    @Override
    public void start() { 
        mRobot.counter.setDefaultCommand(mRobot.counter.count());
        mGamepad.button(0).onTrue(mRobot.counter.reset());
    }
}
