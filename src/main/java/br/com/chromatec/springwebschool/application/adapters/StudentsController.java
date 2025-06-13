package br.com.chromatec.springwebschool.application.adapters;

import br.com.chromatec.springwebschool.application.exceptions.StudentNotFoundException;
import br.com.chromatec.springwebschool.application.ports.StudentsService;
import br.com.chromatec.springwebschool.application.representations.StudentRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;
import java.util.UUID;

@RestController
@RequestMapping(value = "/students", consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentsController {

    private final StudentsService service;

    public StudentsController(@Autowired StudentsService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") UUID id) throws StudentNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody StudentRepresentation representation) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.insert(representation));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") UUID id, @RequestBody StudentRepresentation representation) throws StudentNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.update(id, representation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) throws StudentNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.delete(id));
    }

}
