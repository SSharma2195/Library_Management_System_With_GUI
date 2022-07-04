package client.gui;

import client.listner.Client;
import client.listner.MainFrameListener;
import oop.Constants;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final JPanel panel;
    private JPanel selectedPanel;
    private JPanel selectedPanel2;
    private final MainFrameListener listener;
    private JButton addBook;
    private JButton addLoan;
    private JButton addPerson;
    private JButton returnLoan;
    private JButton books;
    private JButton person;
    private JButton loan;
    private Client client;

    public MainFrame(Client client) {
        super("Library Management System");
        this.client = client;
        listener = new MainFrameListener(this, client);
        selectedPanel = new BookPanel(listener);
        setResizable(false);
        selectedPanel2 = new AddBookPanel(client.getBooks(), this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(111, 145, 171));
        setContentPane(panel);
        panel.add(selectedPanel);
        panel.add(selectedPanel2);

        addBook = new JButton();
        addBook.setIcon(new ImageIcon("Add Book.png"));
        addBook.setBounds(250, 20, 140, 37);
        addBook.setOpaque(false);
        addBook.setContentAreaFilled(false);
        addBook.setBorderPainted(false);
        panel.add(addBook);

        addLoan = new JButton();
        addLoan.setIcon(new ImageIcon("Add Loan.png"));
        addLoan.setBounds(400, 20, 140, 37);
        addLoan.setOpaque(false);
        addLoan.setContentAreaFilled(false);
        addLoan.setBorderPainted(false);
        panel.add(addLoan);

        addPerson = new JButton();
        addPerson.setIcon(new ImageIcon("Add Person.png"));
        addPerson.setBounds(550, 20, 140, 37);
        addPerson.setOpaque(false);
        addPerson.setContentAreaFilled(false);
        addPerson.setBorderPainted(false);
        panel.add(addPerson);

        returnLoan = new JButton();
        returnLoan.setIcon(new ImageIcon("Return icon.png"));
        returnLoan.setBounds(700, 20, 140, 37);
        returnLoan.setOpaque(false);
        returnLoan.setContentAreaFilled(false);
        returnLoan.setBorderPainted(false);
        panel.add(returnLoan);

        books = new JButton();
        books.setIcon(new ImageIcon("Books.png"));
        books.setBounds(20, 100, 140, 37);
        books.setOpaque(false);
        books.setContentAreaFilled(false);
        books.setBorderPainted(false);
        panel.add(books);

        person = new JButton();
        person.setIcon(new ImageIcon("Person.png"));
        person.setBounds(20, 150, 140, 37);
        person.setOpaque(false);
        person.setContentAreaFilled(false);
        person.setBorderPainted(false);
        panel.add(person);

        loan = new JButton();
        loan.setIcon(new ImageIcon("Loan.png"));
        loan.setBounds(20, 200, 140, 37);
        loan.setOpaque(false);
        loan.setContentAreaFilled(false);
        loan.setBorderPainted(false);
        panel.add(loan);

        addBook.addActionListener(listener);
        addPerson.addActionListener(listener);
        addLoan.addActionListener(listener);
        returnLoan.addActionListener(listener);
        books.addActionListener(listener);
        loan.addActionListener(listener);
        person.addActionListener(listener);

        JLabel background = new JLabel();
        background.setIcon(new ImageIcon("./Background.png"));
        background.setBounds(0, 0, Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT-20);
        panel.add(background);

    }

    public void setPanel(String name) {
        panel.remove(selectedPanel);
        if (name.equals("loan")) {
            selectedPanel = new OnLoanPanel(listener);
        } else if (name.equals("person")) {
            selectedPanel = new PersonPanel(listener);
        } else if (name.equals("book")) {
            selectedPanel = new BookPanel(listener);
        }
        panel.add(selectedPanel);
    }

    public void setPanel2(String name) {
        panel.remove(selectedPanel2);
        if (name.equals("loan")) {
            selectedPanel2 = new AddLoanPanel(client.getLoan(), this);
        } else if (name.equals("person")) {
            selectedPanel2 = new AddPeoplePanel(client.getPerson(), this);
        } else if (name.equals("book")) {
            selectedPanel2 = new AddBookPanel(client.getBooks(), this);
        }
        setVisible(false);
        panel.add(selectedPanel2);
        setVisible(true);
    }

    public boolean getAddBook(Object but) {
        return addBook.equals(but);
    }

    public boolean getAddLoan(Object but) {
        return addLoan.equals(but);
    }

    public boolean getAddPerson(Object but) {
        return addPerson.equals(but);
    }

    public boolean getReturnLoan(Object but) {
        return returnLoan.equals(but);
    }

    public boolean getBooks(Object but) {
        return books.equals(but);
    }

    public boolean getPerson(Object but) {
        return person.equals(but);
    }

    public boolean getLoan(Object but) {
        return loan.equals(but);
    }

    public MainFrameListener getListener() {
        return listener;
    }

    public void execute(String sql){
        client.sendQuery(sql);
    }

    public Client getClient() {
        return client;
    }
}
