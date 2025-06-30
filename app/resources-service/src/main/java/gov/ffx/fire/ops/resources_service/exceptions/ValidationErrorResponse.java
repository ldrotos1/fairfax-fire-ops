package gov.ffx.fire.ops.resources_service.exceptions;

import java.util.List;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ValidationErrorResponse extends ErrorResponse {
  private List<String> validationErrors;
}
