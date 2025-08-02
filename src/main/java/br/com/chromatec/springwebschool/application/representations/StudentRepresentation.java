package br.com.chromatec.springwebschool.application.representations;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigInteger;

public record StudentRepresentation (@JsonIgnore BigInteger id, String name, String surname) { }
