package frames;

import Generation.Habitat;
import SQL.DatabaseMgr;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Objects;

public class DBLoad {
    private static JFrame _frame;
    private static TextArea _dbEntryList;

    public static void init() {
        _frame = new JFrame("Choose load");
        _frame.setSize(500, 500);
        _dbEntryList = new TextArea("");
        try {
            _dbEntryList.append(DatabaseMgr.getInstance().tableToString());
        } catch (SQLException e) {
            e.printStackTrace();
            _dbEntryList.append("Error");
        }
        JComboBox<String> box;
        try {
            box = getComboBox(DatabaseMgr.getInstance().getIDs());
        } catch (SQLException e) {
            box = getComboBox(new String[] {""});
        }
        JComboBox<String> typeBox = getLoadTypeBox();
        _frame.getContentPane().add(_dbEntryList);
        JPanel buttonPanel = new JPanel();
        JButton loadButton = new JButton("Load");
        JComboBox<String> finalBox = box;
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt((String) Objects.requireNonNull(finalBox.getSelectedItem()));
                String type = (String) typeBox.getSelectedItem();
                assert type != null;
                if (type.equals("All")) {
                    try {
                        Habitat.getInstance().addFromString(DatabaseMgr.getInstance().getItemFromDatabase(id));
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    try {
                        Habitat.getInstance().addOnlyFromString(DatabaseMgr.getInstance().getItemFromDatabase(id), type);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                _frame.setVisible(false);
            }
        });
        buttonPanel.add(loadButton);
        _frame.getContentPane().add(_dbEntryList, BorderLayout.NORTH);
        _frame.add(box, BorderLayout.WEST);
        _frame.add(typeBox, BorderLayout.EAST);
        _frame.add(buttonPanel, BorderLayout.SOUTH);
        _frame.setVisible(true);
    }

    private static JComboBox<String> getComboBox(String[] boxItems) {
        return new JComboBox<String>(boxItems);
    }

    private static JComboBox<String> getLoadTypeBox() {
        String[] boxItems = {"All", "Capital", "Wooden"};

        return new JComboBox<String>(boxItems);
    }
}
