package br.com.chromatec.springwebschool.application.adapters;

import br.com.chromatec.springwebschool.application.ports.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentsController {


    private final StudentsService service;

    public StudentsController(@Autowired StudentsService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.findAll());
    }
}
