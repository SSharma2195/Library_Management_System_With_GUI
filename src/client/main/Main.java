package client.main;

import client.gui.MainFrame;
import client.listner.Client;

public class Main {

    public static void main(String[] args) {
        Client client = new Client("localhost", 6666);
        MainFrame frame = new MainFrame(client);
        frame.setVisible(true);
    }
}

