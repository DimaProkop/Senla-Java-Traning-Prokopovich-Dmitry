package com.training.senla.service;

import com.training.senla.di.DependencyInjection;
import com.training.senla.facade.Facade;

import java.io.EOFException;
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
            saveDataAfterExit();
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveDataAfterExit() {
        DataPacket packet = new DataPacket("exportAll", null);
        sendRequest(packet);
    }
}
