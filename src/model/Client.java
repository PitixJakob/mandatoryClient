package model;

import control.Controller;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by jakob on 08-09-16.
 */
public class Client{
    private String hostname;
    private int port;
    private String username;
    private Timer timer;

    private Socket socket;
    private PrintWriter toServer;
    private BufferedReader fromServer;
    private Controller controller;


    /**
     * Initial client construction
     * @param controller
     */
    public Client(Controller controller){
        this.controller = controller;
    }

    /**
     * Sends a connection request to the server
     * @param hostname
     * @param port
     * @param username
     * @throws IOException
     */
    public void connect(String hostname, int port, String username) throws IOException {
        socket = new Socket(hostname, port);
        toServer = new PrintWriter(socket.getOutputStream(), true);
        fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.username = username;
        this.hostname = hostname;
        this.port = port;

        timer = new Timer(60000, e -> sendMessage("ALVE"));

        sendMessage("JOIN "+username+", "+hostname+":"+port);
    }


    public void showError(String errorMessage) {
        controller.showError(errorMessage);
    }

    public void sendMessage(String message){
        toServer.println(message);
    }

    public void receiveMessage(String message){
        controller.receiveMessage(message);
    }

    public void sendChatLine(String message){
        String result = "DATA "+username+": "+message;
        sendMessage(result);
    }

    public void joinOK(){
        Reader ir = new Reader(this);
        Thread readerThread = new Thread(ir);
        readerThread.start();
        timer.start();
        controller.receiveMessage("Connected to server "+hostname+":"+port);
    }

    public void updateListedUsers(String[] users){
        controller.updateListedUsers(users);
    }

    public void logout() throws IOException {
        sendMessage("QUIT");
        if (fromServer != null){
            fromServer.close();
        }
        if (toServer != null){
            toServer.close();
        }
        if (!socket.isClosed()){
            socket.close();
        }
    }

    public BufferedReader getFromServer() {
        return fromServer;
    }
}
