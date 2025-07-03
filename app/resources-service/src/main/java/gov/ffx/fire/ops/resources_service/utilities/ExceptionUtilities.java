package gov.ffx.fire.ops.resources_service.utilities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@Component
public class ExceptionUtilities {

  public static List<String> getMethodExceptions(HandlerMethodValidationException exception) {
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
