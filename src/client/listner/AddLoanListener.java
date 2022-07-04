package client.listner;

import client.gui.AddLoanPanel;
import client.gui.MainFrame;
import oop.DBConnection;
import oop.Loan;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class AddLoanListener implements ActionListener {

    private final AddLoanPanel frame;
    private ArrayList<Loan> loans;
    private int searchID=-1;
    private MainFrame mainFrame;

    public AddLoanListener(AddLoanPanel frame, ArrayList<Loan> loans, MainFrame mainFrame) {
        this.loans = loans;
        this.frame = frame;
        this.mainFrame = mainFrame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(frame.getAdd(obj)){
            add();
        }else if(frame.getUpdate(obj)){
            update();
        }else if(frame.getDelete(obj)){
            delete();
        }else if(frame.getSearch(obj)){
            search();
        }else if(frame.getReset(obj)){
            searchID = -1;
            frame.reset();
        }
    }

    private void search() {
        String id = JOptionPane.showInputDialog("Enter Loan ID");
        if(id != null && id.matches("[0-9]+")){
            int id_ = Integer.parseInt(id);
            for(Loan loan :loans){
                if(loan.getLoanID() == id_){
                    searchID = id_;
                    frame.setText(loan);
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "No Loan found with given ID");
        }else{
            JOptionPane.showMessageDialog(null, "Enter Valid Loan ID");
        }
    }

    private void deleteQuery() {
        String sql = "DELETE FROM on_loan WHERE loan_id = "+ searchID +";";
        mainFrame.execute(sql);
        JOptionPane.showMessageDialog(null, "Loan deleted");
        mainFrame.setPanel("loan");
        loans = mainFrame.getClient().getLoan();
    }

    private void delete() {
        if(searchID != -1){
            deleteQuery();
            deleteUser(searchID);
            searchID = -1;
            frame.reset();
            return;
        }
        String id = JOptionPane.showInputDialog("Enter Loan ID");
        if(id != null && id.matches("[0-9]+")){
            int id_ = Integer.parseInt(id);
            for(Loan loan :loans){
                if(loan.getLoanID() == id_){
                    deleteQuery();
                    deleteUser(id_);
                    searchID = -1;
                    frame.reset();
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "No loan found with given ID");
        }else{
            JOptionPane.showMessageDialog(null, "Enter Valid Loan ID");
        }
    }

    private void update() {
        if(searchID != -1){
            if(frame.notEmpty()) {
                String array[] = frame.getText();
                String sql = "UPDATE on_loan SET book_id=" + array[0] + ", person_id=" + array[1] + ", loan_period=" + array[2] +
                        ", loan_start=\"" + array[3] + "\", loan_end=\"" + array[4] + "\""+
                        "WHERE loan_id = "+searchID+";";
                mainFrame.execute(sql);
                JOptionPane.showMessageDialog(null, "loan update");
                mainFrame.setPanel("loan");
                loans = mainFrame.getClient().getLoan();
            }
        }else {
            JOptionPane.showMessageDialog(null, "No Loan selected");
        }
    }

    private void deleteUser(int id){
        for(int i=0;i<loans.size(); i++){
            if(loans.get(i).getLoanID() == id){
                loans.remove(i);
            }
        }
    }

    private void add() {
        if(frame.notEmpty()){
            String array[] = frame.getText();
            String sql = "INSERT INTO on_loan(book_id, person_id, loan_period, loan_start, loan_end, return_status) \n" +
                    "VALUES ("+array[0]+","+array[1]+","+array[2]+",\""+array[3]+"\",\""+array[4]+"\",\"On loan\")";
            mainFrame.execute(sql);
            JOptionPane.showMessageDialog(null, "loan added");
            mainFrame.setPanel("loan");
            loans = mainFrame.getClient().getLoan();
        }else{
            JOptionPane.showMessageDialog(null, "Data not valid");
        }
    }
}
