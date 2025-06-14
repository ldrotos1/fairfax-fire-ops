package gov.ffx.fire.ops.resources_service.domain.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Station {
  private int stationNumber;
  private String stationName;
  private int battalion;
  private boolean isVolunteer;
  private String address;
  private String city;
  private String state;
  private String zipCode;
  private String phoneNumber;
}
