package frames;

import Events.KeyEventListener;
import Generation.Habitat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gen_frame {
    private static final Habitat _habitat = Habitat.getInstance();
    private static final JButton _startButton = new JButton("Start");
    private static final JButton _stopButton = new JButton("Stop");
    public static void init(boolean showInfo, boolean errFlag) {
        Statistics stats = new Statistics();

        JFrame frame = new JFrame("Generation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if (errFlag) {ErrorFrame.init(frame);}

        _stopButton.setEnabled(false);


        _startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _startMacros();
            }
        });

        _stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _habitat.Pause();
                if (showInfo) {
                    stats.init(frame);
                } else {
                    _stopMacros();
                }
            }
        });

        JRadioButton showTimeButton = new JRadioButton("Show simulation time");
        showTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _habitat.showSimulationTime();
            }
        });
        JRadioButton hideTimeButton = new JRadioButton("Hide simulation time");
        hideTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _habitat.hideSimulationTime();
            }
        });

        ButtonGroup simTimeGroup = new ButtonGroup();
        simTimeGroup.add(showTimeButton);
        simTimeGroup.add(hideTimeButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(_startButton);
        buttonPanel.add(_stopButton);
        buttonPanel.add(showTimeButton);
        buttonPanel.add(hideTimeButton);

        frame.addKeyListener(new KeyEventListener(_startButton, _stopButton, showTimeButton, hideTimeButton));
        frame.setFocusable(true);

        showTimeButton.doClick();

        frame.getContentPane().add(_habitat, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        frame.setSize(1920, 1080);
        frame.setVisible(true);
    }

    private static void _startMacros() {
        _habitat.Start();
        _startButton.setEnabled(false);
        _stopButton.setEnabled(true);
    }
    private static void _stopMacros() {
        _habitat.Pause();
        _startButton.setEnabled(true);
        _stopButton.setEnabled(false);
    }
}
