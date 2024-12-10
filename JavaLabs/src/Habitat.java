//Wooden: chance 0.3, time 3
//Capital: chance 0.7 time 2

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

class Habitat extends JPanel {
    private final ArrayList<House> _houses;
    private final Timer _timer;
    private int _simulationTime;
    private boolean _showTime;

    public Habitat() {
        _houses = new ArrayList<House>();
        _simulationTime = 0;
        _showTime = true;

        _timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _simulationTime++;
                if (_simulationTime % 2 == 0) {
                    generateCapitalHouse();
                }
                if (_simulationTime % 3 == 0) {
                    generateWoodenHouse();
                }
                repaint();
            }
        });
    }

    public void startSimulation() {
        _timer.start();
    }

    public void stopSimulation() {
        _timer.stop();
        _houses.clear();
        _simulationTime = 0;
    }

    public void toggleTimeDisplay() {
        _showTime = !_showTime;
        //repaint();
    }

    private void generateCapitalHouse() {
        double random = Math.random();
        if (random <= 0.7) {
            _houses.add(new CapitalHouse());
        }
    }

    private void generateWoodenHouse() {
        double random = Math.random();
        if (random <= 0.3) {
            _houses.add(new WoodenHouse());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (House house : _houses) {
            //g.setColor(house.getColor());
            //g.fillRect(house.get_xCoordinate(), house.get_yCoordinate(), 50, 50);
            g.drawImage(house.get_image(), house.get_xCoordinate(), house.get_yCoordinate(),100, 100, null);

            g.setColor(Color.ORANGE);
            g.drawString(house.getName(), house.get_xCoordinate(), house.get_yCoordinate() + 60);
        }

        if (_showTime) {
            g.setColor(Color.BLACK);
            g.drawString("Simulation Time: " + _simulationTime + " seconds", 10, 20);
        }
    }
}