package gov.ffx.fire.ops.resources_service.domain.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StationListItem {
  private int stationDesignator;
  private String stationName;
  private int battalion;
}
