package first.robot.mechanism;

import org.wpilib.command3.Command;
import org.wpilib.command3.Mechanism;

public class CounterMechanism implements Mechanism {
    private int mCount = 0;

    public Command count() {
        return run(coroutine -> {
            while (true) {
                System.out.println(mCount);
                mCount++;
                coroutine.yield();
            }
        })
        .withPriority(Command.DEFAULT_PRIORITY + 1)
        .named("count");
    }

    public Command reset() {
        return run(coroutine -> {
            mCount = 0;
        })
        .withPriority(Command.HIGHEST_PRIORITY)
        .named("reset");
    }
}
