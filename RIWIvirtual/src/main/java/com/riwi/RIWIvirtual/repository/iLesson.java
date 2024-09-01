package com.riwi.RIWIvirtual.repository;

import com.riwi.RIWIvirtual.entity.riwiLesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface iLesson extends JpaRepository<riwiLesson, Long> {
}
