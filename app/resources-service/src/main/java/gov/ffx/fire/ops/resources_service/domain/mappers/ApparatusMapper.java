package gov.ffx.fire.ops.resources_service.domain.mappers;

import gov.ffx.fire.ops.resources_service.domain.entities.ApparatusEntity;
import gov.ffx.fire.ops.models.apparatus.StationApparatus;;

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