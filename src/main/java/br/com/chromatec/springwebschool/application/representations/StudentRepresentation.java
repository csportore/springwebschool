package br.com.chromatec.springwebschool.application.representations;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public record StudentRepresentation (@JsonIgnore UUID id, String name, String surname) { }
