package com.ms.authservice.services;

import com.ms.authservice.dto.StudentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="student-service")
public interface StudentRestConsumer {
    @PostMapping("/create")
    void create(@RequestBody StudentDto studentDto);
}