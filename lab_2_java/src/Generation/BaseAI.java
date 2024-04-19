package Generation;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Vector;

public abstract class BaseAI extends Thread {
    private static int _velocity = 50;
    private boolean _isRunning = true;
    protected Vector<House> houses;
    protected JPanel graphicsPanel;

    public BaseAI(Vector<House> Houses, JPanel GraphicsPanel) {
        houses = Houses;
        graphicsPanel = GraphicsPanel;
    }

    protected void setRunStatus(boolean status) {
        _isRunning = status;
    }
    public boolean getRunStatus() { return _isRunning; }
    public void pauseAI() { setRunStatus(false); }
    public void resumeAI() {
        setRunStatus(true);
        notify();
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                if (!getRunStatus()) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else { process(); }
            }
            process();
            graphicsPanel.repaint();
            try {
                Thread.sleep(_velocity);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    abstract void process();
    public static void setVelocity(int speed) {
        _velocity = speed;
    }
    public static int getVelocity() { return _velocity; }
}
