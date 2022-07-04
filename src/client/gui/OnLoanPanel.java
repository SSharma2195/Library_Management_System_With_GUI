package client.gui;

import client.listner.MainFrameListener;
import oop.Constants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class OnLoanPanel extends JPanel {

    public OnLoanPanel(MainFrameListener listner) {
        setLayout(null);
        setBounds(Constants.PANEL_X, Constants.PANEL_Y, Constants.PANEL_WIDTH, Constants.PANEL_HEIGHT);
        String[] column = {"ID", "Book ID", "Person ID", "Loan Period", "Loan Start", "Loan End", "Return Date", "Status"};
        String[][] data = listner.getLoan();
        DefaultTableModel model = new DefaultTableModel(data, column);
        JTable table = new JTable(model);
        table.setForeground(Color.BLACK);
        table.setFocusable(true);
        table.requestFocus();
        table.setAutoCreateRowSorter(true);
        JScrollPane bar = new JScrollPane(table);
        bar.setBounds(0, 0, Constants.TABLE_WIDTH, Constants.TABLE_HEIGHT);
        add(bar);
    }
}
