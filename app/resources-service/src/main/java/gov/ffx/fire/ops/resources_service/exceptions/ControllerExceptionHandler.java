package gov.ffx.fire.ops.resources_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import gov.ffx.fire.ops.resources_service.utilities.ExceptionUtilities;

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
      .validationErrors(ExceptionUtilities.getMethodExceptions(exception))
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
}