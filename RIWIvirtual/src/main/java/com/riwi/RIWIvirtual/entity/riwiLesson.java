package com.riwi.RIWIvirtual.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "lessons")
public class riwiLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean active;

    @ElementCollection
    private List<String> multimediaContent;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class relatedClass;

    @OneToMany(mappedBy = "assignedClass")
    private List<Student> students;
}
