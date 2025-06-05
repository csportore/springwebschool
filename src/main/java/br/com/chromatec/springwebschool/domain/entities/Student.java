package br.com.chromatec.springwebschool.domain.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "students")
public class Student implements Serializable {

        @Id
        @GeneratedValue
        private UUID id;

        @Column(nullable = false, length = 200)
        private String name;

        @Column(nullable = false, length = 200)
        private String surname;

        public UUID getId() {
                return id;
        }

        public void setId(UUID id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getSurname() {
                return surname;
        }

        public void setSurname(String surname) {
                this.surname = surname;
        }
}
