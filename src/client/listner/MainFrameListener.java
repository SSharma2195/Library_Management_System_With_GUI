package client.listner;

import client.gui.AddBookPanel;
import client.gui.AddLoanPanel;
import client.gui.AddPeoplePanel;
import client.gui.MainFrame;
import oop.*;
import oop.Books;
import oop.Loan;
import server.LoanStatus;
import oop.Person;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainFrameListener implements ActionListener {
    private final MainFrame frame;
    private Client client;

    public MainFrameListener(MainFrame frame, Client client) {
        this.frame = frame;
        this.client = client;
    }

    public String[][] getBooks() {
        ArrayList<Books> bookList= client.getBooks();
        String[][] data = new String[bookList.size()][8];
        for (int i = 0; i < bookList.size(); i++) {
            Books book = bookList.get(i);
            data[i][0] = "" + book.getBook_id();
            data[i][1] = "" + book.getTitle();
            data[i][2] = "" + book.getAuthors();
            data[i][3] = "" + book.getAvgRating();
            data[i][4] = "" + book.getIsbn();
            data[i][5] = "" + book.getPage();
            data[i][6] = "" + book.getRatingCount();
            data[i][7] = "" + book.getQuantity();
        }
        return data;
    }

    public String[][] getLoan() {
        ArrayList<Loan> loanList= client.getLoan();
        String[][] data = new String[loanList.size()][8];
        for (int i = 0; i < loanList.size(); i++) {
            Loan loan = loanList.get(i);
            data[i][0] = "" + loan.getLoanID();
            data[i][1] = "" + loan.getBookID();
            data[i][2] = "" + loan.getPersonID();
            data[i][3] = "" + loan.getLoanPeriod();
            data[i][4] = "" + loan.getLoanStart();
            data[i][5] = "" + loan.getLoanEnd();
            data[i][6] = "" + loan.getReturnDate();
            data[i][7] = "" + loan.getStatus();
        }
        return data;
    }

    public String[][] getPerson() {
        ArrayList<Person> personList = client.getPerson();
        String[][] data = new String[personList.size()][4];
        for (int i = 0; i < personList.size(); i++) {
            Person person = personList.get(i);
            data[i][0] = "" + person.getPerson_id();
            data[i][1] = "" + person.getFirst_name();
            data[i][2] = "" + person.getLast_name();
            data[i][3] = "" + person.getLibraryCard();
        }
        return data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object but = e.getSource();
        if(frame.getAddBook(but)){
            frame.setPanel2("book");
        }else if(frame.getAddLoan(but)){
            frame.setPanel2("loan");
        }else if(frame.getAddPerson(but)){
            frame.setPanel2("person");
        }else if(frame.getReturnLoan(but)){
            returnLoan();
        }else if(frame.getBooks(but)){
            frame.setPanel("book");
        }else if(frame.getPerson(but)){
            frame.setPanel("person");
        }else if(frame.getLoan(but)){
            frame.setPanel("loan");
        }
    }

    private void returnLoan() {
        ArrayList<Loan> loanList = client.getLoan();
        String val = JOptionPane.showInputDialog("Enter Loan ID");
        if(val != null && val.matches("[0-9]+")){
            int id = Integer.parseInt(val);
            for(Loan loan:loanList){
                if(loan.getLoanID() == id){
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    LocalDateTime now = LocalDateTime.now();
                    String sql = "UPDATE on_loan SET returned_date=\""+dtf.format(now)+"\", return_status=\"Returned\" WHERE loan_id="+id+";";
                    loan.setStatus(LoanStatus.RETURN);
                    client.sendQuery(sql);
                    JOptionPane.showMessageDialog(null, "Loan Returned");
                    frame.setPanel("loan");
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "No Such loan available");
        }else {
            JOptionPane.showMessageDialog(null, "Invalid ID");
        }
    }

    private void addPerson() {
        ArrayList<Person> personList = client.getPerson();
        AddPeoplePanel frame = new AddPeoplePanel(personList, this.frame);
        frame.setVisible(true);
    }

    private void addLoan() {
        ArrayList<Loan> loanList = client.getLoan();
        AddLoanPanel frame = new AddLoanPanel(loanList, this.frame);
        frame.setVisible(true);
    }

    private void addBook() {
        ArrayList<Books> bookList= client.getBooks();
        AddBookPanel frame = new AddBookPanel(bookList, this.frame);
        frame.setVisible(true);
    }
}
