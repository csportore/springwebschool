package br.com.chromatec.springwebschool.application.ports;

import br.com.chromatec.springwebschool.application.exceptions.StudentNotFoundException;
import br.com.chromatec.springwebschool.application.mappers.StudentMapper;
import br.com.chromatec.springwebschool.application.representations.StudentRepresentation;
import br.com.chromatec.springwebschool.infrastructure.ports.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentsService {

    private final StudentsRepository repository;

    public StudentsService(@Autowired StudentsRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<StudentRepresentation> findAll() {
        var entityList = this.repository.findAll();
        var representationList = new ArrayList<StudentRepresentation>();
        entityList.forEach( s -> representationList.add(new StudentRepresentation(s.getId(), s.getName(), s.getSurname())));
        return representationList;
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public StudentRepresentation findById(BigInteger id) throws StudentNotFoundException {
        var entity =  this.repository.findById(id);
        return StudentMapper.INSTANCE.entityToRepresentation(entity.orElseThrow( () -> new StudentNotFoundException(
                String.format("Student with id %s not found", id))
        ) );
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public StudentRepresentation insert(StudentRepresentation representation) {
        var entity = this.repository.save(StudentMapper.INSTANCE.representationToEntity(representation));
        return StudentMapper.INSTANCE.entityToRepresentation(entity);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public StudentRepresentation update(BigInteger id, StudentRepresentation representation) throws StudentNotFoundException {
        var optEntity = this.repository.findById(id);
        var entity = optEntity.orElseThrow(() -> new StudentNotFoundException(
                String.format("Student with id %s not found", id))
        ) ;
        entity.setName(representation.name());
        entity.setSurname(representation.surname());

        this.repository.save(entity);
        return StudentMapper.INSTANCE.entityToRepresentation(entity);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public BigInteger delete(BigInteger id) throws StudentNotFoundException {
        try {
            this.repository.deleteById(id);
        } catch (IllegalArgumentException iae) {
            throw new StudentNotFoundException(String.format("Student with id %s not found", id));
        }
        return id;
    }
}
