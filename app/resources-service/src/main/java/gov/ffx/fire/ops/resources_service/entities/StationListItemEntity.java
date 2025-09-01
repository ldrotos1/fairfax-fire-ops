package gov.ffx.fire.ops.resources_service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "station", schema = "ffx_fire_ops")
public class StationListItemEntity {
  
  @Id
  @Column(name = "station_designator")
  private Integer stationDesignator;
  
  @Column(name = "station_name")
  private String stationName;
  
  @Column(name = "battalion")
  private int battalion;
}
