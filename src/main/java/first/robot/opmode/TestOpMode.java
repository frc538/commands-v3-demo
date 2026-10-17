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
            // This will start the count command to run in the background
            coroutine.fork(mRobot.numbers.count());

            // This will start the letters command and not proceed until it is finished
            coroutine.await(mRobot.letters.letters());

            // In this case, the letters are guaranteed to reach their natural conclusion
            // The numbers are not though, because they were started via fork
            // Notice in the NumberMechanism that the counter now goes to 100, which makes it obvious that the letters command 
            // is what is keeping the parent command running for this example
           
        })
        .named("Composition Test");
    }
}
