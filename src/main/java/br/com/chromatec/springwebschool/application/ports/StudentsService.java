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

    public StudentRepresentation insert(StudentRepresentation representation) {
        var entity = this.repository.save(StudentMapper.INSTANCE.representationToEntity(representation));
        return StudentMapper.INSTANCE.entityToRepresentation(entity);
    }

    public StudentRepresentation update(UUID id, StudentRepresentation representation) throws StudentNotFoundException {
        var optEntity = this.repository.findById(id);
        var entity = optEntity.orElseThrow(() -> new StudentNotFoundException(
                String.format("Student with id %s not found", id))
        ) ;
        entity.setName(representation.name());
        entity.setSurname(representation.surname());

        this.repository.save(entity);
        return StudentMapper.INSTANCE.entityToRepresentation(entity);
    }

    public UUID delete(UUID id) throws StudentNotFoundException {
        try {
            this.repository.deleteById(id);
        } catch (IllegalArgumentException iae) {
            throw new StudentNotFoundException(String.format("Student with id %s not found", id));
        }
        return id;
    }
}
