package com.riwi.RIWIvirtual.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentDTO {
    private Long id;
    private String name;
    private String email;
    private boolean active;
    private LocalDateTime createdAt;
    private Long assignedClassId;
}
