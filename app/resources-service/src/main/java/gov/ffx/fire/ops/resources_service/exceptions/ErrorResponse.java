package gov.ffx.fire.ops.resources_service.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
  String errorMessage;
  Integer statusCode;
  HttpStatus status;
}
