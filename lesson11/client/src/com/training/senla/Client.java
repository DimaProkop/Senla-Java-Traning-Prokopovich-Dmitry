package com.training.senla;

import com.training.senla.launcher.Launcher;
import com.training.senla.menu.Builder;
import com.training.senla.service.RequestHandler;
import com.training.senla.service.RequestHandlerImpl;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by dmitry on 26.11.16.
 */

public class Client {

    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 3000;

    public static void main(String args[]) throws IOException {
        Socket client = new Socket(ADDRESS, PORT);
        RequestHandler requestHandler = new RequestHandlerImpl(client);
        Builder builder = new Builder();
        Launcher launcher = new Launcher(requestHandler);
        launcher.start(builder.buildMenu());

    }
}