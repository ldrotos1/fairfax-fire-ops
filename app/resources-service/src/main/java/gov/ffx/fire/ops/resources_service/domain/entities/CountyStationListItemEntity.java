package gov.ffx.fire.ops.resources_service.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@Table(name = "county_station", schema = "ffx_fire_ops")
public class CountyStationListItemEntity {
  
  @Id
  @Column(name = "station_designator")
  private Integer stationDesignator;
  
  @Column(name = "station_name")
  private String stationName;
  
  @Column(name = "battalion")
  private int battalion;
}
