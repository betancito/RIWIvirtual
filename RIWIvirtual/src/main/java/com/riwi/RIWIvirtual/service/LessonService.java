package com.riwi.RIWIvirtual.service;

import com.riwi.RIWIvirtual.dtos.lessons.LessonResponse;
import com.riwi.RIWIvirtual.entity.RiwiLesson;
import org.springframework.http.ResponseEntity;

public interface LessonService {
    public RiwiLesson createLesson(RiwiLesson lesson);

    public RiwiLesson disableLesson(Long id) throws Exception;

    public ResponseEntity<LessonResponse> getLessonWithMultimedia (Long id);
}
