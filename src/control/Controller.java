package control;

import gui.GuiClient;
import model.Client;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by jakob on 12-09-16.
 */
public class Controller {
    private Client client;
    private GuiClient guiClient;

    public Controller() {
        client = new Client(this);
        guiClient = new GuiClient(this);
    }

    public void receiveMessage(String message) {
        guiClient.showMessage(message);

    }

    public void updateListedUsers(String[] users) {
        guiClient.updateListOfUsers(users);
    }

    public void showError(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage);
    }

    public void sendChatLine(String message){
        client.sendChatLine(message);
    }

    public void connect(String hostname, int port, String username) throws IOException {
        client.connect(hostname, port, username);
    }

    public void logout() throws IOException {
        client.logout();
    }
}
