package com.riwi.RIWIvirtual.dtos.lessons;

import com.riwi.RIWIvirtual.entity.riwiClass;
import lombok.*;

import java.util.List;

    @Data
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class LessonResponse {

        private String title;
        private String description;
        private boolean status;
        private riwiClass assignedClass;
        private List<String> multimediaContent;

    }
