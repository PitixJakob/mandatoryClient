package control;

import gui.GuiClient;
import model.Client;

import java.io.IOException;

/**
 * Created by jakob on 12-09-16.
 */
public class Controller {
    private Client client;
    private GuiClient guiClient;

    public Controller(Client client, GuiClient guiClient) {
        this.client = client;
        this.guiClient = guiClient;
        client.setController(this);
        guiClient.setController(this);
    }

    public void receiveMessage(String message) {

    }

    public void updateListedUsers(String[] users) {

    }

    public void showError(String errorMessage) {

    }

    public void sendChatLine(String message){
        client.sendChatLine(message);
    }

    public void connect(String hostname, int port, String username) throws IOException {
        client.connect(hostname, port, username);
    }
}
