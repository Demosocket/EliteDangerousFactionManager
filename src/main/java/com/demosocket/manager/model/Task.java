package com.demosocket.manager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "message_header")
    private String messageHeader;

    @Column(name = "combat_task")
    private String combatTask;

    @Column(name = "election_task")
    private String electionTask;

    @Column(name = "task_1")
    private String task1;

    @Column(name = "task_2")
    private String task2;

    @Column(name = "do_nothing")
    private String doNothing;

    @Column(name = "task_description")
    private String taskDescription;
}
