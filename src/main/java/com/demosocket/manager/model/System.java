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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nagiisys_sys_id_seq")
    @SequenceGenerator(sequenceName = "nagiisys_sys_id_seq", allocationSize = 1, name = "nagiisys_sys_id_seq")
    @Column(name = "sys_id")
    private Integer id;

    @Column(name = "sys")
    private String sys;

    @Column(name = "population")
    private Long population;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date date;
}
