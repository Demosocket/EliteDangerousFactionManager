package com.demosocket.manager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {

    private Long id;
    private String userName;
    private String date;
    private String messageHeader;
    private String combatTask;
    private String electionTask;
    private String task1;
    private String task2;
    private String doNothing;
    private String taskDescription;
}
