package com.blindbox.controller;

import com.blindbox.model.Shipment;
import com.blindbox.service.ShipmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Shipment Management System", description = "Operations pertaining to shipments in the Shipment Management System")
@RestController
@RequestMapping("/shipments")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    // Lấy tất cả thông tin giao hàng
    @Operation(summary = "Get all shipments", description = "Retrieve a list of all available shipments")
    @GetMapping
    public ResponseEntity<List<Shipment>> getAllShipments() {
        List<Shipment> shipments = shipmentService.getAllShipments();
        return new ResponseEntity<>(shipments, HttpStatus.OK);
    }

    // Lấy thông tin giao hàng theo ID
    @Operation(summary = "Get a shipment by ID", description = "Retrieve a single shipment using its ID")
    @GetMapping("/{id}")
    public ResponseEntity<Shipment> getShipmentById(@PathVariable Integer id) {
        Shipment shipment = shipmentService.getShipmentById(id);
        if (shipment != null) {
            return new ResponseEntity<>(shipment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Tạo mới thông tin giao hàng
    @Operation(summary = "Create a new shipment", description = "Add a new shipment to the system")
    @PostMapping
    public ResponseEntity<Shipment> createShipment(@RequestBody Shipment shipment) {
        Shipment createdShipment = shipmentService.createShipment(shipment);
        return new ResponseEntity<>(createdShipment, HttpStatus.CREATED);
    }

    // Cập nhật thông tin giao hàng
    @Operation(summary = "Update an existing shipment", description = "Update an existing shipment using its ID")
    @PutMapping("/{id}")
    public ResponseEntity<Shipment> updateShipment(@PathVariable Integer id, @RequestBody Shipment shipment) {
        Shipment updatedShipment = shipmentService.updateShipment(id, shipment);
        if (updatedShipment != null) {
            return new ResponseEntity<>(updatedShipment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Xóa thông tin giao hàng
    @Operation(summary = "Delete a shipment by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShipment(@PathVariable Integer id) {
        if (shipmentService.deleteShipment(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
