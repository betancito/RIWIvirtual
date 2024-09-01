package com.riwi.RIWIvirtual.service;

import com.riwi.RIWIvirtual.dtos.lessons.LessonResponse;
import com.riwi.RIWIvirtual.entity.riwiLesson;
import org.springframework.http.ResponseEntity;

public interface lessonService  {
    public riwiLesson createLesson(riwiLesson lesson);

    public riwiLesson disableLesson(Long id) throws Exception;

    public ResponseEntity<LessonResponse> getLessonWithMultimedia (Long id);
}
