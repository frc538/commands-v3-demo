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
            // awaitAll starts all commands provided and does not proceed until ALL are finished
            coroutine.awaitAll(
                mRobot.numbers.count(),
                mRobot.letters.letters()
            );

            // This is likely what was intended when the original fork-only example was first made
            // This allows both commands to run in parallel until both are completed.
            // One of the commands finishing sooner does not prevent the other command from executing

            // This is similar to the fork-park example, EXCEPT the parent command does finish on its own
        })
        .named("Composition Test");
    }
}
