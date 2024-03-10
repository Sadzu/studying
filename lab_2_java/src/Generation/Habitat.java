package Generation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Habitat extends JPanel {
    private static Habitat _instance;

    private final ArrayList<House> _houses;
    private final Timer _timer;
    private int _simulationTime;
    private boolean _showTime;

    private final double _woodenChance;
    private final int _woodenTime;
    private int _woodenCount;
    private final double _capitalChance;
    private final int _capitalTime;
    private int _capitalCount;

    public static Habitat getInstance(int woodenTime, double woodenChance, int capitalTime, double capitalChance) {
        if (_instance == null) {
            _instance = new Habitat(woodenTime, woodenChance, capitalTime, capitalChance);
        }

        return _instance;
    }
    public static Habitat getInstance() {return _instance;}

    protected Habitat(int woodenTime, double woodenChance, int capitalTime, double capitalChance) {
        _houses = new ArrayList<House>();
        _simulationTime = 0;
        _showTime = true;

        _woodenTime = woodenTime;
        _woodenChance = woodenChance;
        _woodenCount = 0;
        _capitalTime = capitalTime;
        _capitalChance = capitalChance;
        _capitalCount = 0;

        _timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _simulationTime++;
                if (_simulationTime % _woodenTime == 0) {
                    _generateWooden();
                }
                if (_simulationTime % _capitalTime == 0) {
                    _generateCapital();
                }

                repaint();
            }
        });
    }

    private void _generateWooden() {
        double random = Math.random();
        if (random <= _woodenChance) {
            _houses.add(new Wooden((int)(Math.random() * 1800), (int)(Math.random() * 900)));
            _woodenCount += 1;
        }
    }
    private void _generateCapital() {
        double random = Math.random();
        if (random <= _capitalChance) {
            _houses.add(new Capital((int)(Math.random() * 1800), (int)(Math.random() * 900)));
            _capitalCount += 1;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (House house : _houses) {
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

    public void Start() {_timer.start();}
    public void Stop() {
        _timer.stop();
        _simulationTime = 0;
        _houses.clear();
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
}
