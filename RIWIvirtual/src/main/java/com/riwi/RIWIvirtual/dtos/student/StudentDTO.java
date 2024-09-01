package com.riwi.RIWIvirtual.dtos.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private Long id;
    private String name;
    private String email;
    private boolean active;
    private Long assignedClassId;
}
