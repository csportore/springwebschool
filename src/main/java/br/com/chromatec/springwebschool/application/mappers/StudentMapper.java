package br.com.chromatec.springwebschool.application.mappers;

import br.com.chromatec.springwebschool.application.representations.StudentRepresentation;
import br.com.chromatec.springwebschool.domain.entities.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    Student representationToEntity (StudentRepresentation representation);
    StudentRepresentation entityToRepresentation(Student entity);
}
