package gov.ffx.fire.ops.resources_service.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ErrorResponse {
  String errorMessage;
  Integer statusCode;
  HttpStatus status;
}
