package com.smhrd.soolsool.controller;

import com.smhrd.soolsool.domain.Liquid;
import com.smhrd.soolsool.service.LiquidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class LiquidController {
    @Autowired
    private LiquidService liquidService;

    @GetMapping("/Info")
    public ResponseEntity<List<Liquid>> list() {
        List<Liquid> liquids = liquidService.findAll();
        return ResponseEntity.ok(liquids);
    }

}
