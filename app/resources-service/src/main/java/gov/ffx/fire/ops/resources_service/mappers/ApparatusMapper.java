package gov.ffx.fire.ops.resources_service.mappers;

import gov.ffx.fire.ops.models.apparatus.StationApparatus;
import gov.ffx.fire.ops.resources_service.entities.ApparatusEntity;;

public class ApparatusMapper {

  public static StationApparatus apparatusEntityToApparatus(ApparatusEntity apparatusEntity) {
    return StationApparatus.builder()
      .unitDesignator(apparatusEntity.getUnitDesignator())
      .apparatusType(apparatusEntity.getApparatusType().getApparatusType())
      .apparatusCategory(apparatusEntity.getApparatusType().getApparatusCategory())
      .year(apparatusEntity.getYear())
      .make(apparatusEntity.getMake())
      .model(apparatusEntity.getModel())
      .isReserved(apparatusEntity.getIsReserved())
      .build();
  }
}