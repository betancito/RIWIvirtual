package com.riwi.RIWIvirtual.service;

import com.riwi.RIWIvirtual.entity.riwiLesson;
import com.riwi.RIWIvirtual.repository.iLesson;
import org.springframework.beans.factory.annotation.Autowired;
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
}
