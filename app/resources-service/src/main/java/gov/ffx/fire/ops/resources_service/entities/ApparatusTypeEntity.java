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
@Table(name = "apparatus_type", schema = "ffx_fire_ops")
public class ApparatusTypeEntity {

  @Id
  @Column(name = "apparatus_type_id")
  private Integer apparatusTypeId;

  @Column(name = "apparatus_type")
  private String apparatusType;

  @Column(name = "apparatus_category")
  private String apparatusCategory;

  @Column(name = "apparatus_image")
  private String apparatusImage;

  @Column(name = "max_staff")
  private Integer maxStaff;

  @Column(name = "min_staff_ff")
  private Integer minFirefighterStaff;

  @Column(name = "min_staff_tech")
  private Integer minTechnicianStaff;

  @Column(name = "min_staff_officer")
  private Integer minOfficerStaff;

  @Column(name = "min_staff_command")
  private Integer minCommandStaff;

  @Column(name = "is_paramedic_required")
  private Boolean isParamedicRequired;

  @Column(name = "is_cross_staffed")
  private Boolean isCrossStaffed;

  @Column(name = "is_vol_staffed")
  private Boolean isVolunteerStaffed;
}
