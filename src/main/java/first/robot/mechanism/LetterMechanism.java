package first.robot.mechanism;

import org.wpilib.command3.Command;
import org.wpilib.command3.Mechanism;

public class LetterMechanism implements Mechanism {
    public Command letters() {
        return run(coroutine -> {
            for (char letter : "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray()) {
                System.out.println(letter);
                coroutine.yield();
            }
        })
        .named("letters");
    }
}
