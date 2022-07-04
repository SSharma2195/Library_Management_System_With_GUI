package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {
    protected Socket skt;
    private QueryManagement query;
    private ObjectOutputStream out;
    private DataInputStream in;


    public ServerThread(Socket clientSocket) {
        try {
            this.skt = clientSocket;
            query = new QueryManagement();
            in =  new DataInputStream(skt.getInputStream());
            out = new ObjectOutputStream(skt.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            while (true) {
                String str = "";
                try {
                    str = in.readUTF();
                }catch (Exception e){
                    continue;
                }
                if(str.equals("books")){
                    out.writeObject(query.initBooks());
                }else if(str.equals("loan"))
                    out.writeObject(query.initLoan());
                else if(str.equals("person"))
                    out.writeObject(query.initPerson());
                else {
                    query.execute(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
