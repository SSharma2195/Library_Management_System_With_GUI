package client.listner;

import oop.Books;
import oop.Loan;
import oop.Person;

import javax.swing.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    private DataOutputStream dataOut = null;
    private Socket skt = null;
    private ArrayList<String> books;
    private ObjectInputStream objectInput;

    public Client(String address, int port) {
        try {
            skt = new Socket(address, port);
            dataOut = new DataOutputStream(skt.getOutputStream());
            objectInput = new ObjectInputStream(skt.getInputStream());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error while connecting to server");
            System.exit(0);
        }
    }

    public ArrayList<Books> getBooks() {
        try {
            dataOut.writeUTF("books");
            Object obj = null;
            obj = objectInput.readObject();
            return (ArrayList<Books>) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Loan> getLoan() {
        try {
            dataOut.writeUTF("loan");
            Object obj = null;
            obj = objectInput.readObject();
            return (ArrayList<Loan>) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Person> getPerson() {
        try {
            dataOut.writeUTF("person");
            Object obj = null;
            try {
                obj = objectInput.readObject();
            }catch (Exception e){
                return getPerson();
            }
            return (ArrayList<Person>) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendQuery(String sql) {
        try {
            dataOut.writeUTF(sql);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
