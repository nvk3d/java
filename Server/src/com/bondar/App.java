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
        /*System.out.println("Выбери свою сторону, парень: server or client");
        Scanner sc = new Scanner(System.in);
        while (res.equals("OK.")) {
            if (sc.nextLine().equalsIgnoreCase("server")) {
                res = "OK.";
                System.out.println(res);
            } else if (sc.nextLine().equalsIgnoreCase("client")) {
                res = "OK.";
                System.out.println(res);
            } else {
                System.out.println("Нужно выбрать сторону: server or client");
            }
        }*/
    }
}
