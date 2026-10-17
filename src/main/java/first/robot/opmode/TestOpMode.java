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
    }

    @Override
    public void start() { 
        mGamepad.button(0).onTrue(testCompositions());
    }

    private Command testCompositions() {
        return Command.noRequirements(coroutine -> {
            // This will start the count command to run in the background
            coroutine.fork(mRobot.numbers.count());

            // This will start the letters command to run in the background
            coroutine.fork(mRobot.letters.letters());

            // fork allows the parent command to continue as soon as the child command starts
            // unfortunately, if the parent command finished before the child commands do, they will be interrupted and will not finish normally

            // Park is used so that the command will NEVER finish on its own
            // It can be interrupted by another command cancelling it (due to priority rules, etc.)
            // But it will not just "reach an end" on its own
            coroutine.park();

            // In this case, since the parent command is never finished or interrupted, the child commands are free to finish
            // But the parent command continues to run even after they do.
        })
        .named("Composition Test");
    }
}
