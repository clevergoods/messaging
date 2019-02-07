package by.vlasov.messaging.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Component
@ControllerAdvice
@ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
@ResponseBody
@Slf4j
public class MessagingExceptionHandler {

    @ExceptionHandler(value = { SQLException.class, DataAccessException.class, PersistenceException.class })
    public void handleSql(Exception ex, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.error("Request from {} raised error: {} ", request.getRequestURL(), ex.getLocalizedMessage());
        response.sendError(405, ex.getLocalizedMessage());
    }

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public void handleParameter(Exception ex, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.error("Request from {} raised error: {} ", request.getRequestURL(), ex.getLocalizedMessage());
        response.sendError(406, ex.getLocalizedMessage());
    }

    @ExceptionHandler(value = { IllegalArgumentException.class })
    public void handleConflict(Exception ex, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.error("Request from {} raised error: {} ", request.getRequestURL(), ex.getLocalizedMessage());
        response.sendError(409, ex.getLocalizedMessage());
    }

    @ExceptionHandler(value = { Exception.class })
    public void handleException(Exception ex, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.error("Request from {} raised error: {} ", request.getRequestURL(), ex.getLocalizedMessage());
        response.sendError(500, ex.getLocalizedMessage());
    }
}
