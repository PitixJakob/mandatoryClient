package model;

import java.net.Socket;
import java.util.Timer;

/**
 * Created by jakob on 07-09-16.
 */
public class Connection {
    private User user;
    private Socket socket;
    private String ip;
    private int port;
    private Timer timer;
}
