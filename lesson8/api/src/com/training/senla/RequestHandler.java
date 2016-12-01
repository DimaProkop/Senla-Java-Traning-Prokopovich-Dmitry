package com.training.senla;

/**
 * Created by dmitry on 30.11.16.
 */
public interface RequestHandler {
    Object sendRequest(DataPacket packet);
    void disconnect();
}
