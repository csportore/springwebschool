package br.com.chromatec.springwebschool.infrastructure.ports;

import br.com.chromatec.springwebschool.domain.entities.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentsRepository extends CrudRepository<Student, UUID> {
}
