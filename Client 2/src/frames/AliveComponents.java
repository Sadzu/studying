package frames;

import Generation.Habitat;
import Generation.House;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;

public class AliveComponents {
    private static final Habitat _habitat = Habitat.getInstance();
    public static void init(TreeMap<Integer, House> houses, JFrame parentFrame) {
        JDialog frame = new JDialog(parentFrame, "Alive components", true);
        frame.setSize(1000, 1000);
        Container container = frame.getContentPane();

        TextArea housesList = new TextArea("", 1, 4, TextArea.SCROLLBARS_NONE);
        housesList.setEditable(false);
        for (int i = 1; i <= _habitat.getSimulationTime() + Math.max(_habitat.getWoodenAliveTime(), _habitat.getCapitalAliveTime()); i++) {
            if (houses.containsKey(i)) {
                if (houses.get(i).getName().equals("Wooden")) {
                    housesList.append(houses.get(i).getName() + ". Was born in " + (i - _habitat.getWoodenAliveTime()) + " seconds\n");
                } else {
                    housesList.append(houses.get(i).getName() + ". Was born in " + (i - _habitat.getCapitalAliveTime()) + " seconds\n");
                }
                if (houses.containsKey(-i)) {
                    if (houses.get(-i).getName().equals("Wooden")) {
                        housesList.append(houses.get(-i).getName() + ". Was born in " + (i - _habitat.getWoodenAliveTime()) + " seconds\n");
                    } else {
                        housesList.append(houses.get(-i).getName() + ". Was born in " + (i - _habitat.getCapitalAliveTime()) + " seconds\n");
                    }
                }
            }
        }

        container.add(housesList, BorderLayout.NORTH);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                _habitat.Resume();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);

        container.add(buttonPanel, BorderLayout.SOUTH);
        container.setLayout(new GridLayout(2, 1, 10, 10));

        frame.setVisible(true);
    }
}
