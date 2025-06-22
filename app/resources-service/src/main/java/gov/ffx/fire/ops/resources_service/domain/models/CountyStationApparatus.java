package gov.ffx.fire.ops.resources_service.domain.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountyStationApparatus {
  private String unitDesignator;
  private String apparatusType;
  private String apparatusCategory;
  private Integer year;
  private String make;
  private String model;
  private Boolean isReserved;
}
