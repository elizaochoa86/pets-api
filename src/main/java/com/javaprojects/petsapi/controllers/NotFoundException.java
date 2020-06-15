package com.javaprojects.petsapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "value not found")
public class NotFoundException extends RuntimeException {
}
