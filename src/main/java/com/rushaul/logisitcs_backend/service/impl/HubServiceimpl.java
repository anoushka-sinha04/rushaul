package com.rushaul.logisitcs_backend.service.impl;

import com.rushaul.logisitcs_backend.model.Hub;
import com.rushaul.logisitcs_backend.repository.HubRepository;
import com.rushaul.logisitcs_backend.service.HubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HubServiceimpl implements HubService {

    // -------------------------------------------------- DEPENDENCIES
    private final HubRepository hubRepository;

    // -------------------------------------------------- CONSTRUCTOR
    @Autowired
    public HubServiceimpl(HubRepository hubRepository) {
        this.hubRepository = hubRepository;
    }


    // -------------------------------------------------- CREATE HUB
    @Override
    public Hub createHub(Hub hub) {
        return hubRepository.save(hub);
    }


    // -------------------------------------------------- GET HUB BY ID
    @Override
    public Optional<Hub> getHubById(Long id) {
        return hubRepository.findById(id);
    }


    // -------------------------------------------------- GET ALL HUBS
    @Override
    public List<Hub> getAllHubs() {
        return hubRepository.findAll();
    }


    // -------------------------------------------------- UPDATE HUB
    @Override
    public Hub updateHub(Long id, Hub hubDetails) {
        return hubRepository.findById(id).map(hub -> {
            hub.setName(hubDetails.getName());
            hub.setLocation(hubDetails.getLocation());
            return hubRepository.save(hub);
        }).orElseThrow(() -> new RuntimeException("Hub not found with ID: " + id));
    }


    // -------------------------------------------------- DELETE HUB
    @Override
    public void deleteHub(Long id) {
        hubRepository.deleteById(id);
    }
}
