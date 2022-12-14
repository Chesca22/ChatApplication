package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;
    public Server(ServerSocket serverSocket){
        this.serverSocket=serverSocket;
    }

    public void startServerSocket(){
        try{
            while (!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                System.out.println("A new client has connected");
                ClientHandler clientHandler = new ClientHandler(socket);

                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeServerSocket(){
        try{
            if (serverSocket!= null){
                serverSocket.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // the main method instantiate object from it and then run it
    public static void main (String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(2020);
        Server server = new Server(serverSocket);
        server.startServerSocket();
    }
}
