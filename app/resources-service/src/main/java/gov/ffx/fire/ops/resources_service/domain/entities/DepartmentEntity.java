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
@Table(name = "department", schema = "ffx_fire_ops")
public class DepartmentEntity {
 
  @Id
  @Column(name = "dept_id")
  private Integer departmentId;

  @Column(name = "dept_full_name")
  private String departmentFullName;

  @Column(name = "dept_short_name")
  private String departmentShortName;

  @Column(name = "dept_abbreviation")
  private String departmentAbbreviation;
}
