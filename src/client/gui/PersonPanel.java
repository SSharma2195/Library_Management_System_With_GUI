package client.gui;

import client.listner.MainFrameListener;
import oop.Constants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PersonPanel extends JPanel {

    public PersonPanel(MainFrameListener listener) {
        setLayout(null);
        setBounds(Constants.PANEL_X, Constants.PANEL_Y, Constants.PANEL_WIDTH, Constants.PANEL_HEIGHT);
        String[] column = {"ID", "First Name", "Last Name", "Library Card"};
        String[][] data = listener.getPerson();
        DefaultTableModel model = new DefaultTableModel(data, column);
        JTable table = new JTable(model);
        table.setForeground(Color.BLACK);
        table.setAutoCreateRowSorter(true);
        JScrollPane bar = new JScrollPane(table);
        bar.setBounds(0, 0, Constants.TABLE_WIDTH, Constants.TABLE_HEIGHT);
        bar.requestFocus(true);
        add(bar);
    }
}
