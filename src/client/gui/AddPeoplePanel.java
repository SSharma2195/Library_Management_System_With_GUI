package client.gui;

import client.listner.AddPersonListener;
import oop.Constants;
import oop.Person;

import javax.swing.*;
import java.util.ArrayList;

public class AddPeoplePanel extends JPanel{

    private final JTextField firstName;
    private final JTextField secondName;
    private final JTextField libraryCard;
    private final JButton add;
    private final JButton delete;
    private final JButton update;
    private final JButton search;
    private final JButton reset;

    public AddPeoplePanel(ArrayList<Person> persons,  MainFrame frame) {
        setBounds(1000, 0, Constants.ADDING_PERSON_PANEL_WIDTH, Constants.ADDING_PERSON_PANEL_HEIGHT-20);
        setLayout(null);

        firstName = new JTextField();
        secondName = new JTextField();
        libraryCard = new JTextField();

        firstName.setBounds(200, 100, 270, 40);
        secondName.setBounds(200, 162, 270, 40);
        libraryCard.setBounds(200, 225, 270, 40);

        add(firstName);
        add(secondName);
        add(libraryCard);

        AddPersonListener listener = new AddPersonListener(this, persons, frame);

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
        background.setIcon(new ImageIcon("./personFrame.png"));
        background.setBounds(0, 0, Constants.ADDING_PERSON_PANEL_WIDTH, Constants.ADDING_PERSON_PANEL_HEIGHT - 20);
        add(background);
    }

    public void reset(){
        firstName.setText("");
        secondName.setText("");
        libraryCard.setText("");
    }

    public boolean notEmpty() {
        return !firstName.getText().equals("") &&
                !secondName.getText().equals("") &&
                !libraryCard.getText().equals("");
    }

    public String[] getText() {
        return new String[]{firstName.getText(), secondName.getText(), libraryCard.getText()};
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

    public void setText(Person person) {
        firstName.setText(""+person.getFirst_name());
        secondName.setText(""+person.getLast_name());
        libraryCard.setText(""+person.getLibraryCard());

    }

}
