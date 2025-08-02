package br.com.chromatec.springwebschool.infrastructure.ports;

import br.com.chromatec.springwebschool.domain.entities.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface StudentsRepository extends CrudRepository<Student, BigInteger> {
}
