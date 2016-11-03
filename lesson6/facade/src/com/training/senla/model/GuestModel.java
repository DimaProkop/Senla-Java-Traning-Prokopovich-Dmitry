package com.training.senla.model;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public class GuestModel extends BaseModel implements Serializable{

    private static final Logger LOG = LogManager.getLogger(GuestModel.class);
    private static final long serialVersionUID = -4477116269261501412L;

    private String name;
    private RoomModel roomModel;
    private List<ServiceModel> serviceModelList;

    public GuestModel() {

    }

    public GuestModel(String name) {
        this.name = name;
        this.roomModel = null;
        this.serviceModelList = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoomModel getRoomModel() {
        return roomModel;
    }

    public void setRoomModel(RoomModel roomModel) {
        this.roomModel = roomModel;
    }

    public List<ServiceModel> getServiceModelList() {
        return serviceModelList;
    }

    public void setServiceModelList(List<ServiceModel> serviceModelList) {
        this.serviceModelList = serviceModelList;
    }

    public void addService(ServiceModel serviceModel) {
        if(serviceModelList == null) {
            serviceModelList = new ArrayList<>();
        }
        try {
            serviceModelList.add(serviceModel);
        } catch (Exception e) {
            LOG.error(e);
        }

    }

    public void removeService(ServiceModel serviceModel) {
        if(serviceModelList == null) {
            LOG.info("Services is empty");
        }
        try {
            for (int i = 0; i < serviceModelList.size(); i++) {
                if (serviceModelList.get(i).getId() == serviceModel.getId()) {
                    serviceModelList.remove(i);
                    return;
                }
            }
        } catch (Exception e) {
            LOG.error(e);
            LOG.info("Service not found");
        }
    }
}
