package gui;

import client.Client;
import com.sun.glass.events.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultCaret;
import sun.awt.WindowClosingListener;

/**
 *
 * @author Gudni
 */
public class GuiClient extends javax.swing.JFrame {

    private Client client;

    /**
     * Creates new form GuiClient
     */
    public GuiClient() {

        client = new Client(this);
        initComponents();

        usernameField.setTransferHandler(null);

        //Set caret to autoscroll on chat window
        DefaultCaret caret = (DefaultCaret) chatArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                client.logout();
            }
        });
    }

    public void receiveMessage(String message) {
        chatArea.append(message + "\n");
    }

    public void updateListedUsers(String[] users) {
        listOfUsersArea.setText("");
        for (String username : users) {
            listOfUsersArea.append(username + "\n");
        }
    }

    public void showError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

    public boolean validLogin() {
        boolean result = true;

        //CHECK USERNAME IS NOT EMPTY && NOT OVER LENGTH LIMIT
        if (usernameField.getText().length() > 12 || usernameField.getText().length() < 1) {
            result = false;
            showError("Username has a minimum length of 1 and a max length of 12");
        }

        //CHECK HOSTNAME IS NOT EMPTY
        if (hostnameField.getText().isEmpty() && result) {
            result = false;
            showError("Hostname cannot be empty");
        }

        //CHECK PORTNUMBER IS VALID
        if (!portnumberField.getText().isEmpty()) {
            try {
                Integer.parseInt(portnumberField.getText());
            } catch (NumberFormatException e) {
                result = false;
                showError("Portnumber is not a valid number");
            }
        } else if (result) {
            result = false;
            showError("Portnumber cannot be empty");
        }

        //CANT LOGIN IF ALREADY LOGGED IN
        if (client.getLoggedIn() && result) {
            result = false;
            showError("You are already logged in FOOL");
        }

        return result;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        chatArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        listOfUsersArea = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        writeMessageArea = new javax.swing.JTextArea();
        usernameLabel = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        hostnameLabel = new javax.swing.JLabel();
        hostnameField = new javax.swing.JTextField();
        portnumberLabel = new javax.swing.JLabel();
        portnumberField = new javax.swing.JTextField();
        joinChatButton = new javax.swing.JButton();
        logOutButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        sendMessageButton = new javax.swing.JButton();

        jButton1.setText("Speak");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        chatArea.setEditable(false);
        chatArea.setColumns(20);
        chatArea.setLineWrap(true);
        chatArea.setRows(5);
        chatArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(chatArea);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        listOfUsersArea.setEditable(false);
        listOfUsersArea.setColumns(20);
        listOfUsersArea.setRows(5);
        jScrollPane2.setViewportView(listOfUsersArea);

        writeMessageArea.setColumns(20);
        writeMessageArea.setLineWrap(true);
        writeMessageArea.setRows(5);
        writeMessageArea.setWrapStyleWord(true);
        writeMessageArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                writeMessageAreaKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(writeMessageArea);

        usernameLabel.setText("Username:");

        usernameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                usernameFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                usernameFieldKeyTyped(evt);
            }
        });

        hostnameLabel.setText("Hostname:");

        portnumberLabel.setText("Port number:");

        portnumberField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                portnumberFieldKeyReleased(evt);
            }
        });

        joinChatButton.setText("Join chat");
        joinChatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinChatButtonActionPerformed(evt);
            }
        });

        logOutButton.setText("Log out");
        logOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("List of users online");

        jLabel5.setText("Write message");

        jLabel6.setText("Chat");

        sendMessageButton.setText("Send");
        sendMessageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendMessageButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sendMessageButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(joinChatButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(logOutButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(portnumberLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(hostnameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(hostnameField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usernameField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(portnumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(usernameLabel)
                            .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hostnameLabel)
                            .addComponent(hostnameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(portnumberLabel)
                            .addComponent(portnumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendMessageButton)
                    .addComponent(joinChatButton)
                    .addComponent(logOutButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutButtonActionPerformed
        client.logout();
        chatArea.setText("");
        writeMessageArea.setText("");
        listOfUsersArea.setText("");
        usernameField.setText("");
    }//GEN-LAST:event_logOutButtonActionPerformed

    private void joinChatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinChatButtonActionPerformed

        String username = usernameField.getText();
        if (validLogin()) {
            int portnumber = Integer.parseInt(portnumberField.getText());
            String hostname = hostnameField.getText();
            client.connect(hostname, portnumber, username);
        }
    }//GEN-LAST:event_joinChatButtonActionPerformed

    private void sendMessageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendMessageButtonActionPerformed

        String message = writeMessageArea.getText();
        if (message.length() <= 250 && client.getLoggedIn()) {
            client.sendChatLine(message);
            writeMessageArea.setText("");
        } else if (client.getLoggedIn()) {
            showError("You're message contains more than 250 character, you must reduce the number of characters.");
        } else {
            showError("Check if you are logged in.");
        }

    }//GEN-LAST:event_sendMessageButtonActionPerformed

    private void writeMessageAreaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_writeMessageAreaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            sendMessageButton.doClick();
        }
    }//GEN-LAST:event_writeMessageAreaKeyReleased

    private void usernameFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameFieldKeyReleased


    }//GEN-LAST:event_usernameFieldKeyReleased

    private void usernameFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameFieldKeyTyped

        if ((evt.getKeyChar() + "").matches("[^a-zA-Z0-9_-]")) {
            evt.consume();
        }
    }//GEN-LAST:event_usernameFieldKeyTyped

    private void portnumberFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_portnumberFieldKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            joinChatButton.doClick();
        }

    }//GEN-LAST:event_portnumberFieldKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GuiClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GuiClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GuiClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GuiClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GuiClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea chatArea;
    private javax.swing.JTextField hostnameField;
    private javax.swing.JLabel hostnameLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton joinChatButton;
    private javax.swing.JTextArea listOfUsersArea;
    private javax.swing.JButton logOutButton;
    private javax.swing.JTextField portnumberField;
    private javax.swing.JLabel portnumberLabel;
    private javax.swing.JButton sendMessageButton;
    private javax.swing.JTextField usernameField;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextArea writeMessageArea;
    // End of variables declaration//GEN-END:variables
}
