package gov.ffx.fire.ops.resources_service.domain.entities;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "county_station", schema = "ffx_fire_ops")
public class CountyStationEntity {

  @Id
  @Column(name = "station_designator")
  private Integer stationDesignator;
  
  @Column(name = "station_number")
  private Integer stationNumber;

  @Column(name = "station_name")
  private String stationName;

  @ManyToOne
  @JoinColumn(name = "department_id", nullable = false, referencedColumnName = "dept_id")
  private DepartmentEntity department;
  
	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
	@JoinColumn( name = "station_designator" )
  private Set<CountyApparatusEntity> apparatus;

  @Column(name = "battalion")
  private int battalion;

  @Column(name = "division")
  private int division;

  @Column(name = "is_volunteer")
  private Boolean isVolunteer;
  
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

  @Column(name = "density")
  private String density;

  @Column(name = "special_ops")
  private String specialOps;
  
  @Column(name = "is_battalion_hq")
  private Boolean isBattalionHq;
 
  @Column(name = "is_division_hq")
  private Boolean isDivisionHq;
}
