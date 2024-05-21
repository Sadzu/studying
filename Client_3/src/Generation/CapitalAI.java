package Generation;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Vector;

public class CapitalAI extends BaseAI {
    private static int _count = 0;
    private final String _threadName = "WoodenAI-" + _count;

    public CapitalAI(Vector<House> houses, JPanel graphics) {
        super(houses, graphics);
        _count++;
        setName(_threadName);
    }

    @Override
    synchronized void process() {
        for (House house : houses) {
            if (house instanceof Capital) {
                house.move(getRunStatus());
            }
        }
    }
}
