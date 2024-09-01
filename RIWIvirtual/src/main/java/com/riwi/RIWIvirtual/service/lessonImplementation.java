package com.riwi.RIWIvirtual.service;

import com.riwi.RIWIvirtual.entity.riwiLesson;
import com.riwi.RIWIvirtual.repository.iLesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class lessonImplementation implements lessonService {
    @Autowired
    iLesson lessonRepository;

    @Override
    public riwiLesson createLesson(riwiLesson lesson) {
        return lessonRepository.save(lesson);
    }
}
