package com.training.senla.di;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dmitry on 17.11.16.
 */
public class DependencyInjection {
    private Map<String, String> mediumMaps;
    private Map<String, Object> mainMaps;

    public DependencyInjection() {
        mediumMaps = new HashMap<>();
        mainMaps = new HashMap<>();
        InstanceProps props = new InstanceProps();
        mediumMaps.put("Facade.class", props.getFacade());
        mediumMaps.put("GuestModelService.class", props.getGuestModelService());
        mediumMaps.put("RoomModelService.class", props.getRoomModelService());
        mediumMaps.put("RegistrationModelService.class", props.getRegistrationModelService());
        mediumMaps.put("ServiceModelService.class", props.getServiceModelService());
        mediumMaps.put("GuestModelRepository.class", props.getGuestModelRepository());
        mediumMaps.put("RoomModelRepository.class", props.getRoomModelRepository());
        mediumMaps.put("RegistrationModelRepository.class", props.getRegistrationModelRepository());
        mediumMaps.put("ServiceModelRepository.class", props.getServiceModelRepository());
        mediumMaps.put("Converter.class", props.getConverter());
        mediumMaps.put("Exporter.class", props.getExporter());
        mediumMaps.put("Importer.class", props.getImporter());
        mediumMaps.put("DataService.class", props.getDataService());
        mediumMaps.put("StreamService.class", props.getStreamService());

    }

    public Object checkInstanceClass(String className) {
        Object object = mainMaps.get(className);
        if(object == null) {
            String nameInstance = mediumMaps.get(className);
            if(nameInstance != null) {
                try {
                    Class clazz = Class.forName(nameInstance);
                    Object obj = clazz.newInstance();
                    mainMaps.put(className, obj);
                    return obj;

                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }
        return object;
    }
}
