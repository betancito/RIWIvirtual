package com.riwi.RIWIvirtual.controller;

import com.riwi.RIWIvirtual.entity.riwiLesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.riwi.RIWIvirtual.service.lessonService;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lessons")
public class lessonController {
    @Autowired
    private lessonService lessonService;

    @PostMapping("/create")
    public ResponseEntity<riwiLesson> createLesson(@RequestBody riwiLesson lesson){
        riwiLesson savedLesson = lessonService.createLesson(lesson);
        return new ResponseEntity<>(savedLesson, HttpStatus.CREATED);
    }

    @PatchMapping("/disable/{id}")
    public ResponseEntity<String> disableLesson(@PathVariable Long id){
        try{
            lessonService.disableLesson(id);
            return ResponseEntity.ok("Lesson disabled sucessfully");
        } catch (Exception error){
            return  ResponseEntity.status(404).body("Lesson couldn't be found");
        }
    }
}
