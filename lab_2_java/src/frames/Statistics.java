package frames;

import Generation.Habitat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Statistics {
    public static void init(JFrame parentFrame) {
        JDialog frame = new JDialog(parentFrame, "Statistics" ,true);
        frame.setFocusable(false);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        Habitat habitat = Habitat.getInstance(0, 0, 0, 0);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                habitat.Stop();
                System.exit(0);
            }
        });
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                habitat.Resume();
                frame.setVisible(false);
            }
        });

        TextArea statistics = new TextArea("", 1, 4, TextArea.SCROLLBARS_NONE);
        statistics.append("Houses generated: " + habitat.getAllObjectsCount() + '\n');
        statistics.append("Wooden houses generated: " + habitat.getWoodenCount() + '\n');
        statistics.append("Capital houses generated: " + habitat.getCapitalCount() + '\n');
        statistics.append("Simulation time: " + habitat.getSimulationTime() + " seconds");
        statistics.setEditable(false);

        Container container = frame.getContentPane();
        //container.setLayout(new GridLayout(1, 1, 2, 2));
        container.add(statistics, BorderLayout.EAST);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        container.add(buttonPanel, BorderLayout.WEST);

        container.setVisible(true);
        frame.setVisible(true);
    }
}
