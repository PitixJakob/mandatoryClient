package model;

import control.Controller;

import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


    public Client(){

    }




    public void connect(String hostname, int port, String username) throws IOException {
        socket = new Socket(hostname, port);
        toServer = new PrintWriter(socket.getOutputStream(), true);
        fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.username = username;
        this.hostname = hostname;
        this.port = port;

        timer = new Timer(60000, e -> sendMessage("ALVE"));

        IncomingReader ir = new IncomingReader(this);
        Thread readerThread = new Thread(ir);
        readerThread.start();

        sendMessage("JOIN "+username+", "+hostname+":"+port);
    }

    public String getHostname() {
        return hostname;
    }


    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public Socket getSocket() {
        return socket;
    }

    public PrintWriter getToServer() {
        return toServer;
    }

    public void setController(Controller controller){
        this.controller = controller;
    }

    public BufferedReader getFromServer() {
        return fromServer;
    }

    public void sendError(String errorMessage) throws IOException {
        controller.showError(errorMessage);
        toServer.close();
        fromServer.close();
        socket.close();
    }

    public void sendMessage(String message){
        toServer.println(message);
    }

    public void receiveMesaage(String message){
        controller.receiveMessage(message);
    }

    public void joinOK(){
        timer.start();
        controller.receiveMessage("Connected to server "+hostname+":"+port);
    }

    public void sendChatLine(String message){
        String result = "DATA "+username+": "+message;
        sendMessage(result);
    }

    public void updateListedUsers(String[] users){
        controller.updateListedUsers(users);
    }
}
