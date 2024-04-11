package Generation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Habitat extends JPanel {
    private static Habitat _instance;

    private final Timer _timer;
    private int _simulationTime;
    private boolean _showTime;

    private final double _woodenChance;
    private final int _woodenTime;
    private int _woodenCount;
    private final double _capitalChance;
    private final int _capitalTime;
    private int _capitalCount;

    private final int _woodenAliveTime;
    private final int _capitalAliveTime;

    private final HouseCollections _collections = HouseCollections.getInstance();
    private WoodenAI _woodenAI = new WoodenAI(_collections.getHouses(), this);
    private CapitalAI _capitalAI = new CapitalAI(_collections.getHouses(), this);

    public static Habitat getInstance(int woodenTime, double woodenChance, int capitalTime, double capitalChance, int woodenAliveTime, int capitalAliveTime) {
        if (_instance == null) {
            _instance = new Habitat(woodenTime, woodenChance, capitalTime, capitalChance, woodenAliveTime, capitalAliveTime);
        }

        return _instance;
    }
    public static Habitat getInstance() {return _instance;}

    protected Habitat(int woodenTime, double woodenChance, int capitalTime, double capitalChance, int woodenAliveTime, int capitalAliveTime) {
        _simulationTime = 0;
        _showTime = true;

        _woodenTime = woodenTime;
        _woodenChance = woodenChance;
        _woodenCount = 0;
        _capitalTime = capitalTime;
        _capitalChance = capitalChance;
        _capitalCount = 0;

        _woodenAliveTime = woodenAliveTime;
        _capitalAliveTime = capitalAliveTime;

        _timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _simulationTime++;
                boolean flag = false;
                if (_simulationTime % _woodenTime == 0) {
                    _generateWooden(flag);
                    flag = true;
                }
                if (_simulationTime % _capitalTime == 0) {
                    _generateCapital(flag);
                    flag = true;
                }

                _collections.checkAlive(_simulationTime);
                _woodenAI.process();
                _capitalAI.process();

                repaint();
            }
        });
    }

    private void _generateWooden(boolean flag) {
        double random = Math.random();
        if (random <= _woodenChance) {
            Wooden newObject = new Wooden((int)(Math.random() * 1800), (int)(Math.random() * 900));
            newObject.setBornTime(_simulationTime);
            int id = (int)(Math.random() * 1000 + 1);
            newObject.setId(id);
            _collections.addToHouses(newObject);
            _woodenCount += 1;
            _collections.addToRandomId(id);
            if (flag) {
                _collections.addToBornTimeId((_simulationTime + _woodenAliveTime) * (-1), newObject);
            } else {
                _collections.addToBornTimeId(_simulationTime + _woodenAliveTime, newObject);
            }
        }
    }
    private void _generateCapital(boolean flag) {
        double random = Math.random();
        if (random <= _capitalChance) {
            Capital newObject = new Capital((int)(Math.random() * 1800), (int)(Math.random() * 900));
            newObject.setBornTime(_simulationTime);
            int id = (int)(Math.random() * 1000 + 1);
            newObject.setId(id);
            _collections.addToHouses(newObject);
            _collections.addToRandomId(id);
            _capitalCount += 1;
            if (flag) {
                _collections.addToBornTimeId((_simulationTime + _capitalAliveTime) * (-1), newObject);
            } else {
                _collections.addToBornTimeId(_simulationTime + _capitalAliveTime, newObject);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (House house : _collections.getHouses()) {
            g.drawImage(house.getImage(), house.get_xCoordinate(), house.get_yCoordinate(), 100, 100, null);
            g.setColor(Color.BLACK);
            g.drawString(house.getName(), house.get_xCoordinate(), house.get_yCoordinate());
        }

        if (_showTime) {
            g.setColor(Color.BLACK);
            g.drawString("Simulation time: " + _simulationTime + " seconds", 10, 20);
        }
    }

    public void showSimulationTime() {_showTime = true;}
    public void hideSimulationTime() {_showTime = false;}

    public void Start() {
        _timer.start();
        _woodenAI.start();
        _capitalAI.start();
    }
    public void Stop() {
        _timer.stop();
        _simulationTime = 0;
        _collections.clearCollections();
        _woodenAI.interrupt();
        _capitalAI.interrupt();
    }
    public void Pause() {
        _timer.stop();
    }
    public void Resume() {
        _timer.start();
    }

    public int getWoodenCount() {return _woodenCount;}
    public int getCapitalCount() {return _capitalCount;}
    public int getAllObjectsCount() {return _woodenCount + _capitalCount;}
    public int getSimulationTime() {return _simulationTime;}
    public boolean getShowTime() {return _showTime;}
    public int getWoodenAliveTime() {return _woodenAliveTime;}
    public int getCapitalAliveTime() {return _capitalAliveTime;}
    public void changeWoodenAIStatus() {
        if (_woodenAI.getRunStatus()) {
            _woodenAI.pauseAI();
        } else {
            _woodenAI.resumeAI();
        }
    }
    public void changeCapitalAIStatus() {
        if (_capitalAI.getRunStatus()) {
            _capitalAI.pauseAI();
        } else {
            _capitalAI.resumeAI();
        }
    }
    public void setAIPriority(int priority) {
        if (priority == 0) {
            _capitalAI.setPriority(Thread.MAX_PRIORITY);
            _woodenAI.setPriority(Thread.MIN_PRIORITY);
            Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        } else if (priority == 1) {
            _capitalAI.setPriority(Thread.MIN_PRIORITY);
            _woodenAI.setPriority(Thread.MAX_PRIORITY);
            Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        } else if (priority == 2) {
            _capitalAI.setPriority(Thread.MIN_PRIORITY);
            _woodenAI.setPriority(Thread.MIN_PRIORITY);
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        }
    }
}
