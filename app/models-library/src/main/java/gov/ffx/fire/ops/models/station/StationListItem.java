package gov.ffx.fire.ops.models.station;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StationListItem {
  private int stationDesignator;
  private String stationName;
  private int battalion;
}
