package com.riwi.RIWIvirtual.service;

import com.riwi.RIWIvirtual.dto.ClassDTO;
import com.riwi.RIWIvirtual.entity.riwiClass;
import com.riwi.RIWIvirtual.repository.IClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassService {

    @Autowired
    private IClassRepository classRepository;

    public List<ClassDTO> getAllClasses(String name, String description, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<riwiClass> classPage = classRepository.findAll(pageable);

        return classPage.stream()
                .filter(riwiClass -> (name == null || riwiClass.getName().contains(name)) &&
                        (description == null || riwiClass.getDescription().contains(description)) &&
                        riwiClass.isActive())
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ClassDTO convertToDTO(riwiClass riwiClass) {
        ClassDTO classDTO = new ClassDTO();
        classDTO.setId(riwiClass.getId());
        classDTO.setName(riwiClass.getName());
        classDTO.setDescription(riwiClass.getDescription());
        classDTO.setActive(riwiClass.isActive());
        return classDTO;
    }
}
