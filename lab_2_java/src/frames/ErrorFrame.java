package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorFrame {
    public static void init(JFrame parentFrame) {
        JDialog frame = new JDialog(parentFrame, "Error message", true);
        frame.setSize(500, 500);
        Container container = frame.getContentPane();

        TextArea errorMessage = new TextArea("", 1, 1, TextArea.SCROLLBARS_NONE);
        errorMessage.append("Введены неверные данные, установлены значения по умолчанию");
        errorMessage.setEditable(false);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container.setVisible(false);
                frame.setVisible(false);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);

        container.add(errorMessage);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        container.setVisible(true);
        frame.setVisible(true);
    }
}
