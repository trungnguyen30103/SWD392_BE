package com.blindbox.service.impl;

import com.blindbox.model.Shipment;
import com.blindbox.repository.ShipmentRepository;
import com.blindbox.service.ShipmentService;
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
    public Shipment getShipmentById(Integer id) {
        Optional<Shipment> shipment = shipmentRepository.findById(id);
        return shipment.orElse(null);
    }

    @Override
    public Shipment createShipment(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

    @Override
    public Shipment updateShipment(Integer id, Shipment shipment) {
        if (shipmentRepository.existsById(id)) {
            shipment.setShipmentID(id);
            return shipmentRepository.save(shipment);
        }
        return null;
    }

    @Override
    public boolean deleteShipment(Integer id) {
        if (shipmentRepository.existsById(id)) {
            shipmentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
