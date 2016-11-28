package com.training.senla.util.service;

import java.util.List;

/**
 * Created by prokop on 3.11.16.
 */
public interface DataService {

    List<Object> loadData();

    void saveData(Object object);

    List<?> loadEntity();

    void saveEnitty(List<?> list);
}
