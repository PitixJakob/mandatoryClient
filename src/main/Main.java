package main;

import control.Controller;
import gui.GuiClient;
import model.Client;

/**
 * Created by jakob on 12-09-16.
 */
public class Main {
    public static void main(String[] args) {
        GuiClient guiClient = new GuiClient();
        Client client = new Client();
        Controller controller = new Controller(client, guiClient);

    }
}
