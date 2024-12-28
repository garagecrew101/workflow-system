
package com.workflow.api.exception;

import com.workflow.common.exception.WorkflowNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
   
   @ExceptionHandler(WorkflowNotFoundException.class)
   public ResponseEntity<ErrorResponse> handleWorkflowNotFoundException(WorkflowNotFoundException e) {
       log.error("Workflow not found: {}", e.getMessage());
       return ResponseEntity
               .status(HttpStatus.NOT_FOUND)
               .body(new ErrorResponse("WORKFLOW_NOT_FOUND", e.getMessage()));
   }

   @ExceptionHandler(Exception.class)
   public ResponseEntity<ErrorResponse> handleAllExceptions(Exception e) {
       log.error("Unexpected error occurred", e);
       return ResponseEntity
               .status(HttpStatus.INTERNAL_SERVER_ERROR)
               .body(new ErrorResponse("INTERNAL_SERVER_ERROR", "내부 서버 오류가 발생했습니다."));
   }
}

@Getter
@AllArgsConstructor
class ErrorResponse {
   private String code;
   private String message;
}

