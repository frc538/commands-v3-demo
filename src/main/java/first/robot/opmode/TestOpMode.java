package first.robot.opmode;

import org.wpilib.command3.Command;
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
        mGamepad.button(0).onTrue(testCompositions());
    }

    private Command testCompositions() {
        return Command.noRequirements(coroutine -> {
            // This will run the count command, counting to 26 before moving on
            coroutine.await(mRobot.numbers.count());

            // This will run the letters command, reciting the alphabet before moving on
            coroutine.await(mRobot.letters.letters());

            // await will not let the parent command proceed until the child command finishes
        })
        .named("Composition Test");
    }
}
