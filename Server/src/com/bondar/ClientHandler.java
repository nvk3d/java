package com.bondar;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by nvk3d on 09.10.16.
 */

public class ClientHandler implements Runnable {
    private Socket s;
    private PrintWriter out;
    private Scanner in;
    private static int CLIENTS_COUNT = 0;
    private String name;

    public ClientHandler(Socket s) {
        try {
            this.s = s;
            out = new PrintWriter(s.getOutputStream());
            in = new Scanner(s.getInputStream());
            CLIENTS_COUNT++;
            name = "Client #" + CLIENTS_COUNT;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true) {
            if (in.hasNext()) {
                String w = in.nextLine();
                System.out.println(name + ":" + w);
                out.println("echo: " + w);
                out.flush();
                if (w.equalsIgnoreCase("end")) {
                    break;
                }
            }
        }
        try {
            System.out.println("Client disconnect");
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
