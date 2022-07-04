package client.gui;

import client.listner.MainFrameListener;
import oop.Constants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BookPanel extends JPanel {

    private final JTable table;

    public BookPanel(MainFrameListener listner) {
        setLayout(null);
        setBounds(Constants.PANEL_X, Constants.PANEL_Y, Constants.PANEL_WIDTH, Constants.PANEL_HEIGHT);
        String[] column = {"ID", "Title", "Author", "Rating", "ISBN", "Pages", "Rating Count", "Quantity"};
        String[][] data = listner.getBooks();
        DefaultTableModel model = new DefaultTableModel(data, column);
        table = new JTable(model);
        table.setForeground(Color.BLACK);
        table.setAutoCreateRowSorter(true);
        JScrollPane bar = new JScrollPane(table);
        bar.setBounds(0, 0, Constants.TABLE_WIDTH, Constants.TABLE_HEIGHT);
        add(bar);
    }
}