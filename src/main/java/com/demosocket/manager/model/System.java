package com.demosocket.manager.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "nagiisys")
public class System {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sys_id")
    private Integer id;

    @Column(name = "sys_num")
    private Integer sysNum;

    @Column(name = "sys")
    private String sys;

    @Column(name = "population")
    private Long population;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date date;
}
