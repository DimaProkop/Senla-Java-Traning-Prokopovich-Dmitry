package com.training.senla.service;

/**
 * Created by dmitry on 30.11.16.
 */
public interface RequestHandler {
    Object sendRequest(DataPacket packet);
    void disconnect();
}
