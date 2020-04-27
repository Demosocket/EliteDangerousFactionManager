package com.demosocket.manager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "systems")
public class System {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sys_id")
    private Integer id;

    @Column(name = "faction")
    @Enumerated(value = EnumType.STRING)
    private Faction faction;

    @Column(name = "sys_num")
    private Integer sysNum;

    @Column(name = "sys")
    private String sys;

    @Column(name = "population")
    private Long population;

    @Column(name = "primary_economy")
    @Enumerated(value = EnumType.STRING)
    private Economy primaryEconomy;

    @Column(name = "secondary_economy")
    @Enumerated(value = EnumType.STRING)
    private Economy secondaryEconomy;

    @Column(name = "orbit_large_control")
    private Integer orbitLargeControl;

    @Column(name = "orbit_large")
    private Integer orbitLarge;

    @Column(name = "orbit_medium_control")
    private Integer orbitMediumControl;

    @Column(name = "orbit_medium")
    private Integer orbitMedium;

    @Column(name = "planet_large_control")
    private Integer planetLargeControl;

    @Column(name = "planet_large")
    private Integer planetLarge;

    @Column(name = "planet_base_control")
    private Integer planetBaseControl;

    @Column(name = "planet_base")
    private Integer planetBase;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date date;
}
