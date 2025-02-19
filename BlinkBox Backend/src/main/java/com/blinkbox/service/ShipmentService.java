package com.blinkbox.service;

import com.blinkbox.model.Shipment;
import java.util.List;

public interface ShipmentService {
    List<Shipment> getAllShipments();
    Shipment getShipmentById(Long id);
    Shipment createShipment(Shipment shipment);
    Shipment updateShipment(Long id, Shipment shipment);
    void deleteShipment(Long id);
}
