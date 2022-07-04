package client.listner;

import client.gui.AddPeoplePanel;
import client.gui.MainFrame;
import oop.DBConnection;
import oop.Person;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class AddPersonListener implements ActionListener {

    private AddPeoplePanel frame;
    private int searchID=-1;
    private ArrayList<Person> persons;
    private MainFrame mainFrame;

    public AddPersonListener(AddPeoplePanel frame, ArrayList<Person> persons, MainFrame mainFrame) {
        this.persons = persons;
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
        String id = JOptionPane.showInputDialog("Enter Person ID");
        if(id != null && id.matches("[0-9]+")){
            int id_ = Integer.parseInt(id);
            for(Person person :persons){
                if(person.getPerson_id() == id_){
                    searchID = id_;
                    frame.setText(person);
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "No person found with given ID");
        }else{
            JOptionPane.showMessageDialog(null, "Enter Valid Book ID");
        }
    }

    private void deleteQuery() {
        String sql = "DELETE FROM person WHERE person_id = "+ searchID +";";
        mainFrame.execute(sql);
        JOptionPane.showMessageDialog(null, "Person deleted");
        mainFrame.setPanel("person");
        persons = mainFrame.getClient().getPerson();
    }

    private void delete() {
        if(searchID != -1){
            deleteQuery();
            deleteUser(searchID);
            searchID = -1;
            frame.reset();
            return;
        }
        String id = JOptionPane.showInputDialog("Enter Person ID");
        if(id != null && id.matches("[0-9]+")){
            int id_ = Integer.parseInt(id);
            for(Person person :persons){
                if(person.getPerson_id() == id_){
                    deleteQuery();
                    searchID = -1;
                    deleteUser(id_);
                    frame.reset();
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "No person found with given ID");
        }else{
            JOptionPane.showMessageDialog(null, "Enter Valid Person ID");
        }
    }

    private void deleteUser(int id){
        for(int i=0;i<persons.size(); i++){
            if(persons.get(i).getPerson_id() == id){
                persons.remove(i);
            }
        }
    }

    private void update() {
        if(searchID != -1){
            if(frame.notEmpty()) {
                String array[] = frame.getText();
                String sql = "UPDATE person SET first_name=\"" + array[0] + "\", last_name=\"" + array[1] + "\", library_card=" + array[2] +
                        " WHERE person_id = "+searchID+";";
                mainFrame.execute(sql);
                JOptionPane.showMessageDialog(null, "Person Updated");
                mainFrame.setPanel("person");
                persons = mainFrame.getClient().getPerson();
            }
        }else {
            JOptionPane.showMessageDialog(null, "No Person selected");
        }
    }

    private void add() {
        if(frame.notEmpty()){
            String array[] = frame.getText();
            String sql = "INSERT INTO person(first_name, last_name, library_card) VALUES (\""+array[0]+"\", \""+array[1]+"\", "+array[2]+");";
            mainFrame.execute(sql);
            JOptionPane.showMessageDialog(null, "Person added");
            mainFrame.setPanel("person");
            persons = mainFrame.getClient().getPerson();
        }else{
            JOptionPane.showMessageDialog(null, "Data not valid");
        }
    }
}
