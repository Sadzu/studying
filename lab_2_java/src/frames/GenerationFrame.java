package frames;

import Events.KeyEventListener;
import Generation.Habitat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerationFrame {
    private static final Habitat _habitat = Habitat.getInstance();
    private static final JButton _startButton = new JButton("Start");
    private static final JButton _stopButton = new JButton("Stop");
    private static final JRadioButton _showTimeButton = new JRadioButton("Show simulation time");
    private static final JRadioButton _hideTimeButton = new JRadioButton("Hide simulation time");
    private static final JFrame _frame = new JFrame("Generation");
    public static void init(boolean showInfo, boolean errFlag) {
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _addMenu();

        if (errFlag) {ErrorFrame.init(_frame);}

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
                    Statistics.init(_frame);
                } else {
                    _stopMacros();
                }
            }
        });

        _showTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _habitat.showSimulationTime();
            }
        });
        _hideTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _habitat.hideSimulationTime();
            }
        });

        ButtonGroup simTimeGroup = new ButtonGroup();
        simTimeGroup.add(_showTimeButton);
        simTimeGroup.add(_hideTimeButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(_startButton);
        buttonPanel.add(_stopButton);
        buttonPanel.add(_showTimeButton);
        buttonPanel.add(_hideTimeButton);

        _frame.addKeyListener(new KeyEventListener(_startButton, _stopButton, _showTimeButton, _hideTimeButton));
        _frame.setFocusable(true);
        _frame.setAutoRequestFocus(true);

        _showTimeButton.doClick();

        setUnfocusableAll();
        buttonPanel.setFocusable(false);

        _frame.getContentPane().add(_habitat, BorderLayout.CENTER);
        _frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        _frame.setSize(1920, 1080);
        _frame.setVisible(true);
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

    private static void _addMenu() {
        JMenu mainMenu = new JMenu("Main");
        JMenuItem start = new JMenuItem("Start");
        JMenuItem stop = new JMenuItem("Stop");
        JRadioButtonMenuItem hideSimTime = new JRadioButtonMenuItem("Hide simulation time");
        JRadioButtonMenuItem showSimTime = new JRadioButtonMenuItem("Show simulation time");


        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _startButton.doClick();
                stop.setEnabled(true);
                start.setEnabled(false);
            }
        });

        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _stopButton.doClick();
            }
        });

        hideSimTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _hideTimeButton.doClick();
            }
        });

        showSimTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _showTimeButton.doClick();
            }
        });

        ButtonGroup bg = new ButtonGroup();
        bg.add(hideSimTime);
        bg.add(showSimTime);

        showSimTime.setSelected(true);

        mainMenu.add(start);
        mainMenu.add(stop);
        mainMenu.add(showSimTime);
        mainMenu.add(hideSimTime);

        JMenuBar menuPanel = new JMenuBar();
        menuPanel.add(mainMenu);
        _frame.getContentPane().add(menuPanel, BorderLayout.NORTH);
    }

    private static void setUnfocusableAll() {
        _startButton.setFocusable(false);
        _stopButton.setFocusable(false);
        _hideTimeButton.setFocusable(false);
        _showTimeButton.setFocusable(false);
    }
}
