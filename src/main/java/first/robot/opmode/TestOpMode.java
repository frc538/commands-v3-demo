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
            // awaitAny starts all commands provided and proceeds as soon as ANY of them finish
            coroutine.awaitAny(
                mRobot.numbers.count(),
                mRobot.letters.letters()
            );

            // This creates a parallel race, where many commands are running but the parent is allowed to proceed
            // as soon as a single command finishes - and it doesn't matter which one

            // This is similar to the fork-await example, but in this case, the "faster" command effectively
            // gets to be the awaited command, no matter which command it is
        })
        .named("Composition Test");
    }
}
