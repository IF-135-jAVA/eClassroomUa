package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.exception.APIException;
import com.softserve.betterlearningroom.exception.UserAlreadyExistsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.util.ArrayList;

@ControllerAdvice
public class RESTExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ UserAlreadyExistsException.class })
    protected ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException ex,
            WebRequest request) {
        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

        APIException apiException = new APIException("User with current email already exists.", HttpStatus.BAD_REQUEST,
                LocalDateTime.now(), details);
        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }
    
    @ExceptionHandler({ UsernameNotFoundException.class })
    protected ResponseEntity<Object> handleUserNotFoundException(UsernameNotFoundException ex,
            WebRequest request) {
        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

        APIException apiException = new APIException("User not found.", HttpStatus.NOT_FOUND,
                LocalDateTime.now(), details);
        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }
    
    @ExceptionHandler({ BadCredentialsException.class })
    protected ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex,
            WebRequest request) {
        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

        APIException apiException = new APIException("Bad credentials.", HttpStatus.BAD_REQUEST,
                LocalDateTime.now(), details);
        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

        APIException apiException = new APIException("Malformed JSON request.", HttpStatus.BAD_REQUEST,
                LocalDateTime.now(), details);
        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getObjectName() + " : " + error.getDefaultMessage()).collect(Collectors.toList());

        APIException apiException = new APIException("Argument not valid.", HttpStatus.BAD_REQUEST, LocalDateTime.now(),
                details);
        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

        APIException apiException = new APIException("Type Mismatch.", HttpStatus.BAD_REQUEST, LocalDateTime.now(),
                details);
        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

        APIException apiException = new APIException("Constraint Violations.", HttpStatus.BAD_REQUEST,
                LocalDateTime.now(), details);
        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

        APIException apiException = new APIException("Path variable missing.", HttpStatus.BAD_REQUEST,
                LocalDateTime.now(), details);
        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<String>();
        details.add(ex.getParameterName() + " parameter is missing.");

        APIException apiException = new APIException("Servlet parameter missing.", HttpStatus.BAD_REQUEST,
                LocalDateTime.now(), details);
        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

        APIException apiException = new APIException("Method not Supported.", HttpStatus.BAD_REQUEST,
                LocalDateTime.now(), details);

        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<String>();
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));

        details.add(builder.toString());

        APIException apiException = new APIException("Media type not suported.", HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                LocalDateTime.now(), details);
        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<String>();
        details.add(String.format("Could not find the %s method for URL %s.", ex.getHttpMethod(), ex.getRequestURL()));

        APIException apiException = new APIException("No handler found.", HttpStatus.NOT_FOUND, LocalDateTime.now(),
                details);
        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }

    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex, HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        List<String> details = new ArrayList<String>();
        details.add("You dont' have rights to acces this resource.");

        APIException apiException = new APIException("Access denied.", HttpStatus.FORBIDDEN, LocalDateTime.now(),
                details);
        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<String>();
        details.add(ex.getLocalizedMessage());

        APIException apiException = new APIException("Error occurred.", HttpStatus.INTERNAL_SERVER_ERROR,
                LocalDateTime.now(), details);
        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }

}
