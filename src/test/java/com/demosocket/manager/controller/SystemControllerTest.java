//package com.demosocket.manager.controller;
//
//import com.demosocket.manager.model.System;
//import com.demosocket.manager.service.SystemService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.util.ReflectionTestUtils;
//import org.springframework.ui.ExtendedModelMap;
//import org.springframework.ui.Model;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//class SystemControllerTest {
//
//    private final List<System> systems = new ArrayList<>();
//
//    @MockBean
//    public SystemService systemService;
//
//    @BeforeEach
//    public void initSystems() throws ParseException {
//        System system = new System();
//        system.setId(1);
//        system.setSysNum(1);
//        system.setSys("Nagii");
//        system.setPopulation(4000000L);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
//        Date date = simpleDateFormat.parse("01-01-2020");
//        system.setDate(date);
//
//        systems.add(system);
//    }
//
//    @Test
//    public void findAll() {
//        Mockito.when(systemService.findAll()).thenReturn(systems);
//
//        SystemController systemController = new SystemController(systemService);
//        ReflectionTestUtils.setField(systemController, "systemService", systemService);
//
//        ExtendedModelMap uiModel = new ExtendedModelMap();
//        uiModel.addAttribute("systems", systemController.findAll());
//
//
//    }
//}