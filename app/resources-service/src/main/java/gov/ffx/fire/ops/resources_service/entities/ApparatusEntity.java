package gov.ffx.fire.ops.resources_service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "apparatus", schema = "ffx_fire_ops")
public class ApparatusEntity {

  @Id
  @Column(name = "unit_designator")
  private String unitDesignator;

  @ManyToOne
  @JoinColumn(name = "apparatus_type_id", nullable = false, referencedColumnName = "apparatus_type_id")
  private ApparatusTypeEntity apparatusType;

  @ManyToOne
  @JoinColumn(name = "dept_id", nullable = false, referencedColumnName = "dept_id")
  private DepartmentEntity department;

  @Column(name = "is_reserved")
  private Boolean isReserved;

  @Column(name = "year")
  private Integer year;

  @Column(name = "make")
  private String make;

  @Column(name = "model")
  private String model;
}
