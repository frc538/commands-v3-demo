package first.robot.mechanism;

import org.wpilib.command3.Command;
import org.wpilib.command3.Mechanism;

public class NumberMechanism implements Mechanism {
    public Command count() {
        return run(coroutine -> {
            for (int i = 0 ; i < 100 ; i++) {
                System.out.println(i + 1);
                coroutine.yield();
            }
        })
        .named("numbers");
    }
}
