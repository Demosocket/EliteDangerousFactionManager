package com.demosocket.manager.repository;

import com.demosocket.manager.model.Influence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface InfluenceRepository extends JpaRepository<Influence, Long> {

    @Query(value = "SELECT i.date FROM Influence i GROUP BY i.date ORDER BY i.date DESC LIMIT 2", nativeQuery = true)
    List<Date> findTwoLastDays();

    List<Influence> findAllByDateOrderById(Date date);
}
