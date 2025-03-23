package com.blindbox.controller;

import com.blindbox.service.GachaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gacha")
public class GachaController {

    @Autowired
    private GachaService gachaService;

    @GetMapping("/open-box")
    public String openBox(@RequestParam Integer userId) {
        return gachaService.openBlindBox(userId);
    }
}
