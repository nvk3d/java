package com.bondar;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by nvk3d on 09.10.16.
 */
public class ServerConnection implements Runnable {
    private Integer port;
    private ServerSocket server = null;
    private Socket s = null;

    public ServerConnection() {

    }

    @Override
    public void run() {
        try {
            while (true) {
                s = server.accept();
                System.out.println("New client connection");
                new Thread(new ClientHandler(s)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
                System.out.println("Server closed");
                s.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void init() {
        try {
            server = new ServerSocket(this.port);
            System.out.println("Server is running ...");
            Thread thread = new Thread(this);
            thread.start();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getPort() {
        return this.port;
    }

}
