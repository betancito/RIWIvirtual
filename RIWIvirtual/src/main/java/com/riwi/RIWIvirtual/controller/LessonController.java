package com.riwi.RIWIvirtual.controller;

import com.riwi.RIWIvirtual.dtos.lessons.LessonResponse;
import com.riwi.RIWIvirtual.entity.RiwiLesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.riwi.RIWIvirtual.service.LessonService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lessons")
public class LessonController {
    @Autowired
    private LessonService lessonService;

    @PostMapping("/create")
    public ResponseEntity<RiwiLesson> createLesson(@RequestBody RiwiLesson lesson){
        RiwiLesson savedLesson = lessonService.createLesson(lesson);
        return new ResponseEntity<>(savedLesson, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/disable")
    public ResponseEntity<String> disableLesson(@PathVariable Long id){
        try{
            lessonService.disableLesson(id);
            return ResponseEntity.ok("Lesson disabled sucessfully");
        } catch (Exception error){
            return  ResponseEntity.status(404).body("Lesson couldn't be found");
        }
    }

    @GetMapping("/{id}/multimedia")
    public  ResponseEntity<LessonResponse> getLessonWithMultimedia(@PathVariable Long id){
           return lessonService.getLessonWithMultimedia(id);
    }
}
