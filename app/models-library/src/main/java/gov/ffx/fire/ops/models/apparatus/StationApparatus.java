package gov.ffx.fire.ops.models.apparatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StationApparatus {
  private String unitDesignator;
  private String apparatusType;
  private String apparatusCategory;
  private Integer year;
  private String make;
  private String model;
  private Boolean isReserved;
}
