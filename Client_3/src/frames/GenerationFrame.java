package frames;

import Config.ConfigMgr;
import Events.KeyEventListener;
import Generation.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerationFrame {
    private static Habitat _habitat = Habitat.getInstance();
    private static final JButton _startButton = new JButton("Start");
    private static final JButton _stopButton = new JButton("Stop");
    private static final JRadioButton _showTimeButton = new JRadioButton("Show simulation time");
    private static final JRadioButton _hideTimeButton = new JRadioButton("Hide simulation time");
    private static final JButton _showAliveButton = new JButton("Show alive components");
    private static final JButton _saveButton = _getSaveButton();
    private static final JFrame _frame = new JFrame("Generation");
    private static boolean _showInfo;
    public static void init(boolean showInfo, boolean errFlag, boolean isLoaded) {
        if (isLoaded) {
            ConfigMgr.loadConfig();
            _habitat = Habitat.getInstance();
        } else { _showInfo = showInfo; }

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
        _stopButton.setForeground(Color.GREEN);
        _stopButton.setBackground(Color.CYAN);

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

        _showAliveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _habitat.Pause();
                AliveComponents.init(HouseCollections.getInstance().getBornTimeIds(), _frame);
            }
        });
//      <----Add to button panel (down)---->
        ButtonGroup simTimeGroup = new ButtonGroup();
        simTimeGroup.add(_showTimeButton);
        simTimeGroup.add(_hideTimeButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(_startButton);
        buttonPanel.add(_stopButton);
        buttonPanel.add(_showTimeButton);
        buttonPanel.add(_hideTimeButton);
        buttonPanel.add(_showAliveButton);
        buttonPanel.add(_saveButton);
        buttonPanel.add(getConnectionsListButton());

        _frame.addKeyListener(new KeyEventListener(_startButton, _stopButton, _showTimeButton, _hideTimeButton));
        _frame.setFocusable(true);
        _frame.setAutoRequestFocus(true);

        _showTimeButton.doClick();

        _setUnfocusableAll();
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
        mainMenu.add(_getCmd());
        mainMenu.add(_getSerializeButton());
        mainMenu.add(_getDeserializeButton());

        JMenu threadMenu = getThreadMenu();

        JMenuBar menuPanel = new JMenuBar();
        menuPanel.add(mainMenu);
        menuPanel.add(threadMenu);
        _frame.getContentPane().add(menuPanel, BorderLayout.NORTH);
    }

    private static JMenu getThreadMenu() {
        JMenu threadMenu = new JMenu("Threads menu");
        JMenuItem capitalAIChanger = new JMenuItem("Stop/Resume capital AI");
        JMenuItem woodenAIChanger = new JMenuItem("Stop/Resume wooden AI");

        capitalAIChanger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _habitat.changeCapitalAIStatus();
            }
        });
        woodenAIChanger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _habitat.changeWoodenAIStatus();
            }
        });

        threadMenu.add(capitalAIChanger);
        threadMenu.add(woodenAIChanger);

        JMenu threadPriority = _getThreadPriority();
        threadMenu.add(threadPriority);

        return threadMenu;
    }

    private static JMenu _getThreadPriority() {
        JMenu threadPriority = new JMenu("Threads priority set");
        JMenuItem capitalThreadPriority = new JMenuItem("Set priority to capital");
        capitalThreadPriority.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _habitat.setAIPriority(0);
            }
        });
        JMenuItem woodenThreadPriority = new JMenuItem("Set priority to wooden");
        woodenThreadPriority.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _habitat.setAIPriority(1);
            }
        });
        JMenuItem mainThreadPriority = new JMenuItem("Set priority to main");
        mainThreadPriority.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _habitat.setAIPriority(2);
            }
        });
        threadPriority.add(mainThreadPriority);
        threadPriority.add(capitalThreadPriority);
        threadPriority.add(woodenThreadPriority);
        return threadPriority;
    }

    private static void _setUnfocusableAll() {
        _startButton.setFocusable(false);
        _stopButton.setFocusable(false);
        _hideTimeButton.setFocusable(false);
        _showTimeButton.setFocusable(false);
        _showAliveButton.setFocusable(false);
    }

    private static JMenuItem _getCmd() {
        JMenuItem cmdCallButton = new JMenuItem("Open console");
        Cmd cmd = new Cmd();
        cmd.init(_frame);
        cmdCallButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Cmd().showCmd();
            }
        });

        return cmdCallButton;
    }

    public static boolean getShowInfoFlag() { return _showInfo; }
    public static void setShowInfoFlag(boolean flag) { _showInfo = flag; }

    private static JButton _getSaveButton() {
        JButton saveButton = new JButton("Save config and exit");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfigMgr.saveConfig();
                _habitat.Stop();
                System.exit(0);
            }
        });

        return saveButton;
    }

    private static JMenuItem _getSerializeButton() {
        JMenuItem serializeButton = new JMenuItem("Save objects");
        serializeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _habitat.serialize();
            }
        });

        return serializeButton;
    }

    private static JMenuItem _getDeserializeButton() {
        JMenuItem deserializeButton = new JMenuItem("Load objects");
        deserializeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _habitat.deserialize();
            }
        });

        return deserializeButton;
    }

    private static JButton getConnectionsListButton() {
        JButton connectionsListButton = new JButton("Show connections list");
        connectionsListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConnectionsList.show();
            }
        });

        return connectionsListButton;
    }
}
