package frames;

import Config.ConfigMgr;
import Generation.Habitat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Menu extends JPanel {
    public static void init() {
        final boolean[] errFlag = {false};

        JFrame frame = new JFrame("Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setBounds(100, 100, 1000, 1000);

        JLabel woodenTimeLabel = new JLabel("Введите время генерации деревянного дома");
        JTextField woodenTField = new JTextField("");
        JLabel capitalTimeLabel = new JLabel("Введите время генерации капитального дома");
        JTextField capitalTField = new JTextField("");

        frame.add(woodenTimeLabel);
        frame.add(woodenTField);
        frame.add(capitalTimeLabel);
        frame.add(capitalTField);

        JLabel woodenChanceLabel = new JLabel("Выберите шанс генерации деревянного дома");
        String[] boxItems = {"10%", "20%", "30%", "40%", "50%", "60%", "70%", "80%", "90%", "100%"};
        JComboBox<String> woodenChanceBox = new JComboBox<String>(boxItems);

        JLabel capitalChanceLabel = new JLabel("Выберите шанс генерации капитального дома");
        JComboBox<String> capitalChanceBox = new JComboBox<String>(boxItems);

        JLabel woodenAliveTimeLabel = new JLabel("Введите время жизни деревянного дома");
        JTextField woodenAliveTimeField = new JTextField("");

        JLabel capitalAliveTimeLabel = new JLabel("Введите время жизни капитального дома");
        JTextField capitalAliveTimeField = new JTextField("");

        JCheckBox showInfo = new JCheckBox("Show info", true);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int woodenTime = 0;
                int capitalTime = 0;
                int woodenAliveTime = 0;
                int capitalAliveTime = 0;

                try {
                    woodenTime = Math.abs(Integer.parseInt(woodenTField.getText()));
                    capitalTime = Math.abs(Integer.parseInt(capitalTField.getText()));
                } catch (NumberFormatException err) {
                    woodenTime = 3;
                    capitalTime = 2;
                    errFlag[0] = true;
                }

                double woodenChance = choose((String) woodenChanceBox.getSelectedItem());
                double capitalChance = choose((String) capitalChanceBox.getSelectedItem());

                try {
                    woodenAliveTime = Math.abs(Integer.parseInt(woodenAliveTimeField.getText()));
                    capitalAliveTime = Math.abs(Integer.parseInt(capitalAliveTimeField.getText()));
                } catch (NumberFormatException err) {
                    woodenAliveTime = 3;
                    capitalAliveTime = 5;
                    errFlag[0] = true;
                }

                Habitat habitat = Habitat.getInstance(woodenTime, woodenChance, capitalTime, capitalChance, woodenAliveTime, capitalAliveTime);

                boolean showInfo_flag = showInfo.isSelected();

                frame.setVisible(false);

                GenerationFrame.init(showInfo_flag, errFlag[0], false);
            }
        });

        JButton loadButton = new JButton("Load config");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GenerationFrame.init(false, false, true);
                Habitat habitat = Habitat.getInstance(0, 0, 0, 0, 0, 0);
                frame.setVisible(false);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(loadButton);

        Container container = frame.getContentPane();
        container.setLayout(new GridLayout(4, 2, 10, 10));

        container.add(woodenTimeLabel);
        container.add(woodenTField);
        container.add(capitalTimeLabel);
        container.add(capitalTField);

        container.add(woodenChanceLabel);
        container.add(woodenChanceBox);
        container.add(capitalChanceLabel);
        container.add(capitalChanceBox);

        container.add(woodenAliveTimeLabel);
        container.add(woodenAliveTimeField);
        container.add(capitalAliveTimeLabel);
        container.add(capitalAliveTimeField);

        container.add(showInfo);
        container.add(getConfigDescription());

        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        //frame.add(container);

        container.setVisible(true);
        frame.setVisible(true);
    }

    private static double choose(String item) {

        return switch (item) {
            case "10%" -> 0.1;
            case "20%" -> 0.2;
            case "30%" -> 0.3;
            case "40%" -> 0.4;
            case "50%" -> 0.5;
            case "60%" -> 0.6;
            case "70%" -> 0.7;
            case "80%" -> 0.8;
            case "90%" -> 0.9;
            case "100%" -> 1;
            case null, default -> 0.3;
        };
    }

    private static TextArea getConfigDescription() {
        TextArea description = new TextArea("", 1, 1, TextArea.SCROLLBARS_NONE);
        try {
            description.append("Current saved config:\n");
            BufferedReader reader = new BufferedReader(new FileReader("src/Config/config.cfg"));
            description.append("Show info flag: " + reader.readLine() + '\n');
            description.append("Show time flag: " + reader.readLine() + '\n');
            description.append("Wooden generation time: " + reader.readLine() + '\n');
            description.append("Wooden generation chance: " + reader.readLine() + '\n');
            description.append("Wooden lifetime: " + reader.readLine() + '\n');
            description.append("Capital generation time: " + reader.readLine() + '\n');
            description.append("Capital generation chance: " + reader.readLine() + '\n');
            description.append("Capital lifetime: " + reader.readLine() + '\n');
        } catch (FileNotFoundException err) {
            err.printStackTrace();
            description.append("No config saved or no such file/directory");
        } catch (IOException err) {
            err.printStackTrace();
            description.append("No config saved or no such file/directory");
        }
        description.setEditable(false);

        return description;
    }
}
