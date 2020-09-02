package com.demosocket.manager.service.impl;

import com.demosocket.manager.model.Inner;
import com.demosocket.manager.model.Outer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

class InnerOuterTest {

    private ModelMapper modelMapper;

    private Inner inner;
    private Inner inner2;

    @BeforeEach
    public void modelMapper() {
        inner = new Inner();
        inner.setName("testName");

        inner2 = new Inner();
        inner2.setName("testName2");

        modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setDeepCopyEnabled(true)
                .setFieldAccessLevel(PRIVATE);
    }

    @Test
    void simpleObjectMapper() {

        Outer outer = createTestObj();
        Outer outerMapped = modelMapper.map(outer, Outer.class);
        assertNotSame(outerMapped, outer);
    }

    @Test
    void DeepCopyOnlyObjectMapper() {

        Outer outer = createTestObj();
        Outer outerMapped = modelMapper.map(outer, Outer.class);
        assertEquals(outerMapped.getLastName(), outer.getLastName());
    }

    @Test
    void objectWithCollectionMapper() {

        Outer outer1 = createTestObj();
        Outer outer2 = modelMapper.map(outer1, Outer.class);
        assertNotSame(new ArrayList<>(new HashSet<>(outer1.getClasses())).get(1), new ArrayList<>(new HashSet<>(outer2.getClasses())).get(1));
        assertEquals(new ArrayList<>(new HashSet<>(outer1.getClasses())).size(), 2);
        assertEquals(new ArrayList<>(new HashSet<>(outer1.getClasses())).size(), 2);
        assertEquals(new ArrayList<>(new HashSet<>(outer1.getClasses())).get(1).getName(), new ArrayList<>(new HashSet<>(outer2.getClasses())).get(1).getName());
    }

    private Outer createTestObj() {
        Outer outer = new Outer();
        outer.setLastName("lastName");

        Collection<Inner> collection = new HashSet<>();
        collection.add(inner);
        collection.add(inner2);

        outer.setClasses(collection);
        return outer;
    }
}