package frames;

import Generation.Habitat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        JCheckBox showInfo = new JCheckBox("Show info", true);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int woodenTime = 0;
                int capitalTime = 0;

                try {
                    woodenTime = Integer.parseInt(woodenTField.getText());
                    capitalTime = Integer.parseInt(capitalTField.getText());
                } catch (NumberFormatException err) {
                    woodenTime = 3;
                    capitalTime = 2;
                    errFlag[0] = true;
                }

                double woodenChance = choose((String) woodenChanceBox.getSelectedItem());
                double capitalChance = choose((String) capitalChanceBox.getSelectedItem());

                Habitat habitat = Habitat.getInstance(woodenTime, woodenChance, capitalTime, capitalChance);

                boolean showInfo_flag = showInfo.isSelected();

                frame.setVisible(false);

                Gen_frame.init(showInfo_flag, errFlag[0]);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);

        Container container = frame.getContentPane();
        container.setLayout(new GridLayout(3, 1, 2, 2));

        container.add(woodenTimeLabel);
        container.add(woodenTField);
        container.add(capitalTimeLabel);
        container.add(capitalTField);

        container.add(woodenChanceLabel);
        container.add(woodenChanceBox);
        container.add(capitalChanceLabel);
        container.add(capitalChanceBox);

        container.add(showInfo);

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
}
