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
@Table(name = "influence")
public class Influence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "system_id")
    private Long system_id;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "system_id", referencedColumnName = "id")
//    private System system;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "influence")
    private Integer influence;

    @Column(name = "state")
    @Enumerated(value = EnumType.STRING)
    private State state;
}
