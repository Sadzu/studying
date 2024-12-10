import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("House spawner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Habitat habitat = new Habitat();

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                habitat.startSimulation();
            }
        });

        startButton.addKeyListener(new KeyboardListener(habitat));

        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                habitat.stopSimulation();
            }
        });

        JButton toggleTimeButton = new JButton("Toggle Time Display");
        toggleTimeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                habitat.toggleTimeDisplay();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(toggleTimeButton);

        frame.getContentPane().add(habitat, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        frame.setSize(1920, 1080);
        frame.setVisible(true);
    }
}