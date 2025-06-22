package gov.ffx.fire.ops.resources_service.domain.models;

import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountyStation {
  private int stationDesignator;
  private Integer stationNumber;
  private String stationName;
  private String department;
  private int battalion;
  private int division;
  private boolean isVolunteer;
  private Boolean isBattalionHq;
  private Boolean isDivisionHq;
  private String address;
  private String city;
  private String state;
  private String zipCode;
  private String phoneNumber;
  private String density;
  private String specialOps;
  private Set<CountyStationApparatus> apparatus;
}
