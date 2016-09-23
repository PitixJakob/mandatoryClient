package client;

import gui.GuiClient;
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
    private GuiClient gui;


    /**
     * Initial client construction
     * @param gui
     */
    public Client(GuiClient gui){
        this.gui = gui;
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

        Reader ir = new Reader(this);
        Thread readerThread = new Thread(ir);
        readerThread.start();

        timer = new Timer(60000, e -> sendMessage("ALVE"));

        sendMessage("JOIN "+username+", "+hostname+":"+port);
    }

    /**
     * Tells the controller to show an error to the user
     * @param errorMessage
     */
    public void showError(String errorMessage) {
        gui.showError(errorMessage);
    }

    /**
     * Sends a message to the server
     * @param message Message to be sent
     */
    public void sendMessage(String message){
        toServer.println(message);
    }

    /**
     * Formats a message to be sent to all users connected to the server and sends it
     * @param message Message to be sent
     */
    public void sendChatLine(String message){
        String result = "DATA "+username+": "+message;
        sendMessage(result);
    }

    /**
     * Tells the controller to show a message in the gui
     * @param message Message to be shown
     */
    public void receiveMessage(String message){
        gui.receiveMessage(message);
    }

    /**
     * Starts the timer and tells the gui that the user is connected to the server
     */
    public void joinOK(){
        timer.start();
        receiveMessage("Connected to server "+hostname+":"+port);
    }

    /**
     * Tells the controller to update the userlist
     * @param users new user list
     */
    public void updateListedUsers(String[] users){
        gui.updateListedUsers(users);
    }

    /**
     * Disconnects from the server
     * @throws IOException
     */
    public void logout() throws IOException {
        sendMessage("QUIT");
        closeConn();
    }

    public void closeConn() throws IOException {
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
