package server;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket skt;
    private Socket socket;

    public Server(){
        try {
            skt = new ServerSocket(6666);
            System.out.println("Server started");
            while (true){
                socket = skt.accept();
                new ServerThread(socket).start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
