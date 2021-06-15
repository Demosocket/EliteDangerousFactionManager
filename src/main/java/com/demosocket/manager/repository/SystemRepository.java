package com.demosocket.manager.repository;

import com.demosocket.manager.model.Faction;
import com.demosocket.manager.model.System;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SystemRepository extends JpaRepository<System, Long> {

    System findByName(String systemName);
    List<System> findAllByFaction(Faction faction);
    Integer countAllByFaction(Faction faction);
}
