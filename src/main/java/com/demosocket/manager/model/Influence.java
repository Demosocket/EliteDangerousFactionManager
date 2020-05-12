package com.demosocket.manager.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "influence")
public class Influence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "systems_id", referencedColumnName = "id")
    private System system;

    @Column(name = "day")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date day;

    @Column(name = "influence")
    private Integer influence;

    @Column(name = "state")
    @Enumerated(value = EnumType.STRING)
    private State state;
}
