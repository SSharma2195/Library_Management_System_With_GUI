package client.gui;

import client.listner.AddLoanListener;
import oop.Constants;
import oop.Loan;

import javax.swing.*;
import java.util.ArrayList;

public class AddLoanPanel extends JPanel {

    private JTextField bookID;
    private JTextField personID;
    private JTextField loanPeriod;
    private JTextField loanStart;
    private JTextField loanEnd;
    private JButton add;
    private JButton delete;
    private JButton update;
    private JButton search;
    private JButton reset;

    public AddLoanPanel(ArrayList<Loan> loans, MainFrame frame) {
        setBounds(1000, 0, Constants.ADDING_PERSON_PANEL_WIDTH, Constants.ADDING_PERSON_PANEL_HEIGHT-20);
        setLayout(null);

        bookID = new JTextField();
        personID = new JTextField();
        loanPeriod = new JTextField();
        loanStart = new JTextField();
        loanEnd = new JTextField();

        bookID.setBounds(170, 65, 300, 40);
        personID.setBounds(170, 107, 300, 40);
        loanPeriod.setBounds(170, 155, 300, 40);
        loanStart.setBounds(170, 200, 300, 40);
        loanEnd.setBounds(170, 250, 300, 40);

        add(bookID);
        add(personID);
        add(loanPeriod);
        add(loanStart);
        add(loanEnd);

        AddLoanListener listener = new AddLoanListener(this, loans, frame);

        add = new JButton("Add");
        add.setBounds(30, 320, 80, 30);
        add(add);
        add.addActionListener(listener);

        delete = new JButton("Delete");
        delete.setBounds(120, 320, 80, 30);
        add(delete);
        delete.addActionListener(listener);

        update = new JButton("Update");
        update.setBounds(210, 320, 80, 30);
        add(update);
        update.addActionListener(listener);

        search = new JButton("Search");
        search.setBounds(300, 320, 80, 30);
        add(search);
        search.addActionListener(listener);

        reset = new JButton("Reset");
        reset.setBounds(390, 320, 80, 30);
        add(reset);
        reset.addActionListener(listener);

        JLabel background = new JLabel();
        background.setIcon(new ImageIcon("./loanFrame.png"));
        background.setBounds(0, 0, Constants.ADDING_PERSON_PANEL_WIDTH, Constants.ADDING_PERSON_PANEL_HEIGHT - 20);
        add(background);
    }

    public void reset() {
        bookID.setText("");
        personID.setText("");
        loanPeriod.setText("");
        loanStart.setText("");
        loanEnd.setText("");
    }

    public boolean notEmpty() {
        return !bookID.getText().equals("") &&
                !personID.getText().equals("") &&
                !loanPeriod.getText().equals("")&&
                !loanStart.getText().equals("")&&
                !loanEnd.getText().equals("");
    }

    public String[] getText() {
        return new String[]{bookID.getText(), personID.getText(), loanPeriod.getText(), loanStart.getText(), loanEnd.getText()};
    }

    public boolean getAdd(Object obj) {
        return add.equals(obj);
    }

    public boolean getDelete(Object obj) {
        return delete.equals(obj);
    }

    public boolean getUpdate(Object obj) {
        return update.equals(obj);
    }

    public boolean getSearch(Object obj) {
        return search.equals(obj);
    }

    public boolean getReset(Object obj) {
        return reset.equals(obj);
    }

    public void setText(Loan loan) {
        bookID.setText(""+loan.getBookID());
        personID.setText(""+loan.getPersonID());
        loanPeriod.setText(""+loan.getLoanPeriod());
        loanStart.setText(""+loan.getLoanStart());
        loanEnd.setText(""+loan.getLoanEnd());
    }
}
