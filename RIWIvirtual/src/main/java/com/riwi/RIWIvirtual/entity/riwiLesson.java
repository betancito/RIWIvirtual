package com.riwi.RIWIvirtual.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private boolean status;

    @ElementCollection
    private List<String> multimediaContent;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private riwiClass assignedClass;
}
