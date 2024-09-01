package com.riwi.RIWIvirtual.service;

import com.riwi.RIWIvirtual.dtos.lessons.ErrorResponse;
import com.riwi.RIWIvirtual.dtos.lessons.LessonResponse;
import com.riwi.RIWIvirtual.entity.riwiLesson;
import com.riwi.RIWIvirtual.repository.iLesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class lessonImplementation implements lessonService {
    @Autowired
    iLesson lessonRepository;

    @Override
    public riwiLesson createLesson(riwiLesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public riwiLesson disableLesson(Long id) throws Exception{
        Optional<riwiLesson> lessonOptional = lessonRepository.findById(id);
        if (lessonOptional.isPresent()){
            riwiLesson lesson = lessonOptional.get();
            lesson.setStatus(false); // disable the course by setting status to false
            lesson.setMultimediaContent(null); //delete multimedia content
            return lessonRepository.save(lesson);
        }else {
            throw new Exception("Lesson not found");
        }
    }

    @Override
    public ResponseEntity<LessonResponse> getLessonWithMultimedia(Long id){
        Optional<riwiLesson> lessonOptional = lessonRepository.findById(id);
        if (lessonOptional.isPresent()){
            riwiLesson lesson = lessonOptional.get();
            LessonResponse lessonResponse = new LessonResponse(
                    lesson.getTitle(),
                    lesson.getDescription(),
                    lesson.isStatus(),
                    lesson.getAssignedClass(),
                    lesson.getMultimediaContent()
            );
            return ResponseEntity.ok(lessonResponse);
        }else {
            return (ResponseEntity<LessonResponse>) ResponseEntity.status(HttpStatus.NOT_FOUND);
        }
    }
}
