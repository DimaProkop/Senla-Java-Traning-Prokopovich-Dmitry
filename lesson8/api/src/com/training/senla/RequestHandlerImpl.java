package com.training.senla;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by dmitry on 27.11.16.
 */
public class RequestHandlerImpl implements RequestHandler{
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public RequestHandlerImpl(Socket socket) {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object sendRequest(DataPacket packet) {
        Object response = null;
        try {
            out.writeObject(packet);
            out.flush();
            response = in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public void disconnect() {
        try {
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
