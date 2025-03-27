package com.blindbox.controller;

import com.blindbox.model.BlindBoxItem;
import com.blindbox.model.GachaHistory;
import com.blindbox.response.ResponseData;
import com.blindbox.service.GachaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Gacha Management System", description = "Operations pertaining to gacha in the Gacha Management System")
@RestController
@RequestMapping("/api/gacha")
public class GachaController {

    private final GachaService gachaService;

    @Autowired
    public GachaController(GachaService gachaService) {
        this.gachaService = gachaService;
    }

    @Operation(summary = "Open a blindbox", description = "Perform gacha and open a blindbox for a user")
    @GetMapping("/open-box")
    public ResponseEntity<ResponseData> openBox(@RequestParam Integer userId, @RequestParam Integer orderId) {
        try {
            List<BlindBoxItem> result = gachaService.openBlindbox(userId, orderId);
            return ResponseEntity.ok(new ResponseData(200, true, "Gacha completed successfully", result, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(500, false, "Failed to complete gacha: " + e.getMessage(), null, null));
        }
    }

    @Operation(summary = "Get all gacha history", description = "Retrieve all gacha history records")
    @GetMapping("/history")
    public ResponseEntity<ResponseData> getAllGachaHistory() {
        try {
            List<GachaHistory> result = gachaService.getAllGachaHistory();
            return ResponseEntity.ok(new ResponseData(200, true, "Gacha history retrieved successfully", result, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(500, false, "Failed to retrieve gacha history: " + e.getMessage(), null, null));
        }
    }

    @Operation(summary = "Get gacha history by user ID", description = "Retrieve gacha history records for a specific user")
    @GetMapping("/history/user")
    public ResponseEntity<ResponseData> getGachaHistoryByUserID(@RequestParam Integer userId) {
        try {
            List<GachaHistory> result = gachaService.getGachaHistoryByUserID(userId);
            return ResponseEntity.ok(new ResponseData(200, true, "Gacha history for user retrieved successfully", result, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(500, false, "Failed to retrieve gacha history for user: " + e.getMessage(), null, null));
        }
    }

}