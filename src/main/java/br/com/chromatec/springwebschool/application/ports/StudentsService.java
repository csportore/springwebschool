package br.com.chromatec.springwebschool.application.ports;

import br.com.chromatec.springwebschool.application.exceptions.StudentNotFoundException;
import br.com.chromatec.springwebschool.application.mappers.StudentMapper;
import br.com.chromatec.springwebschool.application.representations.StudentRepresentation;
import br.com.chromatec.springwebschool.infrastructure.ports.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StudentsService {

    private StudentsRepository repository;

    public StudentsService(@Autowired StudentsRepository repository) {
        this.repository = repository;
    }

    public List<StudentRepresentation> findAll() {
        var entityList = this.repository.findAll();
        var representationList = new ArrayList<StudentRepresentation>();
        entityList.forEach( s -> representationList.add(new StudentRepresentation(s.getId(), s.getName(), s.getSurname())));
        return representationList;
    }

    public StudentRepresentation findById(UUID id) throws StudentNotFoundException {
        var entity =  this.repository.findById(id);
        return StudentMapper.INSTANCE.entityToRepresentation(entity.orElseThrow( () -> new StudentNotFoundException(
                String.format("Student with id %s not found", id))
        ) );
    }
}
