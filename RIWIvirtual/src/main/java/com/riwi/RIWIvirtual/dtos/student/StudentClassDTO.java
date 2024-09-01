package com.riwi.RIWIvirtual.dtos.student;

import com.riwi.RIWIvirtual.entity.RiwiClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentClassDTO {
    private Long id;
    private String name;
    private String email;
    private boolean active;
    private LocalDateTime createdAt;
    private RiwiClass assignedClass;
}