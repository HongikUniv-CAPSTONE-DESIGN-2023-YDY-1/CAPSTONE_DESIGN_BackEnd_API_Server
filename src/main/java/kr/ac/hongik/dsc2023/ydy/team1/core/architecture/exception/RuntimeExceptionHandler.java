package kr.ac.hongik.dsc2023.ydy.team1.core.architecture.exception;

import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;

@Slf4j
@RestControllerAdvice
public class RuntimeExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> handle(RuntimeException e){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        log.error("\n"+sw);
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
        if(e instanceof SecurityException){
            return ResponseEntity.status(401).body(errorMessage);
        }
        if(e instanceof IllegalArgumentException){
            return ResponseEntity.status(400).body(errorMessage);
        }
        if(e instanceof IllegalStateException){
            return ResponseEntity.status(409).body(errorMessage);
        }
        return ResponseEntity.internalServerError().body(errorMessage);
    }
    @Getter
    @AllArgsConstructor
    private static class ErrorMessage{
        private String errorMessage;
    }
}
