package gov.ffx.fire.ops.resources_service.domain.mappers;

import gov.ffx.fire.ops.resources_service.domain.entities.CountyApparatusEntity;
import gov.ffx.fire.ops.resources_service.domain.models.CountyStationApparatus;

public class ApparatusMapper {

  public static CountyStationApparatus apparatusEntityToApparatus(CountyApparatusEntity apparatusEntity) {
    return CountyStationApparatus.builder()
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