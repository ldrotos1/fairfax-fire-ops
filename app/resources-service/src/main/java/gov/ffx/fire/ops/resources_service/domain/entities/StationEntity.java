package gov.ffx.fire.ops.resources_service.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "station", schema = "ffx_fire_ops")
public class StationEntity {

  @Id
  @Column(name = "station_number")
  private Integer stationNumber;
  
  @Column(name = "station_name")
  private String stationName;
  
  @Column(name = "battalion")
  private int battalion;

  @Column(name = "is_volunteer")
  private boolean isVolunteer;
  
  @Column(name = "address")
  private String address;
  
  @Column(name = "city")
  private String city;
  
  @Column(name = "state")
  private String state;
  
  @Column(name = "zip")
  private String zipCode;
  
  @Column(name = "phone_number")
  private String phoneNumber;
}
