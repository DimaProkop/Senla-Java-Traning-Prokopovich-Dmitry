package com.training.senla.util.service;

import java.util.List;

/**
 * Created by prokop on 7.11.16.
 */
public interface StreamService {
    void write(List objects, String fileName, String separator, int countFields);
}
