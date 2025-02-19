package com.blinkbox.service.impl;

import com.blinkbox.model.Shipment;
import com.blinkbox.repository.ShipmentRepository;
import com.blinkbox.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Override
    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }

    @Override
    public Shipment getShipmentById(Long id) {
        Optional<Shipment> shipment = shipmentRepository.findById(id);
        return shipment.orElse(null);
    }

    @Override
    public Shipment createShipment(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

    @Override
    public Shipment updateShipment(Long id, Shipment shipment) {
        if (shipmentRepository.existsById(id)) {
            shipment.setShipmentID(id);
            return shipmentRepository.save(shipment);
        }
        return null;
    }

    @Override
    public void deleteShipment(Long id) {
        shipmentRepository.deleteById(id);
    }
}
