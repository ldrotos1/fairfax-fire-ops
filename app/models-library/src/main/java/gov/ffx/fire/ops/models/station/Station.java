package gov.ffx.fire.ops.models.station;

import java.util.Set;

import gov.ffx.fire.ops.models.apparatus.StationApparatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Station {
  private int stationDesignator;
  private Integer stationNumber;
  private String stationName;
  private String department;
  private int battalion;
  private int division;
  private Boolean isVolunteer;
  private Boolean isBattalionHq;
  private Boolean isDivisionHq;
  private String address;
  private String city;
  private String state;
  private String zipCode;
  private String phoneNumber;
  private String density;
  private String specialOps;
  private Set<StationApparatus> apparatus;
}
