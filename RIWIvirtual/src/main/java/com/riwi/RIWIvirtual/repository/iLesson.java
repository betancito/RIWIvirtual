package com.riwi.RIWIvirtual.repository;

import com.riwi.RIWIvirtual.entity.RiwiLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iLesson extends JpaRepository<RiwiLesson, Long> {
}
