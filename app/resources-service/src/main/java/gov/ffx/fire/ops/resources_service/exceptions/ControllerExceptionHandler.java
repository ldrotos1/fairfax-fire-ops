package gov.ffx.fire.ops.resources_service.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@RestControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(StationDoesNotExistException.class)
  public ResponseEntity<ErrorResponse> handleStationDoesNotExistException(StationDoesNotExistException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
      .errorMessage(exception.getMessage())
      .status(HttpStatus.NOT_FOUND)
      .statusCode(HttpStatus.NOT_FOUND.value())
      .build());
  }

  @ExceptionHandler(HandlerMethodValidationException.class)
  public ResponseEntity<ValidationErrorResponse> handleMethodArgumentNotValidException(HandlerMethodValidationException exception) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationErrorResponse.builder()
      .errorMessage("Request has vaildation errors")
      .status(HttpStatus.BAD_REQUEST)
      .statusCode(HttpStatus.BAD_REQUEST.value())
      .validationErrors(getMethodExceptions(exception))
      .build());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGenericException(Exception exception) {
    exception.printStackTrace();
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.builder()
      .errorMessage("An internal system error occured")
      .status(HttpStatus.INTERNAL_SERVER_ERROR)
      .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
      .build());
  }

  private List<String> getMethodExceptions(HandlerMethodValidationException exception) {
    List<String> errorMessages =  new ArrayList<>();
    List<ParameterValidationResult> paramErrors = exception.getParameterValidationResults();
    for (ParameterValidationResult paramError : paramErrors) {
      String paramName =  paramError.getMethodParameter().getParameterName();
      for (MessageSourceResolvable error : paramError.getResolvableErrors()) {
        errorMessages.add(paramName + ": " + error.getDefaultMessage());
      }
    }
    return errorMessages;
  }
}