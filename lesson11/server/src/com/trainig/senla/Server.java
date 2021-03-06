package com.trainig.senla;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by dmitry on 26.11.16.
 */
public class Server {
    private static final int PORT = 3000;

    public static void main(String[] args) {
        ServerSocket server = null;

        try {
            ServerThread thread;
            server = new ServerSocket(PORT);
            while (true) {
                System.out.println("Waiting for a client..");
                Socket socket = server.accept();
                thread = new ServerThread(socket);
                System.out.println("Client " + thread.getName() + " connected.");
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
