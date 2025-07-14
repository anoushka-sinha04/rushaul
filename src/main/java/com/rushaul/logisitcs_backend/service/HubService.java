package com.rushaul.logisitcs_backend.service;

import com.rushaul.logisitcs_backend.model.Hub;

import java.util.List;
import java.util.Optional;

public interface HubService {
    Hub createHub(Hub hub);
    Optional<Hub> getHubById(Long id);
    List<Hub> getAllHubs();
    Hub updateHub(Long id, Hub hub);
    void deleteHub(Long id);
}