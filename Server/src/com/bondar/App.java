package com.bondar;

/**
 * Created by Nikita Bondar 9.10.2016
 */

public class App {
    public static String res;

    public static void main(String[] args) {
	    ServerConnection serverConnection = new ServerConnection();
        serverConnection.setPort(8189);
        serverConnection.init();
        ClientWindow clientWindow = new ClientWindow();
    }
}
