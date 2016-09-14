package gui;

import javax.swing.*;
import control.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Gudni on 12-09-2016.
 */
public class GuiClient {
    private JPanel mainPanel;
    private JLabel userLabel;
    private JLabel ipLabel;
    private JLabel portLabel;
    private JTextField userField;
    private JTextField ipField;
    private JTextField portField;
    private JButton joinChatButton;
    private JButton logOutButton;
    private JTextArea writeMessage;
    private JButton sendMessageButton;
    private JTextArea showMessage;
    private JTextArea showUsersArea;
    private Controller control;


    public GuiClient(Controller control) {
        this.control = control;
        sendMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (writeMessage.getText().length() <= 250) {
                    control.sendChatLine(writeMessage.getText());
                    writeMessage.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "You're message is to long, it may only contain 256 characters");
                }
            }
        });

        joinChatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    control.connect(ipField.getText(), Integer.parseInt(portField.getText()), userField.getText());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    control.logout();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });


    }
}



