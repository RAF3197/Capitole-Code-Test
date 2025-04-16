package com.example.capitolecodetest.shared.exceptions

import com.example.capitolecodetest.shared.entity.ErrorMessageModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(InvalidPaginationException::class)
    fun handleInvalidPagination(ex: InvalidPaginationException): ResponseEntity<ErrorMessageModel> {
        val errorMessage = ErrorMessageModel(
            HttpStatus.BAD_REQUEST.value(),
            ex.message)
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(errorMessage)
    }
}