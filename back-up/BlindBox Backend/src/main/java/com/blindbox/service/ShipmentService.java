package com.blindbox.service;

import com.blindbox.model.Shipment;

import java.util.List;

public interface ShipmentService {
    List<Shipment> getAllShipments();
    Shipment getShipmentById(Integer id);
    Shipment createShipment(Shipment shipment);
    Shipment updateShipment(Integer id, Shipment shipment);
    boolean deleteShipment(Integer id);
}
