package client;

import gui.GuiClient;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by jakob on 08-09-16.
 */
public class Client {

    private String hostname;
    private int port;
    private String username;
    private Timer timer;
    private boolean loggedIn;

    private Socket socket;
    private PrintWriter toServer;
    private BufferedReader fromServer;
    private GuiClient gui;

    /**
     * Initial client construction
     *
     * @param gui
     */
    public Client(GuiClient gui) {
        this.gui = gui;
        loggedIn = false;
        timer = new Timer(5000, e -> sendMessage("ALVE"));
    }

    /**
     * Sends a connection request to the server
     *
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


        sendMessage("JOIN " + username + ", " + hostname + ":" + port);
    }

    /**
     * Tells the Gui to show an error to the user
     *
     * @param errorMessage
     */
    public void showError(String errorMessage) {
        gui.showError(errorMessage);
    }

    /**
     * Sends a message to the server
     *
     * @param message Message to be sent
     */
    public void sendMessage(String message) {
        toServer.println(message);
    }

    /**
     * Formats a message to be sent to all users connected to the server and
     * sends it
     *
     * @param message Message to be sent
     */
    public void sendChatLine(String message) {
        String result = "DATA " + username + ": " + message;
        sendMessage(result);
    }

    /**
     * Tells the Gui to show a message in the textArea
     *
     * @param message Message to be shown
     */
    public void receiveMessage(String message) {
        gui.receiveMessage(message);
    }

    /**
     * Starts the timer and tells the gui that the user is connected to the
     * server
     */
    public void joinOK() {
        loggedIn = true;
        timer.restart();
        receiveMessage("Connected to server " + hostname + ":" + port);
    }

    /**
     * Tells the gui to update the userlist
     *
     * @param users new user list
     */
    public void updateListedUsers(String[] users) {
        gui.updateListedUsers(users);
    }

    /**
     * Disconnects from the server
     *
     * @throws IOException
     */
    public void logout() {
        try {
            if (loggedIn) {
                sendMessage("QUIT");
            }
            timer.stop();
            loggedIn = false;
            closeConn();
        } catch (IOException ex) {
            //I dont care I'm logging out anyways
        }
    }

    /**
     * Closes connection to the server
     *
     * @throws IOException for no apparent reason, How would I ever get an error
     * from disconnecting, and wouldn't that error just result in a
     * disconnection anyways?
     */
    public void closeConn() throws IOException {
        if (fromServer != null) {
            fromServer.close();
        }
        if (toServer != null) {
            toServer.close();
        }
        if (socket != null) {
            if (!socket.isClosed()) {
                socket.close();
            }
        }
    }

    public BufferedReader getFromServer() {
        return fromServer;
    }

    public boolean getLoggedIn() {
        return loggedIn;
    }

}
