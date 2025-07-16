package com.rushaul.logisitcs_backend.controller;

import com.rushaul.logisitcs_backend.model.Hub;
import com.rushaul.logisitcs_backend.service.HubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hubs")
public class HubController {

    // -------------------------------------------------- DEPENDENCIES
    private final HubService hubService;

    // -------------------------------------------------- CONSTRUCTOR
    @Autowired
    public HubController(HubService hubService) {
        this.hubService = hubService;
    }

    // -------------------------------------------------- CREATE HUB
    @PostMapping
    public ResponseEntity<Hub> createHub(@RequestBody Hub hub) {
        return ResponseEntity.ok(hubService.createHub(hub));
    }

    // -------------------------------------------------- GET HUB BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Hub> getHubById(@PathVariable Long id) {
        return hubService.getHubById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // -------------------------------------------------- GET ALL HUBS
    @GetMapping
    public ResponseEntity<List<Hub>> getAllHubs() {
        return ResponseEntity.ok(hubService.getAllHubs());
    }

    // -------------------------------------------------- UPDATE HUB
    @PutMapping("/{id}")
    public ResponseEntity<Hub> updateHub(@PathVariable Long id, @RequestBody Hub hubDetails) {
        return ResponseEntity.ok(hubService.updateHub(id, hubDetails));
    }

    // -------------------------------------------------- DELETE HUB
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHub(@PathVariable Long id) {
        hubService.deleteHub(id);
        return ResponseEntity.noContent().build();
    }
}
