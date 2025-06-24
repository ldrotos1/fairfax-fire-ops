package gov.ffx.fire.ops.resources_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGenericException() {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.builder()
      .errorMessage("An internal system error occured")
      .status(HttpStatus.INTERNAL_SERVER_ERROR)
      .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
      .build());
  }
}
